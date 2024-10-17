package PruebaPerformance.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

import java.io.IOException;
import java.time.Duration;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import us.abstracta.selenium.jmeterdsl.execution.FrontendPerformanceTest;
import us.abstracta.selenium.jmeterdsl.execution.TelemetryConfig;

public class PerformanceTest {

  @Test
  public void test() throws IOException {
    var envConfig = new TelemetryConfig();
    var stats = testPlan(
        threadGroup(1, 1,
          httpDefaults()
            .encoding(StandardCharsets.UTF_8)
            .followRedirects(false),
          httpSampler("/-2", "https://petstore.octoperf.com:443"),
          httpSampler("/actions/Catalog.action-5", "https://petstore.octoperf.com:443/actions/Catalog.action")
            .children(
              regexExtractor("categoryId#2", "categoryId=(\\w+)")
                .defaultValue("categoryId#2_NOT_FOUND")
            ),
          httpSampler("/actions/Catalog.action;jsessionid=D593DCF34230ACD72064B7D31A96E67E-21", "https://petstore.octoperf.com:443/actions/Catalog.action;jsessionid=D593DCF34230ACD72064B7D31A96E67E")
            .param("viewCategory", "")
            .param("categoryId", "${categoryId#2}")
            .children(
              regexExtractor("categoryId#3", "categoryId=(\\w+)")
                .defaultValue("categoryId#3_NOT_FOUND"),
              regexExtractor("productId#3", "productId=([\\w\\d-]+)")
                .defaultValue("productId#3_NOT_FOUND")
            ),
          httpSampler("/actions/Catalog.action-22", "https://petstore.octoperf.com:443/actions/Catalog.action")
            .param("viewProduct", "")
            .param("productId", "${productId#3}")
            .children(
              regexExtractor("categoryId#4", "categoryId=(\\w+)")
                .defaultValue("categoryId#4_NOT_FOUND"),
              regexExtractor("workingItemId#4", "workingItemId=([\\d\\w-]+)")
                .defaultValue("workingItemId#4_NOT_FOUND")
            ),
          httpSampler("/actions/Cart.action-23", "https://petstore.octoperf.com:443/actions/Cart.action")
            .param("addItemToCart", "")
            .param("workingItemId", "${workingItemId#4}")
            .children(
              regexExtractor("categoryId#5", "categoryId=(\\w+)")
                .defaultValue("categoryId#5_NOT_FOUND"),
              regexExtractor("workingItemId#5", "workingItemId=([\\d\\w-]+)")
                .defaultValue("workingItemId#5_NOT_FOUND")
            ),
          httpSampler("/actions/Order.action-25", "https://petstore.octoperf.com:443/actions/Order.action")
            .param("newOrderForm", "")
            .children(
              regexExtractor("categoryId#6", "categoryId=(\\w+)")
                .defaultValue("categoryId#6_NOT_FOUND")
            )
        ),
        influxDbListener(String.format("%s/api/v2/write?org=%s&bucket=%s",
            envConfig.get("INFLUX_URI"), envConfig.get("INFLUX_ORG"),
            envConfig.get("INFLUX_BUCKET")))
            .token(envConfig.get("INFLUX_TOKEN"))

    ).run();
    assertEquals(0, stats.overall().errorsCount());
  }

  public static void main(String[] args) throws InterruptedException{
    new FrontendPerformanceTest()
        .backendPerformanceTest(PerformanceTest.class.getName(), "test")
        .frontendTest("PruebaPerformance.selenium.PetStoreTestCorrelationRecorder", "testCheckout", Duration.ofMinutes(5))
        .basePageObject("PruebaPerformance.selenium.pages.BasePage")
        .run();
  }


}