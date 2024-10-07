import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class PruebaPerformance_scriptingAvanzado {

    @Test
    public void performanceTest() throws IOException{
        testPlan(
                csvDataSet(testResource("usuarios.csv")),
                threadGroup(1,3,
                        httpSampler("https://petstore.octoperf.com")
                                .children(jsr223PostProcessor(c -> c.prevMap())),
                        httpSampler("https://petstore.octoperf.com/actions/Account.action")
                                .post("username=${usuario}&password=${contraseña}&signon=Login&_sourcePage=qcR5hjqgK1HArN7zn1V_il-AIOFms14gyG-9ci38UCHIEO4y9EWeWvCBDFFSY47E_11eSVWVTv2NoOuzGc0rYImweudTj8j25xdnyyoXvJg%3D&__fp=4VRNOQwDuzOYs9OJDgXmI1b5-TFlnl5FB6PPbNyCFVEUDybZeKXgUz-d9detouAn",ContentType.APPLICATION_FORM_URLENCODED)
                                .header("accept-encoding","gzip, deflate, br, zstd")
                                .downloadEmbeddedResources()
                                .children(
                                        regexExtractor("mascota","Catalog.action\\?viewCategory=&amp;categoryId=(.*?)\"")
                                ),
                        constantTimer(Duration.ofMillis(1500)),
                        httpSampler("https://petstore.octoperf.com/actions/Catalog.action?viewCategory=&categoryId=${mascota}"),
                        threadPause(Duration.ofMillis(1500))
                        ),
                resultsTreeVisualizer()
        ).run();
    }
}
