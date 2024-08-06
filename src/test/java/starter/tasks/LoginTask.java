package starter.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import starter.ui.LoginUI;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class LoginTask implements Task {

    private List<Map<String, String>> data;

    public LoginTask(List<Map<String, String>> dataUser) {
        this.data = dataUser;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(data.get(0).get("user")).into(LoginUI.USER_NAME),
                Enter.theValue(data.get(0).get("password")).into(LoginUI.USER_PASSWORD),
                Click.on(LoginUI.BTN_LOGIN)
        );
    }

    public static LoginTask Login(List<Map<String, String>> data) {
        return instrumented(LoginTask.class, data);
    }
}
