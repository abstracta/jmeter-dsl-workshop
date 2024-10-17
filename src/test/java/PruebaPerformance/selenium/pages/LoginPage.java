package PruebaPerformance.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

  @FindBy(css = "#Content > ul > li")
  private WebElement errorMessage;

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  public String errorMessage() {
    return getText(errorMessage);
  }

}
