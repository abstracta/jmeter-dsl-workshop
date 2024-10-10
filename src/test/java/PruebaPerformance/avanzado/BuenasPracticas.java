package PruebaPerformance.avanzado;

import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;
import us.abstracta.jmeter.javadsl.http.DslHttpSampler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;
import static us.abstracta.jmeter.javadsl.core.listeners.AutoStopListener.AutoStopCondition.errors;

public class BuenasPracticas {
    private String host = "https://petstore.octoperf.com";

    @Test
    public void pruebaPerformance() throws IOException{
        TestPlanStats stats = testPlan(
                httpDefaults().encoding(StandardCharsets.UTF_8),
                csvDataSet(testResource("usuarios.csv")),
                threadGroup(5,1,
                        transaction("Home page y Login",
                                pedidoGet(host,"Saltwater, Freshwater"),
                                login()),
                                autoStop()
                                    .when(errors().total().greaterThanOrEqualTo(2l)),
                        transaction("Selección de categoria",
                                pedidoGet(host + "/actions/Catalog.action?viewCategory=&categoryId=${categoryId}","Product"))
                ),
                //
                jtlWriter("../../","Success"),
                jtlWriter("../../","Errors").withAllFields(),
                resultsTreeVisualizer()
        ).run();
    }

    DslHttpSampler login(){
        return httpSampler("https://petstore.octoperf.com/actions/Account.action")
                .post("username=${usuario}&password=${password}&signon=Login&_sourcePage=BD_MhFDsQSGuPF45FJbPqTqoX2K9774cvzCEue_pFME8lfkMQ0ERqCOnL5-Qo0AJFjNa8KnMc6qEJ4l7_DXHMyU3Qxc5rUuyD_Sdg2djJ0U%3D&__fp=8ZuxvgGH1OoPNSnmV5Hy5PiZnlcpmgmtjFqewKDO9aBO4bRVeVnasaIY8Oz76u7C", ContentType.TEXT_PLAIN)
                .header("header1","valor1")
                .downloadEmbeddedResources()
                .children(
                        constantTimer(Duration.ofMillis(1000)),
                        regexExtractor("categoryId","categoryId=(.*?)\"")
                                .defaultValue("NOT_FOUND"),
                        responseAssertion().containsSubstrings("Sign Out")
                );
    }

    DslHttpSampler pedidoGet(String url,String assertion){
        return httpSampler(url)
                .children(
                        responseAssertion(assertion)
                );
    }
}

