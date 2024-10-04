import org.apache.http.entity.ContentType;
import org.apache.jmeter.protocol.http.util.HTTPConstants;
import org.junit.jupiter.api.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;
import us.abstracta.jmeter.javadsl.http.DslHttpSampler;

import java.io.IOException;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;
import static us.abstracta.jmeter.javadsl.core.listeners.AutoStopListener.AutoStopCondition.errors;

public class PruebaPerformance_buenasPracticas {

    private static String host = "https://opencart.abstracta.us/";

    @Test
    public void performanceTest() throws IOException {
        TestPlanStats stats = testPlan(
                csvDataSet("idProductos"),
            threadGroup(1,1,
                httpSampler(host + "login")
                        .post("testBody_opcion1", ContentType.APPLICATION_JSON)
                        .header("header1","valorHeader1")
                        .header("header2","valorHeader2")
                        .downloadEmbeddedResources()
                        .children(
                                regexExtractor("TokenID","Token: (.*?),"),
                                responseAssertion().containsSubstrings("Login exitoso"),
                                autoStop()
                                        .when(errors().total().greaterThan(2l))
                        ),
                uniformRandomTimer(Duration.ofSeconds(4),Duration.ofSeconds(10)),
                transaction("página principal y buscar producto",
                        simpleController("Página principal",
                                //mismo pedido que abajo pero mas complicado de leer
                                httpSampler("https://opencart.abstracta.us/")
                                        .children(
                                                responseAssertion().containsSubstrings("Login exitoso")
                                        ),
                                // mismo pedido que el de arriba pero mas legible a gran escala
                                getRequest("Página princial","Bienvenidos")
                        ),
                        simpleController("Productos",
                                httpSampler("https://opencart.abstracta.us/verProductos?id:${id}")
                        )
                ),
                httpSampler("https://opencart.abstracta.us/verProductos?id:${id}")
                        .header("header1","valorHeader1")
                        .header("Authorization","${TokenID}"),
                threadPause(Duration.ofSeconds(4)),
                httpSampler(host + "/agregarProductos")
                        .method(HTTPConstants.POST)
                        .param("opción2","testBody_opcion2")
                        .header("header1","valorHeader1")
                        .header("Authorization","${TokenID}")
                        .downloadEmbeddedResources()
                ),
                resultsTreeVisualizer()
        ).run();
        assertThat(stats.overall().sampleTimePercentile99()).isLessThan(Duration.ofMillis(500));
    }

    private static DslHttpSampler getRequest(String host, String assertion){
        return httpSampler(host)
                .children(
                        responseAssertion().containsSubstrings(assertion)
                );
    }
}
