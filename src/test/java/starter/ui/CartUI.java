package starter.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CartUI {
    public static Target PRODUCT_LIST = Target.the("Lista productos").locatedBy("//div[@class='inventory_item']//button");

    public static Target DETAIL_PRODUCT = Target.the("Detalle producto").locatedBy("(//div[@class='inventory_list']//div[@class='inventory_item']//div[@class='{0}'])[{1}]");

    public static Target BUTTON_ADD_PRODUCT = Target.the("Boton añadir producto").locatedBy("(//button[@class='btn btn_primary btn_small btn_inventory '])[{0}]");

    public static final String ICON_CART = "shopping_cart_link";

    public static Target ITEMS_CART = Target.the("Cantidad de items carrito de compras").located(By.className("shopping_cart_badge"));


    public static Target PRODUCT_NAME_CART = Target.the("Nombre producto carrito de compra").locatedBy("//div[@class='inventory_item_name' and contains(.,'{0}')]/ancestor::div[@class='cart_item_label']//div[@class='inventory_item_name']");

    public static Target PRODUCT_DESCRIP_CART = Target.the("Detalle producto carrito de compra").locatedBy("//div[@class='inventory_item_name' and contains(.,'{0}')]/ancestor::div[@class='cart_item_label']//div[@class='inventory_item_desc']");

    public static Target SUBTOTAL_PRICE_PRODUCTS = Target.the("Total de los productos").locatedBy("//div[@class='summary_subtotal_label']");

    public static Target BTN_FINISH = Target.the("Boton de finalizar").locatedBy("//button[@id='finish']");

    public static Target MSG_SUCCESS_SHOPPING = Target.the("Mensaje de compra exitosa").locatedBy(" //h2[@class='complete-header']");

    public static Target PRODUCT_PRICE_CART = Target.the("Precio producto carrito de compra").locatedBy("//div[@class='inventory_item_name' and contains(.,'{0}')]/ancestor::div[@class='cart_item_label']//div[@class='inventory_item_price']");

    public static Target BTN_CHECKOUT = Target.the("Boton Checkout").locatedBy("//button[@id='checkout']");

    public static Target INPUT_FIRT_NAME = Target.the("Nombre").locatedBy("//input[@id='first-name']");

    public static Target INPUT_LAST_NAME = Target.the("Apellido").locatedBy("//input[@id='last-name']");

    public static Target INPUT_POSTAL_CODE = Target.the("Codigo postal").locatedBy("//input[@id='postal-code']");

    public static Target BTN_CONTINUE = Target.the("Boton continuar").locatedBy("//input[@id='continue']");

}
