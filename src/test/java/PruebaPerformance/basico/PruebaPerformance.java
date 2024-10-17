package PruebaPerformance.basico;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static us.abstracta.jmeter.javadsl.JmeterDsl.httpSampler;
import static us.abstracta.jmeter.javadsl.JmeterDsl.testPlan;
import static us.abstracta.jmeter.javadsl.JmeterDsl.threadGroup;

import java.io.IOException;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

public class PruebaPerformance {

  @Test
  public void performanceTest() throws IOException {
    TestPlanStats stats = testPlan(
        threadGroup(1, 1,
            httpSampler("https://petstore.octoperf.com")
        )
        //,resultsTreeVisualizer()
    ).run(); //showInGui() para ver la prueba mediante la interfaz grafica de JMeter
    assertThat(stats.overall().sampleTimePercentile99()).isLessThan(Duration.ofMillis(10));
  }
}