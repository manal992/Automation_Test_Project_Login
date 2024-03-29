import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OpenBrowserSoft {

    WebDriver driver = null;
    LoginPage login ;

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

        //4- Create new object
        login = new LoginPage(driver);
    }

    @Test
    public void ValidData() throws InterruptedException {
        //4- Enter valid username and password (positive)
        driver.navigate().to("https://the-internet.herokuapp.com/login");

        login.LoginSteps( "tomsmith","SuperSecretPassword!");

        Thread.sleep(3000);

        String expected_result = "You logged into a secure area!";
        String actual_result =login.FlashPOM( ).getText();

        //** Soft Assertion

        SoftAssert soft = new SoftAssert();

        //First assert
        System.out.println("First assert");
        soft.assertTrue(actual_result.contains(expected_result),"First assert");

        //Second assert
        System.out.println("Second assert");
        soft.assertTrue(login.LogoutPOM( ).isDisplayed(),"Second assert");

        //Third assert
        System.out.println("Third assert");
        soft.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/secure","Third assert");

        // Assert all
        soft.assertAll();

    }

    @Test
    public void inValidData()
    {
        // Enter inValid username and password (negative)
        driver.navigate().to("https://the-internet.herokuapp.com/login");

        login.LoginSteps( "manal","m1234c");

        String expected_result = "Your username is invalid!";
        String actual_result =login.FlashPOM( ).getText();

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
