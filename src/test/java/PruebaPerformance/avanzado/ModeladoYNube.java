package PruebaPerformance.avanzado;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.TimeoutException;
import org.apache.http.entity.ContentType;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;
import us.abstracta.jmeter.javadsl.core.listeners.JtlWriter.SampleStatus;
import us.abstracta.jmeter.javadsl.http.DslHttpSampler;
import us.abstracta.jmeter.javadsl.octoperf.OctoPerfEngine;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;
import static us.abstracta.jmeter.javadsl.core.listeners.AutoStopListener.AutoStopCondition.errors;

public class ModeladoYNube {

    private static String url = "https://petstore.octoperf.com";

    @Test
    public void pruebaPeformance() throws IOException, InterruptedException, TimeoutException {
        TestPlanStats stats = testPlan(
            httpDefaults().encoding(StandardCharsets.UTF_8),
            csvDataSet(testResource("usuarios.csv")),
            threadGroup()
                .rampTo(10, Duration.ofSeconds(60))
                .holdFor(Duration.ofSeconds(60))
                .rampTo(0, Duration.ofSeconds(60))
                .children(
                    transaction("HomePage",
                        getHomepage()
                    ),
                    transaction("Login",
                        getLogin(),
                        login()
                    ),
                    autoStop()
                        .when(errors().total().greaterThan(20l))
                ),
            jtlWriter("./", "Resultados"),
            jtlWriter("./", "Errores").withAllFields().logOnly(SampleStatus.ERROR)
            //,resultsTreeVisualizer()
        ).runIn(new OctoPerfEngine(System.getenv("OctoPerfToken"))
            .projectName("prueba"));
        assertThat(stats.overall().sampleTimePercentile99()).isLessThan(Duration.ofMillis(10));
    }

    @NotNull
    private static DslHttpSampler getHomepage() {
        return httpSampler(url + "/actions/Catalog.action");
    }

    private static DslHttpSampler getLogin() {
        return httpSampler(url + "/actions/Account.action?signonForm=")
            .children(
                regexExtractor("_sourcePage", "name=\"_sourcePage\" value=\"(.*?)\""),
                regexExtractor("__fp", "name=\"__fp\" value=\"(.*?)\"")
            );
    }

    private static DslHttpSampler login() {
        return httpSampler(url + "/actions/Account.action").post(
                "username=${usuario}&password=${password}&signon=Login&_sourcePage${_sourcePage"
                    + "}=&__fp"
                    + "=${__fp}",
                ContentType.APPLICATION_FORM_URLENCODED)
            .header("accept-encoding", "\"gzip,deflate, br, zstd\"")
            .downloadEmbeddedResources()
            .children(
                constantTimer(Duration.ofSeconds(1)),
                responseAssertion("Welcome ${usuario}")
            );
    }
}