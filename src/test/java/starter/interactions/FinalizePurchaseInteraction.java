package starter.interactions;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import static net.serenitybdd.core.Serenity.getDriver;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static starter.ui.CartUI.BTN_FINISH;
import static starter.ui.CartUI.SUBTOTAL_PRICE_PRODUCTS;

public class FinalizePurchaseInteraction implements Interaction {


    @Override
    public <T extends Actor> void performAs(T actor) {

        try {
            Actions actions = new Actions(getDriver());

            float total_products = Serenity.sessionVariableCalled("total_products");
            System.out.println("Total price products serenity " + total_products);
            WebElementFacade lbl_total_price = BrowseTheWeb.as(actor).find(By.xpath("//div[@class='summary_subtotal_label']"));
            actions.moveToElement(lbl_total_price).perform();

            Assert.assertEquals("Error el precio total de los productos seleccionados no coincide con el total del carrito de compras ", String.valueOf(total_products), SUBTOTAL_PRICE_PRODUCTS.resolveFor(actor).getText().replace("Item total: $", ""));

            //Finalizar la compra
            actor.attemptsTo(
                    WaitUntil.the(BTN_FINISH, isVisible()),
                    Click.on(BTN_FINISH)
            );

            Thread.sleep(2000);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static FinalizePurchaseInteraction FinalizePurchase() {
        return instrumented(FinalizePurchaseInteraction.class);
    }

}
