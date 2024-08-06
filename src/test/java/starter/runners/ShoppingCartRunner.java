package starter.runners;


import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/ShoppingCart.feature",
        tags = "",
        glue = "starter.stepdefinitions")
public class ShoppingCartRunner {
}
