package starter.interactions;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.junit.Assert;
import org.junit.experimental.theories.Theories;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import starter.ui.LoginUI;

import java.util.*;

import static net.serenitybdd.core.Serenity.getDriver;
import static net.serenitybdd.core.Serenity.webdriver;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static starter.ui.CartUI.*;

public class SelectProductInteraction implements Interaction {

    private int products;

    public SelectProductInteraction(int products) {
        this.products = products;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Actions actions = new Actions(getDriver());

        try {
            Thread.sleep(2000);
            int totalproducts = 0;
            List<String> nameProduct = new ArrayList<String>();
            List<String> detailProduct = new ArrayList<String>();
            List<WebElementFacade> listProducts = PRODUCT_LIST.resolveAllFor(actor);

            if (products <= listProducts.size()) {

                System.out.println("lista " + listProducts.size());
                Random random = new Random();

                //se almacena los numeros ids perteneciente a los item (en el ejemplo solo hay dos productos a seleccionar)
                Set<Integer> uniqueNumbers = new HashSet<>();
                while (uniqueNumbers.size() < products) {
                    uniqueNumbers.add(random.nextInt(listProducts.size() - 1) + 1);
                }

                System.out.println("unique " + uniqueNumbers.size());

                int contador = 1;
                float total_products = 0;
                Thread.sleep(5000);

                for (int productId : uniqueNumbers) {
                    System.out.print(productId + " Product ID Random ");

                    String product_name = DETAIL_PRODUCT.of("inventory_item_name ", String.valueOf(productId)).resolveFor(actor).getText();
                    String product_detail = DETAIL_PRODUCT.of("inventory_item_desc", String.valueOf(productId)).resolveFor(actor).getText();
                    String product_price = DETAIL_PRODUCT.of("inventory_item_price", String.valueOf(productId)).resolveFor(actor).getText();

                    System.out.println("ID " + productId + " Name 2: " + product_name + " Desc: " + product_detail + "Detail: " + product_price);
                    detailProduct.add(product_name);
                    detailProduct.add(product_detail);
                    detailProduct.add(product_price);

                    //Buscar los items del carrito que pertenecen al producto
                    nameProduct.add(product_name);

                    WebElementFacade scroll = BrowseTheWeb.as(actor).find(By.xpath("(//button[@class='btn btn_primary btn_small btn_inventory '])[" + productId + "]"));
                    actions.moveToElement(scroll);
                    //listProducts.get(productId).waitUntilClickable();

                    if (contador > 1) {

                        if (productId != 1) {
                            actor.attemptsTo(
                                    Click.on(BUTTON_ADD_PRODUCT.of(String.valueOf(productId - 1)))
                            );
                        } else {
                            actor.attemptsTo(
                                    Click.on(BUTTON_ADD_PRODUCT.of(String.valueOf(productId)))
                            );
                        }
                    } else {
                        actor.attemptsTo(
                                Click.on(BUTTON_ADD_PRODUCT.of(String.valueOf(productId)))
                        );
                    }


                    total_products += Float.parseFloat(product_price.replace("$", ""));
                    totalproducts++;
                    contador++;
                    Thread.sleep(3000);
                }

                System.out.println("Total products " + totalproducts);
                System.out.println("Detalle productos " + detailProduct);
                System.out.println("Name productos " + nameProduct);

                //se ubica en la posicion del icono del carrito de compras
                WebElementFacade iconCart = BrowseTheWeb.as(actor).find(By.xpath("//div[@id='shopping_cart_container']"));
                ScrollInteraction.to(iconCart);
                //actions.moveToElement(iconCart).build().perform();
                //iconCart.waitUntilClickable();
                Thread.sleep(3000);

                Assert.assertEquals("La cantidad de item seleccionados no coincide con la cantidad de item del carrito de compras. ", String.valueOf(totalproducts), ITEMS_CART.resolveFor(actor).getText());

                Thread.sleep(1000);

                actor.attemptsTo(
                        WaitUntil.the(ITEMS_CART, isVisible()),
                        Click.on(ITEMS_CART)
                );

                // obtener cada item del producto con los nombres de obtenidos de la seleccion
                List<String> detailProductCart = new ArrayList<String>();

                for (String name : nameProduct) {
                    Thread.sleep(2000);
                    System.out.println("Name P " + name);
                    System.out.println("label " + PRODUCT_PRICE_CART.of(name).resolveFor(actor).getText());
                    detailProductCart.add(PRODUCT_NAME_CART.of(name).resolveFor(actor).getText());
                    detailProductCart.add(PRODUCT_DESCRIP_CART.of(name).resolveFor(actor).getText());
                    detailProductCart.add(PRODUCT_PRICE_CART.of(name).resolveFor(actor).getText());
                }

                System.out.println("List items cart: " + detailProductCart);

                Thread.sleep(3000);
                assertThat("Los productos seleccionados no son iguales a los del carrito ", detailProduct, containsInAnyOrder(detailProductCart.toArray()));

                //continuar con la compra
                actor.attemptsTo(
                        WaitUntil.the(BTN_CHECKOUT, isVisible()),
                        Click.on(BTN_CHECKOUT)
                );


                Serenity.setSessionVariable("total_products").to(total_products);
                System.out.println("Total price products " + total_products);

                Thread.sleep(3000);
            }else {
                Assert.fail("Ingrese una cantidad de productos menor o igual a la contenida en la pagina");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static SelectProductInteraction SelectProduct(int products) {
        return instrumented(SelectProductInteraction.class, products);
    }
}
