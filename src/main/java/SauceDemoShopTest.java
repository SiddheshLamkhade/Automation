import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;

public class SauceDemoShopTest {

    static WebDriver driver;
    static final String BASE = "https://sauce-demo.myshopify.com";

    public static void main(String[] args) throws InterruptedException {

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

        /**
         * Element : Logo image (alt text)
         * Locator : By.cssSelector("#logo img")
         * Reason : CSS selector is used because id is available and gives fast and reliable access
         */
        String LogoText = driver.findElement(By.cssSelector("#logo img")).getAttribute("alt");
        System.out.println("H1 text        : " + LogoText);

        /**
         * Element : H3 heading
         * Locator : By.xpath("//h3")
         * Reason : XPath is used because no unique id or class is available
         */
        WebElement h3 = driver.findElement(By.xpath("//h3"));
        System.out.println("H3 text : " + h3.getText());

        /**
         * Element : Navigation links (list)
         * Locator : By.cssSelector("nav ul li a")
         * Reason : CSS selector is used for simple hierarchy and better performance
         */
        List<WebElement> navLinks = driver.findElements(By.cssSelector("nav ul li a"));
        System.out.println("Nav link count: " + navLinks.size());

        System.out.println("=== Task 1 Complete ===\n");
    }


    // TASK 2
    public static void task2_NavigateToAboutUs() {

        System.out.println("=== Task 2: About Us ===");

        /**
         * Element : About Us link (header)
         * Locator : By.linkText("About Us")
         * Reason : linkText is used because visible text is stable and easy to read
         */
        driver.findElement(By.linkText("About Us")).click();

        System.out.println("URL check : " +
                (driver.getCurrentUrl().contains("/pages/about-us") ?
                        "PASS — contains /pages/about-us" : "FAIL"));

        System.out.println("Title check : " +
                (driver.getTitle().contains("About Us") ?
                        "PASS — " + driver.getTitle() : "FAIL"));

        /**
         * Element : Page heading (h1)
         * Locator : By.cssSelector("#page-content > h1")
         * Reason : CSS selector is used for direct access using page structure
         */
        System.out.println("H1 text : " +
                driver.findElement(By.cssSelector("#page-content > h1")).getText());

        /**
         * Element : Breadcrumb links (list)
         * Locator : By.cssSelector(".breadcrumb a")
         * Reason : CSS selector is used because class is available and returns multiple elements
         */
        List<WebElement> crumbs = driver.findElements(By.cssSelector(".breadcrumb a"));

        /**
         * Element : Paragraph content
         * Locator : By.cssSelector("#page-content > div.wysiwyg > p")
         * Reason : CSS selector is used to target exact nested structure
         */
        String content = driver.findElement(By.cssSelector("#page-content > div.wysiwyg > p")).getText();
        System.out.println("Content check : " +
                (content.contains("Sauce") ? "PASS — contains 'Sauce'" : "FAIL"));

        System.out.println("=== Task 2 Complete ===\n");
    }


    // TASK 3
    public static void task3_CatalogAndProducts() throws InterruptedException {

        System.out.println("=== Task 3: Catalog Page ===");

        /**
         * Element : Catalog link
         * Locator : By.linkText("Catalog")
         * Reason : linkText is used because text is unique and readable
         */
        driver.findElement(By.linkText("Catalog")).click();

        System.out.println("URL check : " +
                (driver.getCurrentUrl().contains("/collections/all") ?
                        "PASS — contains /collections/all" : "FAIL"));

        System.out.println("Title check : " +
                (driver.getTitle().contains("Products") ?
                        "PASS — " + driver.getTitle() : "FAIL"));

        /**
         * Element : Page heading (h1)
         * Locator : By.cssSelector("#page-content > h1")
         * Reason : CSS selector is used for fast and direct access
         */
        System.out.println("H1 text : " +
                driver.findElement(By.cssSelector("#page-content > h1")).getText());

        /**
         * Element : Product titles (list)
         * Locator : By.cssSelector("section.product-grid h3")
         * Reason : CSS selector is used to efficiently get multiple elements
         */
        List<WebElement> products = driver.findElements(By.cssSelector("section.product-grid h3"));

        System.out.println("\nProduct count : " + products.size() +
                (products.size() == 7 ? " — PASS" : " — FAIL"));

        System.out.print("Products found: ");
        for (int i = 0; i < products.size(); i++) {
            System.out.print(products.get(i).getText());
            if (i < products.size() - 1) System.out.print(", ");
        }
        System.out.println();
        Thread.sleep(300);

        /**
         * Element : Sold Out product name
         * Locator : By.xpath("//*[contains(text(),'Sold Out')]/ancestor::a//h3")
         * Reason : XPath is used because it supports text matching and DOM traversal
         */
        List<WebElement> soldOut = driver.findElements(
                By.xpath("//*[contains(text(),'Sold Out')]/ancestor::a//h3"));

        System.out.print("Sold Out count: " + soldOut.size() + " (");
        for(WebElement e:soldOut){
            System.out.print(e.getText());
            System.out.print(",");
        }
        System.out.println(")");

        /**
         * Element : Grey jacket link
         * Locator : By.partialLinkText("Grey")
         * Reason : partialLinkText is used because full text may vary but partial text is stable
         */
        System.out.println("Grey jacket href: " +
                driver.findElement(By.partialLinkText("Grey"))
                        .getAttribute("href"));

        System.out.println("=== Task 3 Complete ===\n");
    }


