
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class Main {

    private static WebDriver driver = getInitDriver();
    private static WebDriverWait wait = new WebDriverWait(driver, 10);


    public static void main(String[] args) {

        sinIn(driver);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-submenu]")));

        List<WebElement> elements = getVisibleMenuOption();

        int index = 0;
        while (index < elements.size()) {
            WebElement element = elements.get(index++);

            element.click();
            System.out.println(getTitle(driver));

            String currentUrl = driver.getCurrentUrl();
            driver.navigate().refresh();
            assertEquals(currentUrl, driver.getCurrentUrl());

            elements = getVisibleMenuOption();
            wait.until(ExpectedConditions.urlMatches("^((?!" + currentUrl + ").)*$"));

        }
        sinOut(driver);
    }

    private static List<WebElement> getVisibleMenuOption() {
        List<WebElement> elements = driver.findElements(By.cssSelector("[data-submenu]"));
        elements = elements.stream().filter(e -> (e.getAttribute("class").contains("maintab") || e.getAttribute("class").contains("levelone"))).collect(Collectors.toList());
        return elements;
    }


    public static WebDriver getInitDriver() {
        System.setProperty("webdriver.chrome.driver", Main.class.getResource("chromedriver.exe").getPath());
        return new ChromeDriver();
    }

    public static String getTitle(WebDriver driver) {
        String title1 = null;
        String title2 = null;
        try {
            title1 = driver.findElement(By.cssSelector(".page-title")).getText();
        } catch (NoSuchElementException ignored) {
        }

        try {
            title2 = driver.findElement(By.cssSelector(".header-toolbar > [class=title]")).getText();
        } catch (NoSuchElementException ignored) {
        }
        return title1 != null ? title1 : title2;

    }

    public static void sinOut(WebDriver driver) {
        WebElement element = driver.findElement(By.cssSelector("#employee_infos > a > span img"));
        element.click();
        driver.findElement(By.className("icon-signout")).click();
    }

    public static void sinIn(WebDriver driver) {
        driver.manage().window().maximize();
        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0");
        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("passwd"));

        email.sendKeys("webinar.test@gmail.com");
        password.sendKeys("Xcg7299bnSmMuRLp9ITw");
        driver.findElement(By.name("submitLogin")).click();
        
    }

}