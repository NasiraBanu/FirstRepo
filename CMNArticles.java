package com.parallelexecution;
import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CMNArticles {
	//String[] HC = {"Behavioral & Mental Health", "Current Medical News"};
	
	WebDriver driver1;
	WebDriver driver2;
	WebDriver driver3;
	WebDriver driver4;
	WebDriver driver5;
	
	String Title;
	String Description;
	String Keywords;
	String Content;
	String References;
	String Materials;
	Date now = new Date();
	//@Parameters("browser")
	@Test	
	public void cmnTest1() throws Exception
		{	
		try{
				
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Krishna\\Desktop\\Selenium\\chromedriver_win32\\chromedriver.exe");
		driver1 = new ChromeDriver();
		driver1.manage().window().maximize();
		driver1.get("https://www.dovemed.com/admin/");
		// Login Wagtail Application
		WebElement Username = driver1.findElement(By.xpath("//*[@name='username']"));
		Username.sendKeys("Nasira");
		WebElement Password = driver1.findElement(By.xpath("//*[@name='password']"));
		Password.sendKeys("Bnasira12");		
		driver1.findElement(By.xpath("//em[text()='Sign in']")).click();		
		Thread.sleep(2000);
		

		// Sele
					WebElement PageSelect = driver1.findElement(By.xpath("//*[@class=' icon icon-folder-open-inverse icon-arrow-right-after']"));
					PageSelect.click();
					Thread.sleep(3000);
					WebElement HomeRightArrow = driver1.findElement(By.xpath("//a[@class='c-explorer__item__action ']/span/span[1]"));
					if(HomeRightArrow.isDisplayed())
					{
						System.out.println("HomeRightArrow is present");
					}
					else
					{
						System.out.println("HomeRightArrow is not present");
					}

					HomeRightArrow.click();
					Thread.sleep(3000);
					WebElement CMN = driver1.findElement(By.xpath("(//h3[@class='c-explorer__item__title'])[4]"));
					CMN.click();

					// Click Add child page

					Thread.sleep(3000);
					WebElement ChildPage = driver1.findElement(By.xpath("//a[@class='bicolor button button-small icon icon-plus white']"));
					ChildPage.click();
					WebElement AddPage = driver1.findElement(By.xpath("(//a[@class='icon icon-plus-inverse icon-larger'])[3]"));
					Thread.sleep(2000);
					AddPage.click();					
					Thread.sleep(2000);
					
					// Read the data from Excel
					
					System.out.println("Read Excel Data");
					
					File src = new File ("C:\\Users\\Krishna\\Desktop\\CMNArticle.xlsx");
					System.out.println("Excel Loaded");
					FileInputStream fis;
					fis = new FileInputStream(src);
					@SuppressWarnings("resource")
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					XSSFSheet Sheet1 = wb.getSheetAt(0);
					Thread.sleep(2000);
					String strTitle = Sheet1.getRow(1).getCell(0).getStringCellValue();           
					//System.out.println(strTitle);
					String strDescription = Sheet1.getRow(1).getCell(1).getStringCellValue();
					//System.out.println(strDescription);
					String strKeyword = Sheet1.getRow(1).getCell(2).getStringCellValue();
					//System.out.println(strKeyword);
					String strContent = Sheet1.getRow(1).getCell(3).getStringCellValue();
					//System.out.println(strContent);
					String strReferences = Sheet1.getRow(1).getCell(4).getStringCellValue();
					//System.out.println(strReferences);
					String strMaterial = Sheet1.getRow(1).getCell(5).getStringCellValue();
					//System.out.println(strMaterial);
					String strimgDescripe = Sheet1.getRow(1).getCell(6).getStringCellValue();
					//System.out.println(strimgDescripe);	
					Thread.sleep(1000);
	                //Enter the details in CMN WebPage
					driver1.findElement(By.name("title")).sendKeys(strTitle);
					Thread.sleep(1000);
					driver1.findElement(By.xpath("//div[@class='fr-element fr-view']")).sendKeys(strDescription);
					Thread.sleep(1000);
					
					// Click on Press Release
					
					((JavascriptExecutor) driver1).executeScript("arguments[0].scrollIntoView(true);", driver1.findElement(By.xpath("//input[@name='press_release']")));
					driver1.findElement(By.xpath("//input[@name='press_release']")).click();
					Thread.sleep(2000);
					driver1.findElement(By.xpath("//input[@id='id_keywords']")).sendKeys(strKeyword);
					Thread.sleep(2000);
					driver1.findElement(By.xpath("(//div[@class='fr-element fr-view'])[2]")).sendKeys(strContent);
					Thread.sleep(2000);
					driver1.findElement(By.xpath("(//div[@class='fr-element fr-view'])[3]")).sendKeys(strReferences);;
					Thread.sleep(2000);
					driver1.findElement(By.xpath("(//div[@class='fr-element fr-view'])[4]")).sendKeys(strMaterial);;
					Thread.sleep(1000);
					
					//Select the topic as Current Medical News
					
					new Select(driver1.findElement(By.xpath("//select[@id='id_topic']"))).selectByVisibleText("Current Medical News");
					Thread.sleep(3000);
					
					//Add Image
					
					driver1.findElement(By.xpath("//a[@id='id_carousel_items-ADD']")).click();
					Thread.sleep(4000);
					driver1.findElement(By.xpath("//button[text()='Choose an image']")).click();
					Thread.sleep(4000);
					driver1.findElement(By.xpath("//input[@id='id_q']")).sendKeys("Medical News");
					Thread.sleep(4000);
					new Select(driver1.findElement(By.xpath("//select[@id='collection_chooser_collection_id']"))).selectByVisibleText("DoveMed - Current Medical News images");
					Thread.sleep(4000);
					
					//SimpleDateFormat smf = new SimpleDateFormat("Monday");
					//System.out.println(smf.format(now));
					
					//WebElement monday = driver1.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_08.max-165x165.png']"));
					//monday.click();
					
					//driver1.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_07.max-165x165.png']")).click();
					//driver1.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_05.max-165x165.png']")).click();
					//driver1.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_03.max-165x165.png']")).click();
					//driver1.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_06.max-165x165.png']")).click();
				//	driver1.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_04.max-165x165.png']")).click();
					//driver1.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_02.max-165x165.png']")).click();
					//driver1.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_01.max-165x165.png']")).click();
					//Thread.sleep(3000);
					
					
					
					String[] days = new String[]{"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		            Calendar c = Calendar.getInstance();		           
		            String Today = days[c.get(Calendar.DAY_OF_WEEK)-1];
		            System.out.println(Today);
		            if(Today.equalsIgnoreCase("Sunday"))
		            {
		            	driver1.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_08.max-165x165.png']")).click();						
		            }
		            if(Today.equalsIgnoreCase("Monday"))
		            {          	
		            	driver1.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_07.max-165x165.png']")).click();
		            }
		            if(Today.equalsIgnoreCase("Tuesday"))
		            {
		            	driver1.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_05.max-165x165.png']")).click();
		            }
		            if(Today.equalsIgnoreCase("Wednesday"))
		            {
		            	driver1.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_03.max-165x165.png']")).click();
		            }
		            if(Today.equalsIgnoreCase("Thursday"))
		            {
		            	driver1.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_06.max-165x165.png']")).click();
		            }
		            if(Today.equalsIgnoreCase("Friday"))
		            {
		            	driver1.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_04.max-165x165.png']")).click();
		            }
		            if(Today.equalsIgnoreCase("Saturday"))
		            {
		            	driver1.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_02.max-165x165.png']")).click();
		            }
		            
		          /* {
		            	driver1.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_01.max-165x165.png']")).click();
		            	Thread.sleep(3000);
		           }*/
		            	
					//Add Image Description	
		            Thread.sleep(3000);	
					driver1.findElement(By.xpath("//input[@name='carousel_items-0-description']")).sendKeys(strimgDescripe);
					Thread.sleep(2000);
					
                   //Add Health Centers	
					String[] strHC = Sheet1.getRow(1).getCell(7).getStringCellValue().split(",");
					String[] strHealthCentre = Sheet1.getRow(1).getCell(7).getStringCellValue().split(",");
					for(int i = 0; i<strHealthCentre.length;i++){
					System.out.println(strHealthCentre[i]);	
					//for(int j=0; j<strHC.length;j++){
						//System.out.println(strHC[j]);
													
					if(strHealthCentre[i].equalsIgnoreCase(strHC[i])){
						System.out.println("if condition completed");
										
					driver1.findElement(By.xpath("(//a[@class='button bicolor icon icon-plus'])[5]")).click();
					Thread.sleep(4000);
					((JavascriptExecutor) driver1).executeScript("arguments[0].scrollIntoView(true);", driver1.findElement(By.xpath("//button[text()='Choose a page (Center Page)']")));
					driver1.findElement(By.xpath("//button[text()='Choose a page (Center Page)']")).click();
					Thread.sleep(4000);
					driver1.findElement(By.xpath("//input[@id='id_q']")).sendKeys(strHealthCentre[i]);
					Thread.sleep(2000);
					driver1.findElement(By.xpath("//a[@class='choose-page']")).click();
					Thread.sleep(2000);
					}
					}
		            
					
         //click on publish 
		driver1.findElement(By.xpath("//div[@class='dropdown-toggle icon icon-arrow-up']")).click();
		Thread.sleep(3000);
		((JavascriptExecutor) driver1).executeScript("arguments[0].scrollIntoView(true);", driver1.findElement(By.xpath("//em[text()='Publish']")));
		System.out.println("focused");
		driver1.findElement(By.xpath("//em[text()='Publish']")).click();	
		Thread.sleep(3000);
		// click on View Live
		driver1.findElement(By.xpath("(//a[@class='button button-small button-secondary'])[1]")).click();
		System.out.println("View Button Clicked");
		Thread.sleep(3000);
		
		// Close the browser
		
		driver1.close();
					
		          //  }	
}catch(Exception e){
e.printStackTrace();
System.out.println(e);
}
		
		}
	
	@Test	
	public void cmnTest2() throws Exception
		{	
		try{
				
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Krishna\\Desktop\\Selenium\\chromedriver_win32\\chromedriver.exe");
		driver2 = new ChromeDriver();
		driver2.manage().window().maximize();
		driver2.get("https://www.dovemed.com/admin/");
		// Login Wagtail Application
		WebElement Username = driver2.findElement(By.xpath("//*[@name='username']"));
		Username.sendKeys("Nasira");
		WebElement Password = driver2.findElement(By.xpath("//*[@name='password']"));
		Password.sendKeys("Bnasira12");		
		driver2.findElement(By.xpath("//em[text()='Sign in']")).click();		
		Thread.sleep(2000);
		

		// Sele
					WebElement PageSelect = driver2.findElement(By.xpath("//*[@class=' icon icon-folder-open-inverse icon-arrow-right-after']"));
					PageSelect.click();
					Thread.sleep(3000);
					WebElement HomeRightArrow = driver2.findElement(By.xpath("//a[@class='c-explorer__item__action ']/span/span[1]"));
					if(HomeRightArrow.isDisplayed())
					{
						System.out.println("HomeRightArrow is present");
					}
					else
					{
						System.out.println("HomeRightArrow is not present");
					}

					HomeRightArrow.click();
					Thread.sleep(3000);
					WebElement CMN = driver2.findElement(By.xpath("(//h3[@class='c-explorer__item__title'])[4]"));
					CMN.click();

					// Click Add child page

					Thread.sleep(3000);
					WebElement ChildPage = driver2.findElement(By.xpath("//a[@class='bicolor button button-small icon icon-plus white']"));
					ChildPage.click();
					WebElement AddPage = driver2.findElement(By.xpath("(//a[@class='icon icon-plus-inverse icon-larger'])[3]"));
					Thread.sleep(3000);
					AddPage.click();					
					Thread.sleep(2000);
					
					// Read the data from Excel
					
					System.out.println("Read Excel Data");
					
					File src = new File ("C:\\Users\\Krishna\\Desktop\\CMNArticle.xlsx");
					System.out.println("Excel Loaded");
					FileInputStream fis;
					fis = new FileInputStream(src);
					@SuppressWarnings("resource")
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					XSSFSheet Sheet1 = wb.getSheetAt(0);
					Thread.sleep(2000);
					String strTitle = Sheet1.getRow(2).getCell(0).getStringCellValue();           
					//System.out.println(strTitle);
					String strDescription = Sheet1.getRow(2).getCell(1).getStringCellValue();
					//System.out.println(strDescription);
					String strKeyword = Sheet1.getRow(2).getCell(2).getStringCellValue();
					//System.out.println(strKeyword);
					String strContent = Sheet1.getRow(2).getCell(3).getStringCellValue();
					//System.out.println(strContent);
					String strReferences = Sheet1.getRow(2).getCell(4).getStringCellValue();
					//System.out.println(strReferences);
					String strMaterial = Sheet1.getRow(2).getCell(5).getStringCellValue();
					//System.out.println(strMaterial);
					String strimgDescripe = Sheet1.getRow(2).getCell(6).getStringCellValue();
					//System.out.println(strimgDescripe);	
					Thread.sleep(1000);
	                //Enter the details in CMN WebPage
					driver2.findElement(By.name("title")).sendKeys(strTitle);
					Thread.sleep(2000);
					driver2.findElement(By.xpath("//div[@class='fr-element fr-view']")).sendKeys(strDescription);
					Thread.sleep(2000);
					
					// Click on Press Release
					
					((JavascriptExecutor) driver2).executeScript("arguments[0].scrollIntoView(true);", driver2.findElement(By.xpath("//input[@name='press_release']")));
					driver2.findElement(By.xpath("//input[@name='press_release']")).click();
					Thread.sleep(2000);
					driver2.findElement(By.xpath("//input[@id='id_keywords']")).sendKeys(strKeyword);
					Thread.sleep(2000);
					driver2.findElement(By.xpath("(//div[@class='fr-element fr-view'])[2]")).sendKeys(strContent);
					Thread.sleep(2000);
					driver2.findElement(By.xpath("(//div[@class='fr-element fr-view'])[3]")).sendKeys(strReferences);;
					Thread.sleep(2000);
					driver2.findElement(By.xpath("(//div[@class='fr-element fr-view'])[4]")).sendKeys(strMaterial);;
					Thread.sleep(2000);
					
					//Select the topic as Current Medical News
					
					new Select(driver2.findElement(By.xpath("//select[@id='id_topic']"))).selectByVisibleText("Current Medical News");
					Thread.sleep(3000);
					
					//Add Image
					
					driver2.findElement(By.xpath("//a[@id='id_carousel_items-ADD']")).click();
					Thread.sleep(4000);
					driver2.findElement(By.xpath("//button[text()='Choose an image']")).click();
					Thread.sleep(4000);
					driver2.findElement(By.xpath("//input[@id='id_q']")).sendKeys("Medical News");
					Thread.sleep(3000);
					new Select(driver2.findElement(By.xpath("//select[@id='collection_chooser_collection_id']"))).selectByVisibleText("DoveMed - Current Medical News images");
					Thread.sleep(3000);
					
					
					//WebElement Monday = driver2.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_08.max-165x165.png']"));
					//Monday.click();
					
					//driver2.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_07.max-165x165.png']")).click();
					//driver2.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_05.max-165x165.png']")).click();
					//driver2.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_03.max-165x165.png']")).click();
					//driver2.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_06.max-165x165.png']")).click();
					//driver2.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_04.max-165x165.png']")).click();
					//driver2.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_02.max-165x165.png']")).click();
					//driver2.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_01.max-165x165.png']")).click();
					//Thread.sleep(3000);
					
					
					
					String[] days = new String[]{"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		            Calendar c = Calendar.getInstance();		           
		            String Today = days[c.get(Calendar.DAY_OF_WEEK)-1];
		            System.out.println(Today);
		            if(Today.equalsIgnoreCase("Sunday"))
		            {
		            	driver2.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_08.max-165x165.png']")).click();						
		            }
		            if(Today.equalsIgnoreCase("Monday"))
		            {          	
		            	driver2.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_07.max-165x165.png']")).click();
		            }
		            if(Today.equalsIgnoreCase("Tuesday"))
		            {
		            	driver2.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_05.max-165x165.png']")).click();
		            }
		            if(Today.equalsIgnoreCase("Wednesday"))
		            {
		            	driver2.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_03.max-165x165.png']")).click();
		            }
		            if(Today.equalsIgnoreCase("Thursday"))
		            {
		            	driver2.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_06.max-165x165.png']")).click();
		            }
		            if(Today.equalsIgnoreCase("Friday"))
		            {
		            	driver2.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_04.max-165x165.png']")).click();
		            }
		            if(Today.equalsIgnoreCase("Saturday"))
		            {
		            	driver2.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_02.max-165x165.png']")).click();
		            }
		            
		          /* {
		            	driver2.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_01.max-165x165.png']")).click();
		            	Thread.sleep(3000);
		           }*/
					
					//Add Image Description
		            Thread.sleep(3000);
					driver2.findElement(By.xpath("//input[@name='carousel_items-0-description']")).sendKeys(strimgDescripe);
					Thread.sleep(2000);
					
                   //Add Health Centers	
					String[] strHC = Sheet1.getRow(1).getCell(7).getStringCellValue().split(",");
					String[] strHealthCentre = Sheet1.getRow(1).getCell(7).getStringCellValue().split(",");
					for(int i = 0; i<strHealthCentre.length;i++){
					System.out.println(strHealthCentre[i]);	
					//for(int j=0; j<strHC.length;j++){
						//System.out.println(strHC[j]);
													
					if(strHealthCentre[i].equalsIgnoreCase(strHC[i])){
						System.out.println("if condition completed");
										
					driver2.findElement(By.xpath("(//a[@class='button bicolor icon icon-plus'])[5]")).click();
					Thread.sleep(5000);
					((JavascriptExecutor) driver2).executeScript("arguments[0].scrollIntoView(true);", driver2.findElement(By.xpath("//button[text()='Choose a page (Center Page)']")));
					driver2.findElement(By.xpath("//button[text()='Choose a page (Center Page)']")).click();
					Thread.sleep(4000);
					driver2.findElement(By.xpath("//input[@id='id_q']")).sendKeys(strHealthCentre[i]);
					Thread.sleep(3000);
					driver2.findElement(By.xpath("//a[@class='choose-page']")).click();
					Thread.sleep(2000);
					}
					}
					
					
         //click on publish 
		driver2.findElement(By.xpath("//div[@class='dropdown-toggle icon icon-arrow-up']")).click();
		Thread.sleep(3000);
		((JavascriptExecutor) driver2).executeScript("arguments[0].scrollIntoView(true);", driver2.findElement(By.xpath("//em[text()='Publish']")));
		System.out.println("focused");
		driver2.findElement(By.xpath("//em[text()='Publish']")).click();	
		Thread.sleep(2000);
		// click on View Live
		driver2.findElement(By.xpath("(//a[@class='button button-small button-secondary'])[1]")).click();
		System.out.println("View Button Clicked");
		Thread.sleep(3000);
		
		// Close the browser
		
		driver2.close();
					
		
}catch(Exception e){
e.printStackTrace();
System.out.println(e);
}
	
		}
	
	@Test	
	public void cmnTest3() throws Exception
		{	
		try{
				
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Krishna\\Desktop\\Selenium\\chromedriver_win32\\chromedriver.exe");
		driver3 = new ChromeDriver();
		driver3.manage().window().maximize();
		driver3.get("https://www.dovemed.com/admin/");
		// Login Wagtail Application
		WebElement Username = driver3.findElement(By.xpath("//*[@name='username']"));
		Username.sendKeys("Nasira");
		WebElement Password = driver3.findElement(By.xpath("//*[@name='password']"));
		Password.sendKeys("Bnasira12");		
		driver3.findElement(By.xpath("//em[text()='Sign in']")).click();		
		Thread.sleep(2000);
		

		// Sele
					WebElement PageSelect = driver3.findElement(By.xpath("//*[@class=' icon icon-folder-open-inverse icon-arrow-right-after']"));
					PageSelect.click();
					Thread.sleep(3000);
					WebElement HomeRightArrow = driver3.findElement(By.xpath("//a[@class='c-explorer__item__action ']/span/span[1]"));
					if(HomeRightArrow.isDisplayed())
					{
						System.out.println("HomeRightArrow is present");
					}
					else
					{
						System.out.println("HomeRightArrow is not present");
					}

					HomeRightArrow.click();
					Thread.sleep(3000);
					WebElement CMN = driver3.findElement(By.xpath("(//h3[@class='c-explorer__item__title'])[4]"));
					CMN.click();

					// Click Add child page

					Thread.sleep(3000);
					WebElement ChildPage = driver3.findElement(By.xpath("//a[@class='bicolor button button-small icon icon-plus white']"));
					ChildPage.click();
					WebElement AddPage = driver3.findElement(By.xpath("(//a[@class='icon icon-plus-inverse icon-larger'])[3]"));
					Thread.sleep(3000);
					AddPage.click();					
					Thread.sleep(2000);
					
					// Read the data from Excel
					
					System.out.println("Read Excel Data");
					
					File src = new File ("C:\\Users\\Krishna\\Desktop\\CMNArticle.xlsx");
					System.out.println("Excel Loaded");
					FileInputStream fis;
					fis = new FileInputStream(src);
					@SuppressWarnings("resource")
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					XSSFSheet Sheet1 = wb.getSheetAt(0);
					Thread.sleep(2000);
					String strTitle = Sheet1.getRow(3).getCell(0).getStringCellValue();           
					//System.out.println(strTitle);
					String strDescription = Sheet1.getRow(3).getCell(1).getStringCellValue();
					//System.out.println(strDescription);
					String strKeyword = Sheet1.getRow(3).getCell(2).getStringCellValue();
					//System.out.println(strKeyword);
					String strContent = Sheet1.getRow(3).getCell(3).getStringCellValue();
					//System.out.println(strContent);
					String strReferences = Sheet1.getRow(3).getCell(4).getStringCellValue();
					//System.out.println(strReferences);
					String strMaterial = Sheet1.getRow(3).getCell(5).getStringCellValue();
					//System.out.println(strMaterial);
					String strimgDescripe = Sheet1.getRow(3).getCell(6).getStringCellValue();
					//System.out.println(strimgDescripe);	
					Thread.sleep(1000);
	                //Enter the details in CMN WebPage
					driver3.findElement(By.name("title")).sendKeys(strTitle);
					Thread.sleep(2000);
					driver3.findElement(By.xpath("//div[@class='fr-element fr-view']")).sendKeys(strDescription);
					Thread.sleep(2000);
					
					// Click on Press Release
					
					((JavascriptExecutor) driver3).executeScript("arguments[0].scrollIntoView(true);", driver3.findElement(By.xpath("//input[@name='press_release']")));
					driver3.findElement(By.xpath("//input[@name='press_release']")).click();
					Thread.sleep(2000);
					driver3.findElement(By.xpath("//input[@id='id_keywords']")).sendKeys(strKeyword);
					Thread.sleep(2000);
					driver3.findElement(By.xpath("(//div[@class='fr-element fr-view'])[2]")).sendKeys(strContent);
					Thread.sleep(2000);
					driver3.findElement(By.xpath("(//div[@class='fr-element fr-view'])[3]")).sendKeys(strReferences);;
					Thread.sleep(2000);
					driver3.findElement(By.xpath("(//div[@class='fr-element fr-view'])[4]")).sendKeys(strMaterial);;
					Thread.sleep(2000);
					
					//Select the topic as Current Medical News
					
					new Select(driver3.findElement(By.xpath("//select[@id='id_topic']"))).selectByVisibleText("Current Medical News");
					Thread.sleep(3000);
					
					//Add Image
					
					driver3.findElement(By.xpath("//a[@id='id_carousel_items-ADD']")).click();
					Thread.sleep(5000);
					driver3.findElement(By.xpath("//button[text()='Choose an image']")).click();
					Thread.sleep(4000);
					driver3.findElement(By.xpath("//input[@id='id_q']")).sendKeys("Medical News");
					Thread.sleep(4000);
					new Select(driver3.findElement(By.xpath("//select[@id='collection_chooser_collection_id']"))).selectByVisibleText("DoveMed - Current Medical News images");
					Thread.sleep(5000);
					
					
					//WebElement Monday = driver3.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_08.max-165x165.png']"));
					//Monday.click();
					
					//driver3.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_07.max-165x165.png']")).click();
					//driver3.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_05.max-165x165.png']")).click();
					//driver3.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_03.max-165x165.png']")).click();
					//driver3.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_06.max-165x165.png']")).click();
					//driver3.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_04.max-165x165.png']")).click();
					//driver3.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_02.max-165x165.png']")).click();
					//driver3.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_01.max-165x165.png']")).click();
					//Thread.sleep(3000);
					
					
					String[] days = new String[]{"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		            Calendar c = Calendar.getInstance();		           
		            String Today = days[c.get(Calendar.DAY_OF_WEEK)-1];
		            System.out.println(Today);
		            if(Today.equalsIgnoreCase("Sunday"))
		            {
		            	driver3.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_08.max-165x165.png']")).click();						
		            }
		            if(Today.equalsIgnoreCase("Monday"))
		            {          	
		            	driver3.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_07.max-165x165.png']")).click();
		            }
		            if(Today.equalsIgnoreCase("Tuesday"))
		            {
		            	driver3.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_05.max-165x165.png']")).click();
		            }
		            if(Today.equalsIgnoreCase("Wednesday"))
		            {
		            	driver3.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_03.max-165x165.png']")).click();
		            }
		            if(Today.equalsIgnoreCase("Thursday"))
		            {
		            	driver3.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_06.max-165x165.png']")).click();
		            }
		            if(Today.equalsIgnoreCase("Friday"))
		            {
		            	driver3.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_04.max-165x165.png']")).click();
		            }
		            if(Today.equalsIgnoreCase("Saturday"))
		            {
		            	driver3.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_02.max-165x165.png']")).click();
		            }
		            
		          /* {
		            	driver3.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_01.max-165x165.png']")).click();
		            	Thread.sleep(3000);
		           }*/
					//Add Image Description	
		            Thread.sleep(3000);
					driver3.findElement(By.xpath("//input[@name='carousel_items-0-description']")).sendKeys(strimgDescripe);
					Thread.sleep(2000);
					
                   //Add Health Centers	
					String[] strHC = Sheet1.getRow(1).getCell(7).getStringCellValue().split(",");
					String[] strHealthCentre = Sheet1.getRow(1).getCell(7).getStringCellValue().split(",");
					for(int i = 0; i<strHealthCentre.length;i++){
					System.out.println(strHealthCentre[i]);	
					//for(int j=0; j<strHC.length;j++){
						//System.out.println(strHC[j]);
													
					if(strHealthCentre[i].equalsIgnoreCase(strHC[i])){
						System.out.println("if condition completed");
										
					driver3.findElement(By.xpath("(//a[@class='button bicolor icon icon-plus'])[5]")).click();
					Thread.sleep(5000);
					((JavascriptExecutor) driver3).executeScript("arguments[0].scrollIntoView(true);", driver3.findElement(By.xpath("//button[text()='Choose a page (Center Page)']")));
					driver3.findElement(By.xpath("//button[text()='Choose a page (Center Page)']")).click();
					Thread.sleep(5000);
					driver3.findElement(By.xpath("//input[@id='id_q']")).sendKeys(strHealthCentre[i]);
					Thread.sleep(3000);
					driver3.findElement(By.xpath("//a[@class='choose-page']")).click();
					Thread.sleep(2000);
					}
					}
					
					
         //click on publish 
		driver3.findElement(By.xpath("//div[@class='dropdown-toggle icon icon-arrow-up']")).click();
		Thread.sleep(3000);
		((JavascriptExecutor) driver3).executeScript("arguments[0].scrollIntoView(true);", driver3.findElement(By.xpath("//em[text()='Publish']")));
		System.out.println("focused");
		driver3.findElement(By.xpath("//em[text()='Publish']")).click();	
		Thread.sleep(2000);
		// click on View Live
		driver3.findElement(By.xpath("(//a[@class='button button-small button-secondary'])[1]")).click();
		System.out.println("View Button Clicked");
		Thread.sleep(3000);
		
		// Close the browser
		
		driver3.close();
					
		
}catch(Exception e){
e.printStackTrace();
System.out.println(e);
}
	
		}
	
	@Test	
	public void cmnTest4() throws Exception
		{	
		try{
				
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Krishna\\Desktop\\Selenium\\chromedriver_win32\\chromedriver.exe");
		driver4 = new ChromeDriver();
		driver4.manage().window().maximize();
		driver4.get("https://www.dovemed.com/admin/");
		// Login Wagtail Application
		WebElement Username = driver4.findElement(By.xpath("//*[@name='username']"));
		Username.sendKeys("Nasira");
		WebElement Password = driver4.findElement(By.xpath("//*[@name='password']"));
		Password.sendKeys("Bnasira12");		
		driver4.findElement(By.xpath("//em[text()='Sign in']")).click();		
		Thread.sleep(2000);
		

		// Sele
					WebElement PageSelect = driver4.findElement(By.xpath("//*[@class=' icon icon-folder-open-inverse icon-arrow-right-after']"));
					PageSelect.click();
					Thread.sleep(3000);
					WebElement HomeRightArrow = driver4.findElement(By.xpath("//a[@class='c-explorer__item__action ']/span/span[1]"));
					if(HomeRightArrow.isDisplayed())
					{
						System.out.println("HomeRightArrow is present");
					}
					else
					{
						System.out.println("HomeRightArrow is not present");
					}

					HomeRightArrow.click();
					Thread.sleep(3000);
					WebElement CMN = driver4.findElement(By.xpath("(//h3[@class='c-explorer__item__title'])[4]"));
					CMN.click();

					// Click Add child page

					Thread.sleep(3000);
					WebElement ChildPage = driver4.findElement(By.xpath("//a[@class='bicolor button button-small icon icon-plus white']"));
					ChildPage.click();
					WebElement AddPage = driver4.findElement(By.xpath("(//a[@class='icon icon-plus-inverse icon-larger'])[3]"));
					Thread.sleep(3000);
					AddPage.click();					
					Thread.sleep(2000);
					
					// Read the data from Excel
					
					System.out.println("Read Excel Data");
					
					File src = new File ("C:\\Users\\Krishna\\Desktop\\CMNArticle.xlsx");
					System.out.println("Excel Loaded");
					FileInputStream fis;
					fis = new FileInputStream(src);
					@SuppressWarnings("resource")
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					XSSFSheet Sheet1 = wb.getSheetAt(0);
					Thread.sleep(2000);
					String strTitle = Sheet1.getRow(4).getCell(0).getStringCellValue();           
					//System.out.println(strTitle);
					String strDescription = Sheet1.getRow(4).getCell(1).getStringCellValue();
					//System.out.println(strDescription);
					String strKeyword = Sheet1.getRow(4).getCell(2).getStringCellValue();
					//System.out.println(strKeyword);
					String strContent = Sheet1.getRow(4).getCell(3).getStringCellValue();
					//System.out.println(strContent);
					String strReferences = Sheet1.getRow(4).getCell(4).getStringCellValue();
					//System.out.println(strReferences);
					String strMaterial = Sheet1.getRow(4).getCell(5).getStringCellValue();
					//System.out.println(strMaterial);
					String strimgDescripe = Sheet1.getRow(4).getCell(6).getStringCellValue();
					//System.out.println(strimgDescripe);	
					Thread.sleep(1000);
	                //Enter the details in CMN WebPage
					driver4.findElement(By.name("title")).sendKeys(strTitle);
					Thread.sleep(2000);
					driver4.findElement(By.xpath("//div[@class='fr-element fr-view']")).sendKeys(strDescription);
					Thread.sleep(2000);
					
					// Click on Press Release
					
					((JavascriptExecutor) driver4).executeScript("arguments[0].scrollIntoView(true);", driver4.findElement(By.xpath("//input[@name='press_release']")));
					driver4.findElement(By.xpath("//input[@name='press_release']")).click();
					Thread.sleep(2000);
					driver4.findElement(By.xpath("//input[@id='id_keywords']")).sendKeys(strKeyword);
					Thread.sleep(2000);
					driver4.findElement(By.xpath("(//div[@class='fr-element fr-view'])[2]")).sendKeys(strContent);
					Thread.sleep(2000);
					driver4.findElement(By.xpath("(//div[@class='fr-element fr-view'])[3]")).sendKeys(strReferences);;
					Thread.sleep(2000);
					driver4.findElement(By.xpath("(//div[@class='fr-element fr-view'])[4]")).sendKeys(strMaterial);;
					Thread.sleep(2000);
					
					//Select the topic as Current Medical News
					
					new Select(driver4.findElement(By.xpath("//select[@id='id_topic']"))).selectByVisibleText("Current Medical News");
					Thread.sleep(3000);
					
					//Add Image
					
					driver4.findElement(By.xpath("//a[@id='id_carousel_items-ADD']")).click();
					Thread.sleep(5000);
					driver4.findElement(By.xpath("//button[text()='Choose an image']")).click();
					Thread.sleep(6000);
					driver4.findElement(By.xpath("//input[@id='id_q']")).sendKeys("Medical News");
					Thread.sleep(6000);
					new Select(driver4.findElement(By.xpath("//select[@id='collection_chooser_collection_id']"))).selectByVisibleText("DoveMed - Current Medical News images");
					Thread.sleep(5000);
					
					
					//WebElement Monday = driver4.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_08.max-165x165.png']"));
					//Monday.click();
					//driver4.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_07.max-165x165.png']")).click();
					//driver4.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_05.max-165x165.png']")).click();
					//driver4.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_03.max-165x165.png']")).click();
					//driver4.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_06.max-165x165.png']")).click();
					//driver4.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_04.max-165x165.png']")).click();
					//driver4.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_02.max-165x165.png']")).click();
					//driver4.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_01.max-165x165.png']")).click();
					//Thread.sleep(3000);
					
					String[] days = new String[]{"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		            Calendar c = Calendar.getInstance();		           
		            String Today = days[c.get(Calendar.DAY_OF_WEEK)-1];
		            System.out.println(Today);
		            if(Today.equalsIgnoreCase("Sunday"))
		            {
		            	driver4.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_08.max-165x165.png']")).click();						
		            }
		            if(Today.equalsIgnoreCase("Monday"))
		            {          	
		            	driver4.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_07.max-165x165.png']")).click();
		            }
		            if(Today.equalsIgnoreCase("Tuesday"))
		            {
		            	driver4.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_05.max-165x165.png']")).click();
		            }
		            if(Today.equalsIgnoreCase("Wednesday"))
		            {
		            	driver4.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_03.max-165x165.png']")).click();
		            }
		            if(Today.equalsIgnoreCase("Thursday"))
		            {
		            	driver4.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_06.max-165x165.png']")).click();
		            }
		            if(Today.equalsIgnoreCase("Friday"))
		            {
		            	driver4.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_04.max-165x165.png']")).click();
		            }
		            if(Today.equalsIgnoreCase("Saturday"))
		            {
		            	driver4.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_02.max-165x165.png']")).click();
		            }
		            
		          /* {
		            	driver4.findElement(By.xpath("//img[@src='https://prod-dovemed.s3.amazonaws.com/media/images/Medical_News_01.max-165x165.png']")).click();
		            	Thread.sleep(3000);
		           }*/
					
					//Add Image Description	
		            Thread.sleep(3000);
					driver4.findElement(By.xpath("//input[@name='carousel_items-0-description']")).sendKeys(strimgDescripe);
					Thread.sleep(2000);
					
                   //Add Health Centers	
					String[] strHC = Sheet1.getRow(1).getCell(7).getStringCellValue().split(",");
					String[] strHealthCentre = Sheet1.getRow(1).getCell(7).getStringCellValue().split(",");
					for(int i = 0; i<strHealthCentre.length;i++){
					System.out.println(strHealthCentre[i]);	
					//for(int j=0; j<strHC.length;j++){
						//System.out.println(strHC[j]);
													
					if(strHealthCentre[i].equalsIgnoreCase(strHC[i])){
						System.out.println("if condition completed");
										
					driver4.findElement(By.xpath("(//a[@class='button bicolor icon icon-plus'])[5]")).click();
					Thread.sleep(5000);
					((JavascriptExecutor) driver4).executeScript("arguments[0].scrollIntoView(true);", driver4.findElement(By.xpath("//button[text()='Choose a page (Center Page)']")));
					driver4.findElement(By.xpath("//button[text()='Choose a page (Center Page)']")).click();
					Thread.sleep(5000);
					driver4.findElement(By.xpath("//input[@id='id_q']")).sendKeys(strHealthCentre[i]);
					Thread.sleep(3000);
					driver4.findElement(By.xpath("//a[@class='choose-page']")).click();
					Thread.sleep(2000);
					}
					}
					
					
         //click on publish 
		driver4.findElement(By.xpath("//div[@class='dropdown-toggle icon icon-arrow-up']")).click();
		Thread.sleep(3000);
		((JavascriptExecutor) driver4).executeScript("arguments[0].scrollIntoView(true);", driver4.findElement(By.xpath("//em[text()='Publish']")));
		System.out.println("focused");
		driver4.findElement(By.xpath("//em[text()='Publish']")).click();	
		Thread.sleep(2000);
		// click on View Live
		driver4.findElement(By.xpath("(//a[@class='button button-small button-secondary'])[1]")).click();
		System.out.println("View Button Clicked");
		Thread.sleep(3000);
		
		// Close the browser
		
		driver4.close();
					
		
}catch(Exception e){
e.printStackTrace();
System.out.println(e);
}
	
		}
				}
	

