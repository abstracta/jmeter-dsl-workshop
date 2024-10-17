package PruebaPerformance.selenium.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CategoryPage extends BasePage {

  @FindBy(css = "table * td a")
  private List<WebElement> products;

  public CategoryPage(WebDriver driver) {
    super(driver);
  }

  public ProductPage goToProduct(int index) {
    WebElement category = products.get(index);
    click(category);
    return new ProductPage(driver);
  }

}
