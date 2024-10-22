package PruebaPerformance.avanzado;

import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

import java.io.IOException;
import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class ScriptingAvanzado {

  @Test
  public void performanceTest() throws IOException {
    TestPlanStats stats = testPlan(
        threadGroup(1, 1,
            csvDataSet(testResource("usuarios.csv")),
            httpSampler("https://petstore.octoperf.com/actions/Catalog.action"),
            httpSampler("https://petstore.octoperf.com/actions/Account.action?signonForm=")
                .children(
                    regexExtractor("_sourcePage", "name=\"_sourcePage\" value=\"(.*?)\""),
                    regexExtractor("__fp", "name=\"__fp\" value=\"(.*?)\"")
                ),
            httpSampler("https://petstore.octoperf.com/actions/Account.action").post("username=${usuario}"
                    + "&password=${contraseña}"
                    + "&signon=Login"
                    + "&_sourcePage=${_sourcePage}"
                    + "&__fp=${__fp}",
                ContentType.APPLICATION_FORM_URLENCODED)
                .header("accept-encoding","gzip,deflate, br, zstd")
                .downloadEmbeddedResources()
                .children(
                    constantTimer(Duration.ofSeconds(1))
                )
        )
        //,resultsTreeVisualizer()
    ).run(); //showInGui() para ver la prueba mediante la interfaz grafica de JMeter
    assertThat(stats.overall().sampleTimePercentile99()).isLessThan(Duration.ofMillis(10));
  }
}
