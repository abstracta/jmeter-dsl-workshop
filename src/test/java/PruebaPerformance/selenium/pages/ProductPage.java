package PruebaPerformance.selenium.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

  @FindBy(css = "table * td a.Button")
  private List<WebElement> productTypes;

  public ProductPage(WebDriver driver) {
    super(driver);
  }

  public CartPage addToCart(int index) {
    WebElement category = productTypes.get(index);
    click(category);
    return new CartPage(driver);
  }

}
