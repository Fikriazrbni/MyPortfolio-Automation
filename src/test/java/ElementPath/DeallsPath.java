package ElementPath;

import org.openqa.selenium.By;

public class DeallsPath {

    public static By menuLogin = By.xpath("//a[text() = 'Login']");
    public static By fieldEmail = By.id("basic_email");
    public static By fieldPassword = By.id("basic_password");
    public static By btnSignIn = By.xpath("//button/span[text()= 'Sign In']");
    public static By dropProfile = By.id("dropdown-area");
    public static By btnMyProfile = By.xpath("//span[text()= 'My Profile']");
    public static By btnLogout = By.xpath("//span[text()= 'Logout']");
    public static By dtlProfile_name = By.xpath("//*[@id='__next']/div[3]/div[1]/div[1]/div/div/div/div[2]/div[1]/div/span");
    public static By modalWrongEmail = By.xpath("/html/body/div[2]/div/div/div/div/div/span[2]/div/div[1]");

}
