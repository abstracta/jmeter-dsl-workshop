package PruebaPerformance.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import PruebaPerformance.selenium.pages.BasePage;
import PruebaPerformance.selenium.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import us.abstracta.selenium.jmeterdsl.JmeterDslSeleniumRecorder;

public class PetStoreTestCorrelationRecorder {

  private WebDriver driver;

  @RegisterExtension
  public final JmeterDslSeleniumRecorder recorder = new JmeterDslSeleniumRecorder()
      .basePageObject(BasePage.class)
      .urlExcludes("(?i).*\\.(bmp|css|js|gif|ico|jpe?g|png|swf"
          + "|woff|woff2)", ".+google.+")
      .correlationRule("JSESSIONID",
          "JSESSIONID=([\\d\\w]+)",
          "jsessionid=([\\d\\w]+)")
      .correlationRule("categoryId",
          "categoryId=(\\w+)",
          "categoryId=(\\w+)")
      .correlationRule("productId",
          "productId=([\\w\\d-]+)",
          "productId=([\\w\\d-]+)")
      .correlationRule("workingItemId",
          "workingItemId=([\\d\\w-]+)",
          "workingItemId=([\\d\\w-]+)");

  @BeforeEach
  public void setup() {
    var options = new ChromeOptions();
    //options.addArguments("--headless=new");
    options.setImplicitWaitTimeout(TestConfig.getImplicitTimeout());
    options.setAcceptInsecureCerts(true);
    options.setProxy(recorder.getProxy());
    driver = new ChromeDriver(options);
  }

  @AfterEach
  public void teardown() {
    driver.close();
  }

  @Test
  public void testCheckout() {
    driver.get("https://petstore.octoperf.com/");
    var loginPage = new HomePage(driver)
        .enter()
        .goToCategory(0)
        .goToProduct(0)
        .addToCart(0)
        .checkout();
    assertEquals(
        "You must sign on before attempting to check out. Please sign on and try checking out "
            + "again.",
        loginPage.errorMessage());
  }
}
