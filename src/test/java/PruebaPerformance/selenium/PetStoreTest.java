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

public class PetStoreTest {

  private WebDriver driver;

  @BeforeEach
  public void setup() {
    var options = new ChromeOptions();
    options.setImplicitWaitTimeout(TestConfig.getImplicitTimeout());
    options.setAcceptInsecureCerts(true);
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
