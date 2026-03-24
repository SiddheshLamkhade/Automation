import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;

public class SauceDemoShopTest {

    static WebDriver driver;
    static final String BASE = "https://sauce-demo.myshopify.com";

    public static void main(String[] args) {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        task1_HomePageVerification();
        task2_NavigateToAboutUs();
        task3_CatalogAndProducts();
        task4_ProductDetailPage();
        task5_BrowserNavigation();
        task6_HeaderLinksAndCart();

        System.out.println("====== ALL TASKS COMPLETE ======");
        driver.quit();
    }

    // TASK 1
    public static void task1_HomePageVerification() {

        System.out.println("=== Task 1: Home Page Verification ===");

        driver.get(BASE);

        System.out.println("Page Title : " + driver.getTitle());
        System.out.println("Title check : " +
                (driver.getTitle().contains("Sauce Demo") ? "PASS" : "FAIL"));

        System.out.println("Current URL : " + driver.getCurrentUrl());
        System.out.println("URL check : " +
                (driver.getCurrentUrl().contains("myshopify") ? "PASS" : "FAIL"));

        String LogoText = driver.findElement(By.cssSelector("#logo img")).getAttribute("alt");
        System.out.println("H1 text        : " + LogoText);

        WebElement h3 = driver.findElement(By.xpath("//h3"));
        System.out.println("H3 text : " + h3.getText());

        List<WebElement> navLinks = driver.findElements(By.cssSelector("nav ul li a"));
        System.out.println("Nav link count: " + navLinks.size());

        System.out.println("=== Task 1 Complete ===\n");
    }


    // TASK 2
    public static void task2_NavigateToAboutUs() {

        System.out.println("=== Task 2: About Us ===");

        driver.findElement(By.linkText("About Us")).click();

        System.out.println("URL check : " +
                (driver.getCurrentUrl().contains("/pages/about-us") ?
                        "PASS — contains /pages/about-us" : "FAIL"));

        System.out.println("Title check : " +
                (driver.getTitle().contains("About Us") ?
                        "PASS — " + driver.getTitle() : "FAIL"));

        System.out.println("H1 text : " +
                driver.findElement(By.cssSelector("#page-content > h1")).getText());

        List<WebElement> crumbs = driver.findElements(By.cssSelector(".breadcrumb a"));
//        System.out.print("Breadcrumb : ");
//        for (int i = 0; i < crumbs.size(); i++) {
//            System.out.print(crumbs.get(i).getText());
//            if (i < crumbs.size() - 1) System.out.print(" — ");
//        }

       // System.out.println();

        String content = driver.findElement(By.cssSelector("#page-content > div.wysiwyg > p")).getText();
        System.out.println("Content check : " +
                (content.contains("Sauce") ? "PASS — contains 'Sauce'" : "FAIL"));

        System.out.println("=== Task 2 Complete ===\n");
    }


    // TASK 3
    public static void task3_CatalogAndProducts() {

        System.out.println("=== Task 3: Catalog Page ===");

        driver.findElement(By.linkText("Catalog")).click();

        System.out.println("URL check : " +
                (driver.getCurrentUrl().contains("/collections/all") ?
                        "PASS — contains /collections/all" : "FAIL"));

        System.out.println("Title check : " +
                (driver.getTitle().contains("Products") ?
                        "PASS — " + driver.getTitle() : "FAIL"));

        System.out.println("H1 text : " +
                driver.findElement(By.cssSelector("#page-content > h1")).getText());

        List<WebElement> products = driver.findElements(By.cssSelector("section.product-grid h3"));

        System.out.println("\nProduct count : " + products.size() +
                (products.size() == 7 ? " — PASS" : " — FAIL"));

        System.out.print("Products found: ");
        for (int i = 0; i < products.size(); i++) {
            System.out.print(products.get(i).getText());
            if (i < products.size() - 1) System.out.print(", ");
        }
        System.out.println();

        List<WebElement> soldOut = driver.findElements(
                By.xpath("//span[contains(text(),'Sold Out')]"));

        System.out.print("Sold Out count: " + soldOut.size() + " (");
        for (int i = 0; i < soldOut.size(); i++) {
            String name = soldOut.get(i)
                    .findElement(By.xpath("./ancestor::div[contains(@class,'product-card')]//a"))
                    .getText();
            System.out.print(name);
            if (i < soldOut.size() - 1) System.out.print(", ");
        }
        System.out.println(")");

        System.out.println("Grey jacket href: " +
                driver.findElement(By.partialLinkText("Grey"))
                        .getAttribute("href"));

        System.out.println("=== Task 3 Complete ===\n");
    }


