package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import starter.interactions.FinalizePurchaseInteraction;
import starter.interactions.SelectProductInteraction;
import starter.questions.GeneralQuestions;
import starter.tasks.LoginTask;
import starter.tasks.NavigateTo;
import starter.tasks.PersonInfoTask;
import starter.util.GeneralEnvironment;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.*;
import static org.hamcrest.Matchers.equalTo;
import static starter.ui.CartUI.MSG_SUCCESS_SHOPPING;

public class ShoppingCartStep {

    @Given("Ingreso a la pagina saucedemo")
    public void ingresoALaPaginaSaucedemo() {
        String environment = GeneralEnvironment.environmentVariables("environments.default.webdriver.base.url");
        theActorCalled("Andres").attemptsTo(NavigateTo.openPage(environment));
    }

    @When("Intento iniciar sesion con las credenciales")
    public void intentoIniciarSesionConLasCredenciales(List<Map<String, String>> list) {
        System.out.println("user " + list.get(0).get("user") + "pass " + list.get(0).get("password"));
        withCurrentActor(LoginTask.Login(list));
    }

    @And("Intento agregar {int} productos al carrito")
    public void intentoAgregarProductosAlCarrito(int products) {
        theActorInTheSpotlight().attemptsTo(SelectProductInteraction.SelectProduct(products));
    }

    @And("Ingreso informacion personal")
    public void ingresoInformacionPersonal(List<Map<String, String>> person_info) {
        for (Map<String, String> info : person_info) {
            withCurrentActor(PersonInfoTask.PersonInfo(info));
        }
    }

    @And("Finalizo la compra")
    public void finalizoLaCompra() {
        withCurrentActor(FinalizePurchaseInteraction.FinalizePurchase());
    }

    @Then("El sistema debe de mostrame el mensaje {string}")
    public void elSistemaDebeDeMostrameElMensaje(String msg) {
        theActorInTheSpotlight().should(seeThat(GeneralQuestions.valueString(MSG_SUCCESS_SHOPPING), equalTo(msg)));
    }
}
