package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utilities.DbManager;
import utilities.MonitoringMail;

public class TestBase {

	public static WebDriver driver;
	public static Properties OR = new Properties();
	public static Properties config = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static WebDriverWait wait;
	public static MonitoringMail mail = new MonitoringMail();
	public static WebElement dropdown;

	@BeforeSuite
	public void setUp() {

		if (driver == null) {
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties\\");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config properties loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties\\");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR properties loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (config.getProperty("browser").equals("firefox")) {

				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
				log.debug("FireFox launched");

			} else if (config.getProperty("browser").equals("chrome")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				log.debug("Chrome launched");

			} else if (config.getProperty("browser").equals("ie")) {

				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				log.debug("IE launched");

			}

			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to :" + config.getProperty("testsiteurl"));

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);

			wait = new WebDriverWait(driver, Integer.parseInt(config.getProperty("explicit.wait")));

			DbManager.setMysqlDbConnection();
			log.debug("Database connection establish");

		}

	}

	public void click(String key) {

		if (key.endsWith("_xpath")) {

			driver.findElement(By.xpath(OR.getProperty(key))).click();

		} else if (key.endsWith("_css")) {

			driver.findElement(By.cssSelector(OR.getProperty(key))).click();

		} else if (key.endsWith("_id")) {
			driver.findElement(By.id(OR.getProperty(key))).click();

		}

		log.debug("Clicking on an element :" + key);
	}

	public void type(String key, String value) {

		if (key.endsWith("_xpath")) {

			driver.findElement(By.xpath(OR.getProperty(key))).sendKeys(value);

		} else if (key.endsWith("_css")) {

			driver.findElement(By.cssSelector(OR.getProperty(key))).sendKeys(value);

		} else if (key.endsWith("_id")) {
			driver.findElement(By.id(OR.getProperty(key))).sendKeys(value);

		}

		log.debug("Clicking on an element :" + key + "Entered value is :" + value);
	}

	public void select(String key, String value) {

		if (key.endsWith("_xpath")) {

			dropdown = driver.findElement(By.xpath(OR.getProperty(key)));

		} else if (key.endsWith("_css")) {

			dropdown = driver.findElement(By.cssSelector(OR.getProperty(key)));

		} else if (key.endsWith("_id")) {

			dropdown = driver.findElement(By.id(OR.getProperty(key)));

		}

		Select select = new Select(dropdown);
		select.selectByVisibleText(value);

		log.debug("Selecting value from dropdown :" + key + "Value selected :" + value);

	}

	public static boolean isElementPresent(String key) {

		try {

			if (key.endsWith("_xpath")) {

				dropdown = driver.findElement(By.xpath(OR.getProperty(key)));

			} else if (key.endsWith("_css")) {

				dropdown = driver.findElement(By.cssSelector(OR.getProperty(key)));

			} else if (key.endsWith("_id")) {

				dropdown = driver.findElement(By.id(OR.getProperty(key)));

			}

			log.debug("Finding element with locator :" + key);
			return true;

		} catch (Throwable t) {

			log.debug("Element not found :" + key + " " + t.getMessage());
			return false;

		}

	}

	@AfterSuite
	public void tearDown() {

		driver.quit();
		log.debug("Test Execution completed !!!	");

	}

}
