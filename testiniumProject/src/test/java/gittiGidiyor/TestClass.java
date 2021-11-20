package gittiGidiyor;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import pages.GittigidiyorPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.log4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestClass {


    @Test
    public void test() throws InterruptedException, IOException {


        log4j.startLog("Test baslar");
        //- www.gittigidiyor.com sitesi açılır.
        GittigidiyorPage gittigidiyorPage = new GittigidiyorPage();
        Driver.getDriver().get(ConfigReader.getProperty("ggidiyorUrl"));
        log4j.info("Site acilir");

        // - Arama kutucuğuna bilgisayar kelimesi girilir.
        gittigidiyorPage.searchBox.sendKeys("bilgisayar" + Keys.ENTER);
        log4j.info("Bilgisayar kelimesi girilir");

        // cookies kapatılır
        gittigidiyorPage.cookies.click();

        // - Arama sonuçları sayfasından 2.sayfa açılır.
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript ("window.scrollBy (0,10000)");
        gittigidiyorPage.secondPage.click();
        log4j.info("arama sonuclari sayfasinda 2.sayfa acilir");

        // - 2.sayfanın açıldığı kontrol edilir.
        String secondUrl = "https://www.gittigidiyor.com/arama?k=bilgisayar&sf=2";
        Assert.assertEquals(secondUrl,Driver.getDriver().getCurrentUrl());
        log4j.info("2.sayfanın acildigi kontrol edilir");

        // - Sonuca göre sergilenen ürünlerden rastgele bir ürün seçilir.
        js.executeScript ("window.scrollBy (0,500)");
        Thread.sleep(3000);
        gittigidiyorPage.firstComputer.click();
        log4j.info("sonuclara gore bir urun secilir");

        // - Seçilen ürünün ürün bilgisi ve tutar bilgisi txt dosyasına yazılır.
        File file = new File("urunSecilenBilgileri.txt");
        FileWriter fileWriter = new FileWriter(file);

        String urunBilgisiText = gittigidiyorPage.urunBilgi.getText();
        String urunFiyat = gittigidiyorPage.urunFiyat.getText();

        fileWriter.write(urunBilgisiText + " " + urunFiyat);

        fileWriter.close();
        log4j.info("secilen urun bilgisi ve tutar bilgisi txt dosyasina yazilir");

        // - Seçilen ürün sepete eklenir.
        js.executeScript ("window.scrollBy (0,500)");
        Thread.sleep(2000);
        gittigidiyorPage.sepeteEkle.click();
        log4j.info("secilen urun sepete eklenir");

        Thread.sleep(2000);

        // - Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyatının doğruluğu karşılaştırılır.
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(gittigidiyorPage.sepet).perform();
        Thread.sleep(2000);
        gittigidiyorPage.sepeteGit.click();

        Assert.assertEquals("sepetteki fiyat ile urun sayfasindaki fiyat uyusmuyor" ,urunFiyat, gittigidiyorPage.sepettekiFiyat.getText());
        log4j.info("fiyatlar karsilastirilir");

        // - Adet arttırılarak ürün adedinin 2 olduğu doğrulanır.
        Select select = new Select(gittigidiyorPage.dropDownSepet);
        select.selectByValue("2");

        Assert.assertTrue("sepette iki adet urun gozukmuyor",gittigidiyorPage.sepettekiUrunSecilmis.isDisplayed());
        log4j.info("adet arttırılarak 2 adet urun oldugu dogrulanir");

        Thread.sleep(2000);

        gittigidiyorPage.urunleriSil.click();

        Thread.sleep(3500);
        log4j.info("urunler silinir");

        // - Ürün sepetten silinerek sepetin boş olduğu kontrol edilir
        Assert.assertTrue(gittigidiyorPage.sepetteUrunBulunmamaktadir.isDisplayed());
        log4j.info("sepetin bos oldugu test edilir");

        Driver.closeDriver();
        log4j.info("sayfa kapanir.");

    }
}
