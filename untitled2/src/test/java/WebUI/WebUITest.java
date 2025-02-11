package WebUI;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class WebUITest {
    String userLogin ="standard_user";
    String password ="secret_sauce";

    @BeforeAll
    public static void setUp(){
        Selenide.open("https://www.saucedemo.com");
    }
    @Test
    public void loginTest(){
        //Проверяем, что зашли на страницу
        $(".login_logo").shouldHave(text("Swag Labs"));
        //Ищем и вводим данные для входа
        $("#user-name").setValue(userLogin);
        $("#password").setValue(password);

        $("#login-button").click();
        //Проверяем переход на страницу
        $(".title").shouldBe(visible).shouldHave(text("Products"));
        sleep(3000);
    }

   /* @Test
    public void addToCard(){
        //Находи 1 карточку
        SelenideElement item =$("(//div[@class='inventory_item_description'])[1]");
        item.shouldBe(visible);
        //Проверяем имя карточки
        SelenideElement itemName = $("#item_4_title_link");
        itemName.$(".inventory_item_name ").shouldHave(text("Sauce Labs Backpack"));
        //Добывляем товар в корзину
        item.$("#add-to-cart-sauce-labs-backpack").click();
        //Проверяем, что товар добавился
        item.$("#remove-sauce-labs-backpack").shouldBe(visible);
        SelenideElement shoppingCart =$(".shopping_cart_link");
        shoppingCart.$("data-test='shopping-cart-badge'").shouldBe(visible).shouldHave(text("1"));
    }*/

    @Test
    public void detailedСard(){
        //Находи 1 карточку
        SelenideElement item =$x("//body/div[@id='root']/div[@id='page_wrapper']/div[@id='contents_wrapper']/div[@id='inventory_container']/div/div[@id='inventory_container']/div[@class='inventory_list']/div[1]");
        item.shouldBe(visible);
        //Проверяем имя карточки
        SelenideElement itemName = $("#item_4_title_link");
        itemName.$(".inventory_item_name ").shouldHave(text("Sauce Labs Backpack"));
        //Переходим в карточку товара
        itemName.click();
        //Проверяем карточку товара
        $("#back-to-products").shouldBe(visible);
        $(".inventory_details_name").shouldBe(visible)
                .shouldHave(text("Sauce Labs Backpack"));

    }


}
