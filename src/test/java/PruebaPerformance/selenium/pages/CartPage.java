package PruebaPerformance.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

  @FindBy(css = "#Cart > a")
  private WebElement checkoutButton;

  public CartPage(WebDriver driver) {
    super(driver);
  }

  public LoginPage checkout() {
    click(checkoutButton);
    return new LoginPage(driver);
  }

}
