import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    WebDriver driver ;
    public LoginPage(WebDriver driver)
    {
        this.driver = driver ;
    }

    public WebElement UserNamePOM( )
    {
        By username = By.id("username");
        return driver.findElement(username);
    }

    public WebElement PasswordPOM( )
    {
        By password = By.name("password");
        return driver.findElement(password);
    }

    public WebElement FlashPOM( )
    {
        By flash = By.id("flash");
        return driver.findElement(flash);
    }

    public WebElement LogoutPOM( )
    {
        By logout = By.cssSelector("a[href=\"/logout\"]");
        return driver.findElement(logout);
    }

    public void LoginSteps( String username ,String password)
    {
        //Enter username using POM
        UserNamePOM( ).clear();
        UserNamePOM( ).sendKeys(username);

        //Enter password using POM
        PasswordPOM( ).sendKeys(password);
        PasswordPOM( ).sendKeys(Keys.ENTER);
    }
}
