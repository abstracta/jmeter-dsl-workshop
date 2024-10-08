import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;
import us.abstracta.jmeter.javadsl.http.DslHttpSampler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;
import static us.abstracta.jmeter.javadsl.core.listeners.AutoStopListener.AutoStopCondition.errors;

public class BuenasPracticas {
    String host = "https://petstore.octoperf.com";

    @Test
    public void performanceTest() throws IOException{
        testPlan(
                httpDefaults().encoding(StandardCharsets.UTF_8),
                csvDataSet(testResource("usuarios.csv")),
                threadGroup(1,4,
                        transaction("Página principal y Login",
                                pedido("prueba","Sign In"),
                                pedidoPostLogin(),
                                autoStop()
                                        .when(errors().total().greaterThanOrEqualTo(2l)),
                                constantTimer(Duration.ofMillis(1500))
                                ),
                        transaction("Selección de mascota",
                                pedido(host + "/actions/Catalog.action?viewCategory=&categoryId=${mascota}","Product ID")
                                )
                        ),
                resultsTreeVisualizer()
        ).run();
    }

    DslHttpSampler pedido(String url, String assertion){
        return httpSampler(url)
                .downloadEmbeddedResources()
                .children(
                        responseAssertion(assertion)
                );
    }

    DslHttpSampler pedidoPostLogin(){
        return httpSampler(host + "/actions/Account.action")
                .post("username=${usuario}&password=${contraseña}&signon=Login&_sourcePage=qcR5hjqgK1HArN7zn1V_il-AIOFms14gyG-9ci38UCHIEO4y9EWeWvCBDFFSY47E_11eSVWVTv2NoOuzGc0rYImweudTj8j25xdnyyoXvJg%3D&__fp=4VRNOQwDuzOYs9OJDgXmI1b5-TFlnl5FB6PPbNyCFVEUDybZeKXgUz-d9detouAn",ContentType.APPLICATION_FORM_URLENCODED)
                .header("accept-encoding","gzip, deflate, br, zstd")
                .downloadEmbeddedResources()
                .children(
                        regexExtractor("mascota","Catalog.action\\?viewCategory=&amp;categoryId=(.*?)\""),
                        responseAssertion("Sign Out")
                );
    }
}
