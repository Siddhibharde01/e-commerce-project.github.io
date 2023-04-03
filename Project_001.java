package project_01;

import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Project_001 {

	WebDriver driver;
	public static void main(String[] args) {
		Project_001 pc = new Project_001();
		pc.launchbrowser();
		pc.loginPage();
		pc.addToCart();
		pc.dropdown();
		pc.multipletab();
		//pc.handlealert();
	}
	
	public void launchbrowser() {
		System.setProperty("webdriver.chrome.driver","E:\\chromedriver111\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.close();
	}
	
	public void loginPage() {
		driver.get("https://www.saucedemo.com/");
	    WebElement username =driver.findElement(By.id("user-name"));
	    username.click();
	    username.sendKeys("standard_user");
	    WebElement pass = driver.findElement(By.id("password"));
	    pass.click();
	    pass.sendKeys("secret_sauce");
	    WebElement login = driver.findElement(By.id("login-button"));
	    login.click();
	}
	
	public void addToCart() {
		driver.get("https://www.saucedemo.com/inventory-item.html?id=0");
		WebElement cart = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
		cart.click();
		WebElement shocart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
		shocart.click();
	}
	
	public void dropdown() {
		driver.get("https://www.saucedemo.com/inventory.html");
		WebElement name = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
		name.click();
		Select sc = new Select(name);
		System.out.println(sc.getFirstSelectedOption().getText());
		List<WebElement> allopt = sc.getOptions();
		allopt.forEach(x->{System.out.println(x.getText());});
		
	}

	public void multipletab() {
		driver.get("https://www.naukri.com/");
		String tabid = driver.getWindowHandle();
		WebElement servicetab =	driver.findElement(By.xpath("//div[normalize-space()='Services']"));
		WebElement caompanytab = driver.findElement(By.xpath("//div[normalize-space()='Companies']"));
		Actions act = new Actions(driver);
		act.keyDown(Keys.CONTROL);
		
		act.keyDown(Keys.CONTROL).click(servicetab).keyUp(Keys.CONTROL).build().perform();
		act.keyDown(Keys.CONTROL).click(caompanytab).keyUp(Keys.CONTROL).build().perform();
		
	Set<String>	allids = driver.getWindowHandles();
	Iterator<String> it = allids.iterator();
	String fid = it.next();
	System.out.println(fid+"\n"+driver.getTitle());
	
	String sid = it.next();
	driver.switchTo().window(sid);
	System.out.println(sid+"\n"+driver.getTitle());
	
	String tid = it.next();
	driver.switchTo().window(tid);
	System.out.println(tid+"\n"+driver.getTitle());
	
	}
	
	public void handlealert() {
		driver.get("http://www.tizag.com/javascriptT/javascriptprompt.php");
		WebElement button = driver.findElement(By.xpath("//input[@value='Say my name!']"));
		button.click();
		Alert at = driver.switchTo().alert();
		System.out.println(at.getText());
		at.sendKeys("testdata");
		at.accept();
		System.out.println(at.getText());
		at.accept();
	}
	
}
