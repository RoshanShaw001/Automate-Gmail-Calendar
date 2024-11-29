package demo;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.bouncycastle.its.ITSPublicEncryptionKey.symmAlgorithm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.INFO);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Connect to the chrome-window running on debugging port
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        driver.get("https://calendar.google.com/");

        String calenderURL = driver.getCurrentUrl();
        if (calenderURL.contains("calendar")) {
            System.out.println("The URL of the Calendar homepage contains \"calendar\"");
        } else {
            System.out.println("The URL of the Calendar homepage doesn't contains \"calendar\"");
        }
        System.out.println("end Test case: testCase01");
    }

    public void testCase02() throws InterruptedException {
        // (//span[@class="AeBiU-RLmnJb"])[2]
        // button[@aria-haspopup="menu"]//span[@class="AeBiU-RLmnJb"]
        // button[@aria-haspopup='menu']//span[@jsname='UkTUqb']

        // li[@data-viewkey="month"]

        // button[@aria-haspopup="menu"]/span[text()="Create"]
        // li[@data-key="task"]

        // input[@placeholder="Add title"]

        // input[@aria-label="Start date"]
        // div[@class="gHQcAb"]//div[@class="b5AwHb kkUTBb" and text()="15"]

        // textarea[@placeholder="Add description"]

        // button[@data-idom-class='pEVtpe']//span[@class='UywwFc-RLmnJb'] -- Save
        // Button
        // div[@class="VYTiVb"] --- for the flash notification after task
        // creation/updation/deletion
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("Start Test case: testCase02");
        // driver.get("https://calendar.google.com/");
        Thread.sleep(3000);
        // Selecting Month view
        driver.findElement(By.xpath("//button[@aria-haspopup='menu']//span[@jsname='UkTUqb']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//li[@data-viewkey='month']")).click();

        // validating switching to month view
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//span[@class='AeBiU-vQzf8d' and text()='Month']")));
        String monthView = driver.findElement(By.xpath("//span[@class='AeBiU-vQzf8d' and text()='Month']")).getText();
        if (monthView.equals("Month")) {
            System.out.println("Successfully switched into Month view");
        } else {
            System.out.println("Switching into Month view Failed");
        }
        // Opening Task editor tab
        driver.findElement(By.xpath("//button[@aria-haspopup=\"menu\"]/span[text()=\"Create\"]")).click();
        driver.findElement(By.xpath("//li[@data-key=\"task\"]")).click();

        // Entering the task title
        WebElement title = driver.findElement(By.xpath("//input[@placeholder=\"Add title\"]"));

        wait.until(ExpectedConditions.elementToBeClickable(title));
        title.sendKeys("Crio INTV Task Automation");

        // Selecting Date
       /*  WebElement dateField = driver.findElement(By.xpath("//span[@data-key='startDate']"));
        wait.until(ExpectedConditions.elementToBeClickable(dateField));
        dateField.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class=\"gHQcAb\"]//div[@class=\"b5AwHb kkUTBb\" and text()='29']")).click();
        */

        // Entering the task description
        WebElement description = driver.findElement(By.xpath("//textarea[@placeholder=\"Add description\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(description));
        description.sendKeys("Crio INTV Calendar Task Automation");

        // Save the task
        // (//div[@class='BTotkb JaKw1']//div)[4]
        // button[@data-idom-class='pEVtpe']//span[@class='SXdXAb-BFbNVe'] -- Did not
        // worked
        driver.findElement(By.xpath("(//div[@class='BTotkb JaKw1']//div)[4]")).click();

        // Validating the flash notifaction
        Thread.sleep(2000);
        String flashNotification = driver.findElement(By.xpath("//div[@class=\"VYTiVb\"]")).getText();

        if (flashNotification.equals("Task created")) {
            System.out.println("Task Created Successfully");
        } else {
            System.out.println("Task creation failed");
        }

        // Verifying the title of the task is correct
        driver.findElement(By.xpath("(//div[@role=\"button\"]//span[contains(text(),'Crio INTV Task Automation')])[1]"))
                .click();
        String titleText = driver.findElement(By.xpath("//div[@class='FzsTU']//span[@role='heading']")).getText();
        if (titleText.equals("Crio INTV Task Automation")) {
            System.out.println("Successfully validated the created Task");
        } else {
            System.out.println("The created Task validation failed");
        }

        //closing the task tab
        driver.findElement(By.xpath("(//div[@class='Tnsqdc ']//div[@class='pYTkkf-Bz112c-RLmnJb'])[1]")).click();
        System.out.println("End Test case: testCase02");
    }

    public void testCase03() throws InterruptedException {
        // (//div[@role="button"]//span[contains(text(),'Crio INTV Task
        // Automation')])[1]

        // div[@jsaction='JIbuQc:DyVDA'] -- edit pencile icon
        // textarea[@placeholder='Add description'] -- task Description
        // div[@class='HcF6Td']//div -- Update task save button
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Start Test case: testCase03");
        Thread.sleep(3000);
        // clicking on the task to update
        driver.findElement(By.xpath("(//div[@role=\"button\"]//span[contains(text(),'Crio INTV Task Automation')])[1]"))
                .click();
        Thread.sleep(3000);
        // Clicking on the edit penciile icon
        driver.findElement(By.xpath("//div[@jsaction='JIbuQc:DyVDA']"))
                .click();

        // Entering the updated description
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement description = driver.findElement(By.xpath("//textarea[@placeholder='Add description']"));
        wait.until(ExpectedConditions.elementToBeClickable(description));
        String updatedDescription = "Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application";
        description.sendKeys(updatedDescription);

        // Save the change
        // div[@class='HcF6Td']//div -- Update task save button
        // div[@class='VYTiVb'] -- Flash Notification
        Thread.sleep(2000);
        driver.findElement(By.xpath("// div[@class='HcF6Td']//div")).click();

        // Validating the flash notifaction
        Thread.sleep(2000);
        String flashNotification = driver.findElement(By.xpath("// div[@class='VYTiVb']")).getText();

        if (flashNotification.equals("Task updated")) {
            System.out.println("Task updated Successfully");
        } else {
            System.out.println("Task updation failed");
        }

        // Validating the description is updated or not
        driver.findElement(By.xpath("(//div[@role=\"button\"]//span[contains(text(),'Crio INTV Task Automation')])[1]"))
                .click();
        Thread.sleep(3000);
        // Clicking on the edit penciile icon
        //driver.findElement(By.xpath("//div[@jsaction='JIbuQc:DyVDA']")).click();
        //Thread.sleep(2000);
        //wait.until(ExpectedConditions.elementToBeClickable(description));
        //String descriptionOfTask = description.getText();
        String descriptionOfTask = driver.findElement(By.xpath("//div[@class='FzsTU']//div[@id='xDetDlgDesc']")).getText();
        if (descriptionOfTask.equals(updatedDescription)){
            System.out.println("Updated Descrition validated");
        }
        else{
            System.out.println("Description not updated");
        }

        //closing the task tab
        driver.findElement(By.xpath("(//div[@class='Tnsqdc ']//div[@class='pYTkkf-Bz112c-RLmnJb'])[1]")).click();
        //Closing the task update tab
        //driver.findElement(By.xpath("(//div[@class='DNOpBd iqiI8']//div[@class='pYTkkf-Bz112c-RLmnJb'])[2]")).click();
        
        System.out.println("end Test case: testCase03");
    }

    public void testCase04() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Start Test case: testCase03");
        Thread.sleep(3000);
        // clicking on the task to update
        driver.findElement(By.xpath("(//div[@role=\"button\"]//span[contains(text(),'Crio INTV Task Automation')])[1]"))
                .click();
        Thread.sleep(3000);
        // Clicking on the Delete icon
        // div[@jsaction='JIbuQc:qAGoT'] -- Delete icon
        driver.findElement(By.xpath("//div[@jsaction='JIbuQc:qAGoT']"))
                .click();

        // Validating the flash notifaction
        Thread.sleep(2000);
        String flashNotification = driver.findElement(By.xpath("// div[@class='VYTiVb']")).getText();

        if (flashNotification.equals("Task deleted")) {
            System.out.println("Task Created Successfully");
        } else {
            System.out.println("Task creation failed");
        }
        System.out.println("end Test case: testCase03");
    }
}
