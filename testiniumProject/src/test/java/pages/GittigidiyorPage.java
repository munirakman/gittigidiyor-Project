package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class GittigidiyorPage {

    public GittigidiyorPage() {

        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//input[@type='text']")
    public WebElement searchBox;

    @FindBy(xpath = "//a[@class='tyj39b-5 bEEsJG']")
    public WebElement cookies;

    @FindBy (linkText = "2")
    public WebElement secondPage;

    @FindBy (xpath = "(//h2[@class='sc-1ku3a4v-0 sc-7u3xly-0 clhtrN jytHfD sc-1n49x8z-16 iqsmnF'])[1]")
    public WebElement firstComputer;

    @FindBy (id = "sp-title")
    public WebElement urunBilgi;

    @FindBy (xpath = "(//div[@id='sp-price-lowPrice'])[1]")
    public WebElement urunFiyat;

    @FindBy (id = "add-to-basket")
    public WebElement sepeteEkle;

    @FindBy (className = "basket-title")
    public WebElement sepet;

    @FindBy (linkText = "Sepete Git")
    public WebElement sepeteGit;

    @FindBy (xpath = "//div[@class='total-price']")
    public WebElement sepettekiFiyat;

    @FindBy (xpath = "//select[@class='amount']")
    public WebElement dropDownSepet;

    @FindBy (xpath = "//select[@data-value='2']")
    public WebElement sepettekiUrunSecilmis;

    @FindBy (xpath = "( //i[@class='gg-icon gg-icon-bin-medium'])[1]")
    public WebElement urunleriSil;

    @FindBy (xpath = "//div[@class='gg-d-19 gg-w-21 gg-t-19 gg-m-18']")
    public WebElement sepetteUrunBulunmamaktadir;



}
