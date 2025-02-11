import com.codeborne.selenide.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class HomeworkTest {
    public static void addDevice(SelenideElement device){
        device.click();
        device.should(Condition.disappear);
        $(byText("Add to cart")).shouldBe(Condition.visible)
                .click();
        Selenide.confirm();

        $(byText("Home ")).click();
    }

    @Test
    void selenideTest(){
        Selenide.open("https://demoblaze.com");
        ElementsCollection cards = Selenide.elements(Selectors.byClassName("card"));
        cards.should(CollectionCondition.size(9));

        SelenideElement nokiaLumiaCard = cards.find(Condition.text("Nokia lumia 1520"));
        SelenideElement nokiaLumiaPriceText = nokiaLumiaCard.find(By.tagName("h5"));
        int nokiaPrice =Integer.parseInt(nokiaLumiaPriceText.getText());
        addDevice(nokiaLumiaCard);

        SelenideElement iphone = cards.find(Condition.text("Iphone 6 32gb"));
        SelenideElement iphonePriceText = iphone.find(By.tagName("h5"));
        int iphonePrice =Integer.parseInt(iphonePriceText.getText());
        addDevice(iphone);

        SelenideElement sonyVaioI7 = cards.find(Condition.text("Sony vaio i7"));
        SelenideElement sonyPriceText = sonyVaioI7.find(By.tagName("h5"));
        int sonyPrice =Integer.parseInt(sonyPriceText.getText());
        addDevice(sonyVaioI7);

        SelenideElement cart = Selenide.element(Selectors.byId("cartur"));
        cart.click();

        int total = Integer.parseInt(Selenide.element(Selectors.byId("totalp")).getText());
        if (total!=(nokiaPrice+iphonePrice+sonyPrice)){
            System.out.println("The total amount of the basket is incorrect");
        }
        $(byText("Place Order")).click();

        SelenideElement nameInput = Selenide.element(Selectors.byId("name"));
        SelenideElement countryInput = Selenide.element(Selectors.byId("country"));
        SelenideElement cityInput = Selenide.element(Selectors.byId("city"));
        SelenideElement cardInput = Selenide.element(Selectors.byId("card"));
        SelenideElement monthInput = Selenide.element(Selectors.byId("month"));
        SelenideElement yearInput = Selenide.element(Selectors.byId("year"));

        nameInput.setValue("test");
        countryInput.setValue("test");
        cityInput.setValue("test");
        cardInput.setValue("test");
        monthInput.setValue("may");
        yearInput.setValue("2025");
        $(byText("Purchase")).click();

        SelenideElement info=Selenide.element(By.className("lead"));
        String[] wholeText = info.getText().split("\n");
        String id = wholeText[0].substring(3);
        String[] finalPrice = wholeText[1].split(" ");
        int price = Integer.parseInt(finalPrice[1]);
        if (price!=(nokiaPrice+iphonePrice+sonyPrice)){
            System.out.println("The total amount of the basket is incorrect");
        }
        System.out.println("Order "+ id);

        Selenide.confirm();
        Selenide.element(By.className("sweet-alert"))
                .should(Condition.disappear);

    }

}
