import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment_1 {

    public WebDriver getWebsite() {
        WebDriver chromeDriver = new ChromeDriver();
        chromeDriver.get("https://demowebshop.tricentis.com/");
        chromeDriver.manage().window().maximize();
        return chromeDriver;
    }

    public void doRegistration(WebDriver chromeDriver) {
        WebElement registerLink = chromeDriver.findElement(By.className("ico-register"));
        registerLink.click();

        WebElement radioFemale = chromeDriver.findElement(By.id("gender-male"));
        radioFemale.click();

        WebElement FirstName = chromeDriver.findElement(By.name("FirstName"));
        FirstName.sendKeys("Siddhesh");

        WebElement LastName = chromeDriver.findElement(By.name("LastName"));
        LastName.sendKeys("Lamkhade");

        WebElement Email = chromeDriver.findElement(By.id("Email"));
        Email.sendKeys("sidlamkhade@gmail.com");

        WebElement Password = chromeDriver.findElement(By.name("Password"));
        Password.sendKeys("sidlamkhade@123");


        WebElement conformPassword = chromeDriver.findElement(By.name("ConfirmPassword"));
        conformPassword.sendKeys("sidlamkhade@123");

        WebElement register = chromeDriver.findElement(By.name("register-button"));
        register.click();
    }

    public static void main(String[] args) {
        FirstTest taskDay = new FirstTest();
        WebDriver driver = taskDay.getWebsite();
        taskDay.doRegistration(driver);
    }
}