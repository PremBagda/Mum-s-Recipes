// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.util.concurrent.TimeUnit;

/*
 * This class does an email login authentication check using
 * the email mumsrecipeuser@hotmail.com in Chrome Browser.
 */
public class CuisineValidityTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;

  @Before
  public void setUp() {
    // Chrome Browser setup.
    System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }

  @Before
  public void login() {
    driver.get("http://localhost:5000");
    driver.manage().window().maximize();
    // Locate and click the email option.
    driver.findElement(By.cssSelector(".firebaseui-idp-password > .firebaseui-idp-text-long")).click();
    // 5 seconds timeout required for google login interface to load
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.findElement(By.name("email")).click();
    // Enter email as "mumsrecipeuser@hotmail.com"
    driver.findElement(By.name("email")).sendKeys("mumsrecipeuser@hotmail.com");
    driver.findElement(By.cssSelector(".firebaseui-id-submit")).click();
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).sendKeys("P@ssw0rd1");
    driver.findElement(By.cssSelector(".firebaseui-id-submit")).click();
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void invalidCuisineTest() {
    // Locate and click the cuisine text area.
    driver.findElement(By.linkText("Add Cuisine")).click();
    driver.findElement(By.id("add-cuisine-form")).click();

    // input invalid cuisine "12345"
    driver.findElement(By.id("add-cuisine-form")).sendKeys("12345");
    String input = driver.findElement(By.id("add-cuisine-form")).getAttribute("value");
    assertFalse((input.equals("12345")));

    // input invalid cuisine "@@@@@"
    driver.findElement(By.id("add-cuisine-form")).sendKeys("@@@@@");
    input = driver.findElement(By.id("add-cuisine-form")).getAttribute("value");
    assertFalse((input.equals("@@@@@")));

    // input invalid cuisine "[[[[["
    driver.findElement(By.id("add-cuisine-form")).sendKeys("[[[[[");
    input = driver.findElement(By.id("add-cuisine-form")).getAttribute("value");
    assertFalse((input.equals("[[[[[")));

    // input invalid cuisine "`````"
    driver.findElement(By.id("add-cuisine-form")).sendKeys("`````");
    input = driver.findElement(By.id("add-cuisine-form")).getAttribute("value");
    assertFalse((input.equals("``````")));

    // input invalid cuisine "{{{{{"
    driver.findElement(By.id("add-cuisine-form")).sendKeys("{{{{{");
    input = driver.findElement(By.id("add-cuisine-form")).getAttribute("value");
    assertFalse((input.equals("{{{{{")));
    System.out.println("input = " + input);
  }

  @Test
  public void validCuisineTest() {
    // Locate and click the cuisine text area.
    driver.findElement(By.linkText("Add Cuisine")).click();
    driver.findElement(By.id("add-cuisine-form")).click();

    // Edge case: valid input cuisine "aaaaa"
    driver.findElement(By.id("add-cuisine-form")).sendKeys("aaaaa");
    String input = driver.findElement(By.id("add-cuisine-form")).getAttribute("value");
    System.out.println("input = " + input);
    assertTrue((input.equals("aaaaa")));
    driver.findElement(By.id("add-cuisine-form")).clear();

    // Edge case: valid input cuisine "zzzzz"
    driver.findElement(By.id("add-cuisine-form")).sendKeys("zzzzz");
    input = driver.findElement(By.id("add-cuisine-form")).getAttribute("value");
    assertTrue((input.equals("zzzzz")));
    driver.findElement(By.id("add-cuisine-form")).clear();

    // Edge case: valid input cuisine "AAAAA"
    driver.findElement(By.id("add-cuisine-form")).sendKeys("AAAAA");
    input = driver.findElement(By.id("add-cuisine-form")).getAttribute("value");
    assertTrue((input.equals("AAAAA")));
    driver.findElement(By.id("add-cuisine-form")).clear();

    // Edge case: valid input cuisine "ZZZZZ"
    driver.findElement(By.id("add-cuisine-form")).sendKeys("ZZZZZ");
    input = driver.findElement(By.id("add-cuisine-form")).getAttribute("value");
    assertTrue((input.equals("ZZZZZ")));
    driver.findElement(By.id("add-cuisine-form")).clear();
  }
}
