package LibKeyword;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseAction {

    public static void click(WebDriver driver, By element){
        WebElement wait = new WebDriverWait(driver, Duration.ofMillis(5000))
                .until(ExpectedConditions.presenceOfElementLocated(element));
        wait.click();
    }

    public static void writeText(WebDriver driver, By element, String text){
        WebElement wait = new WebDriverWait(driver, Duration.ofMillis(5000))
                .until(ExpectedConditions.presenceOfElementLocated(element));
        wait.sendKeys(text);
    }

    public static String getText(WebDriver driver, By element){
        WebElement wait = new WebDriverWait(driver, Duration.ofMillis(5000))
                .until(ExpectedConditions.presenceOfElementLocated(element));
        return wait.getText();
    }

    public static void waitElement(WebDriver driver, By element){
        WebElement wait = new WebDriverWait(driver, Duration.ofMillis(5000))
                .until(ExpectedConditions.presenceOfElementLocated(element));
    }
}