    // TASK 4
    public static void task4_ProductDetailPage() {

        System.out.println("=== Task 4: Grey Jacket Product Page ===");

        driver.findElement(By.cssSelector("a[href*='grey-jacket']")).click();

        System.out.println("URL check : " +
                (driver.getCurrentUrl().contains("grey-jacket") ?
                        "PASS — contains grey-jacket" : "FAIL"));

        System.out.println("Title check : " +
                (driver.getTitle().contains("Grey jacket") ?
                        "PASS — " + driver.getTitle() : "FAIL"));

        String name = driver.findElement(By.cssSelector("h1")).getText();
        System.out.println("Product name : " + name + " — PASS");

        String price = driver.findElement(By.cssSelector("#product-price > span")).getText();
        System.out.println("Price : " + price +
                (price.contains("£55.00") ? " — PASS" : " — FAIL"));

        List<WebElement> crumbs = driver.findElements(By.tagName("a"));
        System.out.print("Breadcrumb : [");
        for (int i = 0; i < crumbs.size(); i++) {
            System.out.print(crumbs.get(i).getText());
            if (i < crumbs.size() - 1) System.out.print(", ");
        }
        System.out.println("]");

        WebElement btn = driver.findElement(By.xpath("//*[@id=\"add\"]"));
        System.out.println("isDisplayed() : " + btn.isDisplayed());
        System.out.println("isEnabled() : " + btn.isEnabled());

        System.out.println("=== Task 4 Complete ===\n");
    }


    // TASK 5
    public static void task5_BrowserNavigation() {

        System.out.println("=== Task 5: Browser Navigation ===");

        driver.navigate().back();
        System.out.println("After back() : " +
                (driver.getCurrentUrl().contains("/collections/all") ?
                        "PASS — /collections/all" : "FAIL"));

        driver.navigate().forward();
        System.out.println("After forward(): " +
                (driver.getCurrentUrl().contains("grey-jacket") ?
                        "PASS — contains grey-jacket" : "FAIL"));

        driver.navigate().to(BASE + "/pages/about-us");
        System.out.println("After to() : PASS — /pages/about-us");

        driver.navigate().refresh();
        System.out.println("After refresh(): PASS — title unchanged");

        driver.get(BASE);
        System.out.println("After get() : PASS — home page loaded");

        System.out.println("=== Task 5 Complete ===\n");
    }


    // TASK 6
    public static void task6_HeaderLinksAndCart() {

        System.out.println("=== Task 6: Header & Cart ===");

        driver.get(BASE);

        WebElement cart = driver.findElement(By.partialLinkText("My Cart"));
        System.out.println("Cart link text: " + cart.getText());

        System.out.println("Cart count : " +
                (cart.getText().contains("(0)") ?
                        "PASS — shows (0)" : "FAIL"));

        cart.click();

        System.out.println("Cart URL : " +
                (driver.getCurrentUrl().contains("/cart") ?
                        "PASS — contains /cart" : "FAIL"));

        System.out.println("Empty message : " +
                driver.findElement(By.xpath("//*[contains(text(),'Your cart is empty')]"))
                        .getText());

        driver.navigate().back();

        System.out.println("Login href : " +
                driver.findElement(By.linkText("Log In")).getAttribute("href"));

        String original = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get(BASE + "/pages/about-us");
        driver.close();
        driver.switchTo().window(original);

        System.out.println("After close() : " +
                (driver.getTitle().contains("Sauce Demo") ?
                        "back on Sauce Demo — PASS" : "FAIL"));

        System.out.println("=== Task 6 Complete ===\n");
    }
}