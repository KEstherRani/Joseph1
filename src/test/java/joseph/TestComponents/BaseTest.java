package joseph.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import joseph.PageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage LP;

	public WebDriver Initializedriver() throws IOException {
		System.out.println("{{{{{{{{{{{{{{{}}}}}}}}}}");
		Properties prop = new Properties();
		FileInputStream FIS = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\joseph\\Resources\\GlobalData.properties");
		prop.load(FIS);
		
		String browserName = System.getProperty("browser")!=null? System.getProperty("browser"): prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");
		System.out.println("------"+System.getProperty("browser"));
		WebDriver driver1 = null;
		if (browserName.contains("Chrome")) {
			//for headless
			ChromeOptions options=new ChromeOptions();
			if(browserName.contains("Headless"))
			{
				System.out.println("&&&&&&&&&&&&&&&&&&");
			options.addArguments("headless");
			}
			driver1 = new ChromeDriver(options);
			driver1.manage().window().setSize(new Dimension(1440, 900));
			
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			driver1 = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("Edge")) {
			driver1 = new EdgeDriver();
		}

		driver1.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver1.manage().window().maximize();
		return driver1;
	}
	
	public List<HashMap<String, String>> getJsonDatatoHashmap(String JsonFilepath) throws IOException{
		//Read json string
	String jsonContent=	FileUtils.readFileToString(new File(JsonFilepath) ,StandardCharsets.UTF_8);
	
	//Convert String to HashMap-- Jackson Databind
	ObjectMapper mapper=new ObjectMapper();
	List<HashMap<String, String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
	
	//data={map,map}
	return data;
	
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts= (TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File destfile=new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png");
		FileUtils.copyFile(src, destfile);
		return System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
	}

	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {

		System.out.println(";;;;;;;;;;;;;;;;;;;;");
		this.driver = Initializedriver();
		LP = new LandingPage(driver);
		LP.goTo();
		return LP;

	}
	@AfterMethod(alwaysRun=true)
	public void tearDown(){
		driver.close();
	}

}
