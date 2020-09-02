package API.uiapi;

import API.Pages.JiraPages;
import API.utils.PayLoadUtil;
import Utils.ConfigReader;
import Utils.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static API.utils.Constants.SESSIONID;

public class UICookieAuth {
    WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost:8080");
    }

    @Test
    public void jiraLogin(){
       driver= Driver.getDriver();
        driver.get("localhost:8080");
        JiraPages pages=new JiraPages();
        pages.userName.clear();
        pages.userName.sendKeys("dddefoul");
        pages.password.clear();
        pages.password.sendKeys(ConfigReader.getProperty("jiraPassword"));
        pages.singInButton.click();
        Assert.assertEquals("Title verification failed","System Dashboard - JIRA",driver.getTitle());
    }



    @Test
    public void jiraCookieAuth(){//FBB025F55A2DDF99474A734AC27651C6  PayLoadUtil.generateCookie()
        Cookie jsessionIdCookie=new Cookie(SESSIONID,PayLoadUtil.generateCookie());
        driver.manage().addCookie(jsessionIdCookie);
        driver.get("http://localhost:8080");
        String tec4=driver.findElement(By.xpath("//a[.='TEC-4']")).getText();
        Assert.assertEquals("Login verification failed","TEC-4",tec4);

    }
}
