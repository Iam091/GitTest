package testcases;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumTesting {

	public static AndroidDriver driver;

	public static void main(String[] args) throws MalformedURLException {

		// code for starting a server programatically

		AppiumDriverLocalService service = AppiumDriverLocalService.buildService(
				new AppiumServiceBuilder().usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
						.withAppiumJS(new File(
								"C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
						.withLogFile(new File("D:\\Javaworkspace\\MobileTesting\\src\\test\\resources\\Logs\\Logs.txt"))
						.withArgument(GeneralServerFlag.LOCAL_TIMEZONE));

		service.start();
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);

		driver.get("http://google.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//input[@aria-label='Search']")).sendKeys("Hello Appium!!!");
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			 // now copy the  screenshot to desired location using copyFile //method
			FileUtils.copyFile(scrFile, new File("D:/narcos/error.png"));
			}
			 
			catch (IOException e)
			 {
			  System.out.println(e.getMessage());
			 
			 }
		// driver.close();

	}

}
