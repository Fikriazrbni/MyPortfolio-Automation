package LibKeyword;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

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
        new WebDriverWait(driver, Duration.ofMillis(5000))
                .until(ExpectedConditions.presenceOfElementLocated(element));
    }

    //LibUtilz
    public static String generateRandomString(int len){
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder builder = new StringBuilder();
        Random rd = new Random();
        for (int x=0; x<len; x++){
            builder.append(alpha.charAt(rd.nextInt(alpha.length())));
        }
        return String.valueOf(builder);
    }
    public static String generateRandomNum(int len){
        StringBuilder builder = new StringBuilder();
        Random rd = new Random();
        for (int i = 1; i<=len; i++){
            int n = rd.nextInt(9);
            builder.append(n);
        }
        return String.valueOf(builder);
    }
    public static String generateDateTime(String pattern){
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = new Date();
        return format.format(date);
    }

    public static void main(String[] args) {
        System.out.println(generateRandomNum(5));
        System.out.println(generateDateTime("mmss"));
        System.out.println(generateRandomString(5));
    }
}
