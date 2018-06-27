package newpackage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class etsy
{
	public static void main(String[] args)
	{
		// System.setProperty("webdriver.firefox.marionette","C:\\Automation Testing\\geckodriver-v0.19.1-win64\\geckodriver.exe");
			
		WebDriver driver = new FirefoxDriver();
		
		String baseUrl = "https://www.etsy.com/";
		
		driver.get(baseUrl);
				
		WebDriverWait wait = new WebDriverWait(driver,20);
		WebElement element = driver.findElement(By.xpath("//button[@class='width-full btn btn-outline btn-outline-black']"));
		wait.until(ExpectedConditions.visibilityOf(element));
		driver.findElement(By.xpath("//button[@class='width-full btn btn-outline btn-outline-black']")).click();
		
		Actions action = new Actions(driver);
	    WebElement searchDropDown = driver.findElement(By.id("search-query"));
	    action.moveToElement(searchDropDown).perform();
	    
	    searchDropDown.sendKeys("sp");
	    
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='search-suggestions']/ul/li")));
	    List<WebElement> searchOptions = driver.findElements(By.xpath("//div[@id='search-suggestions']/ul/li"));

	    System.out.println(searchOptions.size());

	    String selectorStr = "";

	    for (int i=1; i<=searchOptions.size(); i++)
	    {
	    		    	
	    	System.out.println("row is: " +i);
	        selectorStr = ".//div[@id='search-suggestions']/ul/li[" + i + "]/div/div/div";

	        String searchResult = driver.findElement(By.xpath(selectorStr)).getText();

	        System.out.println(searchResult);

	        if(searchResult.equals("sports shoes"))	
	        {
	            searchOptions.get(i-1).click();
	            driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
	            break;
	        }
	        
	        else
	        {
	        	if(i>=searchOptions.size());
	        	{
	        		System.out.println("Couldn't find search string");
	        		break;
	        	}
	        }
	    }
	    
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-secondary caret normal ']")));

		driver.findElement(By.xpath("//button[@class='btn btn-secondary caret normal ']")).click();
		driver.findElement(By.linkText("Lowest Price")).click();
		
		String priceSelector = "//div[contains(@class,'block-grid-xs-2 block-grid-xl-4 hide-lg organic-row-impression-logging block-grid-no-whitespace float-clear pb-xs-1-5')]/div/a/div[2]/div/p[2]/span[2]";
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(priceSelector)));

	    List<WebElement> PriceList = driver.findElements(By.xpath(priceSelector));
	    
	    int nPrices= 10;
	    
	    System.out.println("Sorting by Lowest Price and listing the top "+nPrices+" prices:");
	    
	    for(int i=0; i<nPrices; i++)
	    {
	        System.out.println("£"+PriceList.get(i).getText());
	    }

	}

}