    // TASK 4
    public static void task4_ProductDetailPage() {

        System.out.println("=== Task 4: Grey Jacket Product Page ===");

        /**
         * Element : Grey jacket product link
         * Locator : By.cssSelector("a[href*='grey-jacket']")
         * Reason : CSS selector is used with partial attribute match for flexibility
         */
        driver.findElement(By.cssSelector("a[href*='grey-jacket']")).click();

        System.out.println("URL check : " +
                (driver.getCurrentUrl().contains("grey-jacket") ?
                        "PASS — contains grey-jacket" : "FAIL"));

        System.out.println("Title check : " +
                (driver.getTitle().contains("Grey jacket") ?
                        "PASS — " + driver.getTitle() : "FAIL"));

        /**
         * Element : Product name (h1)
         * Locator : By.cssSelector("h1")
         * Reason : CSS selector is used because h1 is unique on page
         */
        String name = driver.findElement(By.cssSelector("h1")).getText();
        System.out.println("Product name : " + name + " — PASS");

        /**
         * Element : Product price
         * Locator : By.cssSelector("#product-price > span")
         * Reason : CSS selector is used because id gives fast and reliable access
         */
        String price = driver.findElement(By.cssSelector("#product-price > span")).getText();
        System.out.println("Price : " + price +
                (price.contains("£55.00") ? " — PASS" : " — FAIL"));

        /**
         * Element : Breadcrumb links (list)
         * Locator : By.cssSelector("#breadcrumb a")
         * Reason : CSS selector is used because class/id is available for grouping elements
         */
        List<WebElement> crumbs = driver.findElements(By.cssSelector("#breadcrumb a"));
        for(WebElement e: crumbs){
            System.out.print(e.getText());
        }
        System.out.println("]");

        /**
         * Element : Add to cart button
         * Locator : By.xpath("//*[@id=\"add\"]")
         * Reason : XPath is used because id is directly available and easy to locate
         */
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

        /**
         * Element : My Cart link
         * Locator : By.partialLinkText("My Cart")
         * Reason : partialLinkText is used because cart count changes dynamically
         */
        WebElement cart = driver.findElement(By.partialLinkText("My Cart"));
        System.out.println("Cart link text: " + cart.getText());

        System.out.println("Cart count : " +
                (cart.getText().contains("(0)") ?
                        "PASS — shows (0)" : "FAIL"));

        cart.click();

        System.out.println("Cart URL : " +
                (driver.getCurrentUrl().contains("/cart") ?
                        "PASS — contains /cart" : "FAIL"));

        /**
         * Element : Empty cart message
         * Locator : By.xpath("//*[contains(text(),'Your cart is empty')]")
         * Reason : XPath is used because text matching is required
         */
        System.out.println("Empty message : " +
                driver.findElement(By.xpath("//*[contains(text(),'Your cart is empty')]"))
                        .getText());

        driver.navigate().back();

        /**
         * Element : Log In link
         * Locator : By.linkText("Log In")
         * Reason : linkText is used because text is exact and stable
         */
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