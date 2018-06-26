package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FlightDetailsPageObject 
{
	@FindBy(how=How.NAME,using="type")
	public static WebElement type;
	
	

}
