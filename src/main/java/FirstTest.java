import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {

    public WebDriver getWebsite() {
        WebDriver chromeDriver = new ChromeDriver();
        chromeDriver.get("");
        chromeDriver.manage().window().maximize();
        return chromeDriver;
    }

}