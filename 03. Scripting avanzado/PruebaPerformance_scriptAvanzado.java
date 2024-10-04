import org.apache.http.entity.ContentType;
import org.apache.jmeter.protocol.http.util.HTTPConstants;
import org.junit.jupiter.api.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

import java.io.IOException;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class PruebaPerformance_scriptAvanzado {

    @Test
    public void performanceTest() throws IOException {
        TestPlanStats stats = testPlan(
                csvDataSet("idProductos"),
            threadGroup(1,1,
                httpSampler("https://opencart.abstracta.us/login")
                        .post("testBody_opcion1", ContentType.APPLICATION_JSON)
                        .header("header1","valorHeader1")
                        .header("header2","valorHeader2")
                        .downloadEmbeddedResources()
                        .children(
                                regexExtractor("TokenID","Token: (.*?),")
                        ),
                uniformRandomTimer(Duration.ofSeconds(4),Duration.ofSeconds(10)),
                httpSampler("https://opencart.abstracta.us/verProductos?id:${id}")
                        .header("header1","valorHeader1")
                        .header("Authorization","${TokenID}"),
                threadPause(Duration.ofSeconds(4)),
                httpSampler("https://opencart.abstracta.us/agregarProductos")
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
}
