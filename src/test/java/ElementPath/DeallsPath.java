package ElementPath;

import org.openqa.selenium.By;

public class DeallsPath {

    public static By menuLogin = By.id("dealls-navbar-login-btn");
    public static By fieldEmail = By.id("basic_email");
    public static By fieldPassword = By.id("basic_password");
    public static By btnSignIn = By.xpath("//button/span[text()= 'Sign In']");
    public static By dropProfile = By.id("dropdown-area");
    public static By btnMyProfile = By.xpath("//span[text()= 'Profil Saya']");
    public static By btnLogout = By.xpath("//span[text()= 'Logout']");
    public static By dtlProfile_name = By.xpath("//span[text() = 'Fikri Azhari Rabbani']");
    public static By modalWrongEmail = By.xpath("//div[contains(text(), 'Email Not found')]");
}
