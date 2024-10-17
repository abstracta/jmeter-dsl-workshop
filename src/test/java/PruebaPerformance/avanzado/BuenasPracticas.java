package PruebaPerformance.avanzado;

import static us.abstracta.jmeter.javadsl.JmeterDsl.autoStop;
import static us.abstracta.jmeter.javadsl.JmeterDsl.constantTimer;
import static us.abstracta.jmeter.javadsl.JmeterDsl.csvDataSet;
import static us.abstracta.jmeter.javadsl.JmeterDsl.httpDefaults;
import static us.abstracta.jmeter.javadsl.JmeterDsl.httpSampler;
import static us.abstracta.jmeter.javadsl.JmeterDsl.jtlWriter;
import static us.abstracta.jmeter.javadsl.JmeterDsl.regexExtractor;
import static us.abstracta.jmeter.javadsl.JmeterDsl.responseAssertion;
import static us.abstracta.jmeter.javadsl.JmeterDsl.resultsTreeVisualizer;
import static us.abstracta.jmeter.javadsl.JmeterDsl.testPlan;
import static us.abstracta.jmeter.javadsl.JmeterDsl.testResource;
import static us.abstracta.jmeter.javadsl.JmeterDsl.threadGroup;
import static us.abstracta.jmeter.javadsl.JmeterDsl.transaction;
import static us.abstracta.jmeter.javadsl.core.listeners.AutoStopListener.AutoStopCondition.errors;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;
import us.abstracta.jmeter.javadsl.http.DslHttpSampler;

public class BuenasPracticas {
    private String host = "https://petstore.octoperf.com";

    @Test
    public void pruebaPerformance() throws IOException{
        TestPlanStats stats = testPlan(
                httpDefaults().encoding(StandardCharsets.UTF_8),
                csvDataSet(testResource("usuarios.csv")),
                threadGroup(5,1,
                        transaction("Home page y Login",
                            pedidoGet(host, "Saltwater, Freshwater"),
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

