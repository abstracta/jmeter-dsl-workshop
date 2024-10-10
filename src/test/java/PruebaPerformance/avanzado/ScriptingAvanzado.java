package PruebaPerformance.avanzado;

import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

import java.io.IOException;
import java.time.Duration;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class ScriptingAvanzado {

    @Test
    public void pruebaPerformance() throws IOException{
        TestPlanStats stats = testPlan(
                csvDataSet(testResource("usuarios.csv")),
                threadGroup(5,1,
                        httpSampler("https://petstore.octoperf.com"),
                        httpSampler("https://petstore.octoperf.com/actions/Account.action")
                                .post("username=${usuario}&password=${password}&signon=Login&_sourcePage=BD_MhFDsQSGuPF45FJbPqTqoX2K9774cvzCEue_pFME8lfkMQ0ERqCOnL5-Qo0AJFjNa8KnMc6qEJ4l7_DXHMyU3Qxc5rUuyD_Sdg2djJ0U%3D&__fp=8ZuxvgGH1OoPNSnmV5Hy5PiZnlcpmgmtjFqewKDO9aBO4bRVeVnasaIY8Oz76u7C", ContentType.TEXT_PLAIN)
                                .header("header1","valor1")
                                .downloadEmbeddedResources()
                                .children(
                                        constantTimer(Duration.ofMillis(1000)),
                                        regexExtractor("categoryId","categoryId=(.*?)\"")
                                                .defaultValue("NOT_FOUND")
                                ),
                        httpSampler("https://petstore.octoperf.com/actions/Catalog.action?viewCategory=&categoryId=${categoryId}")
                ),
                resultsTreeVisualizer()
        ).run();
    }
}