import org.junit.jupiter.api.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class PruebaPerformance_scriptingAvanzado {

    @Test
    public voPruebaPerformance_scriptingAvanzadot() throws IOException {
        TestPlanStats stats = testPlan(
            threadGroup(1,1,
                httpSampler("https://opencart.abstracta.us")
                ),
                resultsTreeVisualizer()
        ).run(); // showInGui(); si queremos ver nuestra prueba en la GUI de JMeter
        assertThat(stats.overall().sampleTimePercentile99()).isLessThan(Duration.ofMillis(500));
    }
}
