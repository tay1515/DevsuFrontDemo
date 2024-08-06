package starter.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import starter.ui.LoginUI;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static starter.ui.CartUI.*;
import static starter.ui.CartUI.BTN_CONTINUE;

public class PersonInfoTask implements Task {

    private String firt_name;

    private String last_name;

    private String postal_code;


    public PersonInfoTask(Map<String, String> person_info) {
        this.firt_name=person_info.get("Nombre");
        this.last_name=person_info.get("Apellido");
        this.postal_code=person_info.get("Codigo Postal");
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(INPUT_FIRT_NAME, isVisible()),
                Enter.theValue(firt_name).into(INPUT_FIRT_NAME),
                Enter.theValue(last_name).into(INPUT_LAST_NAME),
                Enter.theValue(postal_code).into(INPUT_POSTAL_CODE),
                Click.on(BTN_CONTINUE)
        );
    }

    public static PersonInfoTask PersonInfo(Map<String, String> person_info) {
        return instrumented(PersonInfoTask.class, person_info);
    }
}
