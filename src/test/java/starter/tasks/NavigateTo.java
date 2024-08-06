package starter.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class NavigateTo {
    public static Performable openPage(String url) {
        return Task.where("{0} open page",
                Open.url(url));
        //Open.browserOn().the(DuckDuckGoHomePage.class));
    }
}
