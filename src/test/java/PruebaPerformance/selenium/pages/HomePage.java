package PruebaPerformance.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

  @FindBy(css = "#Content a")
  private WebElement enterLink;

  public HomePage(WebDriver driver) {
    super(driver);
  }

  public CatalogPage enter() {
    click(enterLink);
    return new CatalogPage(driver);
  }

}
