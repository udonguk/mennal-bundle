import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class Run {
    public static void main(String[] args) {
        // bonigarcia 라이브러리를 통해서 크롬 드라이버를 세팅
        WebDriverManager.chromedriver().setup();
        // 셀레늄에 드라이버 설정
        ChromeDriver driver =new ChromeDriver();
        // 웹브라우저 크기 설정
        Dimension dimension =new Dimension(414, 896);
        driver.manage().window().setSize(dimension);
        // 브라우저 띄우기
        driver.get("http://www.mennal.co.kr");

        // 이름 가져오기
        String title = driver.getTitle();

        // 기다리는 방법
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        // 버튼 클릭
        WebElement menuButton = driver.findElement(By.id("menuButton"));
        menuButton.click();

        WebElement categoryButton = driver.findElement(By.id("/survey/01"));
        categoryButton.click();

        // Xpath 를 이용해서 아이디를 패턴으로 가져 왔다.
        List<WebElement> itemButtons =
                driver.findElements(By.xpath("//*[contains(@id,'[order]1')]"));

        itemButtons.forEach(WebElement::click);

        WebElement getResultButton =
                driver.findElement(By.id("getResultButton"));
//        getResultButton.click();
    }
}
