package PruebaPerformance.selenium.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CatalogPage extends BasePage {

  @FindBy(css = "#SidebarContent a")
  private List<WebElement> categories;

  public CatalogPage(WebDriver driver) {
    super(driver);
  }

  public CategoryPage goToCategory(int index) {
    WebElement category = categories.get(index);
    click(category);
    return new CategoryPage(driver);
  }

}
