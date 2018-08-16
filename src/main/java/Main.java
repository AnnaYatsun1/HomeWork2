import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        WebDriver driver = getInitDriver();
        driver.manage().window().maximize();
        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0");
        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("passwd"));

        email.sendKeys("webinar.test@gmail.com");
        password.sendKeys("Xcg7299bnSmMuRLp9ITw");
        driver.findElement(By.name("submitLogin")).click();

//
//        List<WebElement> elements=
        WebElement emaiqq =   driver.findElement(By.id("subtab-AdminParentOrders"));
                emaiqq.click();
//        for (int i = 0; i <elements.size() ; i++) {
//            elements.get(i).click();
//
//
//        }


    }

    public static WebDriver getInitDriver() {
        System.setProperty("webdriver.chrome.driver", Main.class.getResource("chromedriver.exe").getPath());
        return new ChromeDriver();
    }

    public static void getlohinPage(WebDriver driver) {


    }
}
