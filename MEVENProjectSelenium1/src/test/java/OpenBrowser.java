import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class OpenBrowser {

    WebDriver driver = null;

    @BeforeTest  //Annotation
    public void OpenChrome() throws InterruptedException {
        //1- Bridge between test script and browsers
        String ChromePath = System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",ChromePath);

        //2- New Object Of WebDriver
        driver = new ChromeDriver();

        //3- Navigate To Google Website and maximize screen and sleep
      //  driver.navigate().to("https://the-internet.herokuapp.com/login");
        driver.manage().window().maximize();
        Thread.sleep(3000);
    }

    @Test
    public void ValidData() throws InterruptedException {
        //4- Enter valid username and password (positive)
        driver.navigate().to("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.name("password")).sendKeys(Keys.ENTER);

        Thread.sleep(3000);

        String expected_result = "You logged into a secure area!";
        String actual_result = driver.findElement(By.id("flash")).getText();

        //** Hard Assertion

        //First assert
        System.out.println("First assert");
        Assert.assertTrue(actual_result.contains(expected_result));

        //Second assert
        System.out.println("Second assert");
        Assert.assertTrue(driver.findElement(By.cssSelector("a[href=\"/logout\"]")).isDisplayed());

        //Third assert
        System.out.println("Third assert");
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/secure");

    }

    @Test
    public void inValidData()
    {
        // Enter inValid username and password (negative)
        driver.navigate().to("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("inValid");
        driver.findElement(By.name("password")).sendKeys("Super");
        driver.findElement(By.name("password")).sendKeys(Keys.ENTER);

        String expected_result = "Your username is invalid!";
        String actual_result = driver.findElement(By.id("flash")).getText();

        Assert.assertTrue(actual_result.contains(expected_result));
    }

    @AfterTest
    public void CloseDriver()
    {
        //7- Close Driver --> close all taps
        driver.quit();
    }


}










//        //5- Click on login button
//        driver.findElement(By.className("radius")).click();
//        driver.findElement(By.cssSelector("form > button[type=\"submit\"]")).click();
//        driver.findElement(By.xpath("//form[@id=\"login\"]//button[@type=\"submit\"]")).click();

//        //6- Get success message
//        Thread.sleep(2000);
//        String success_message = driver.findElement(By.id("flash")).getText();
//        System.out.println("success_message : " + success_message);
//
//        String success_class =driver.findElement(By.id("flash")).getAttribute("class");
//        System.out.println("success_class : " + success_class);
//
//        String background_color =driver.findElement(By.id("flash")).getCssValue("background-color");
//        System.out.println("background_color : " + background_color);
//
//        String color =driver.findElement(By.id("flash")).getCssValue("color");
//        System.out.println("color : " + color);
