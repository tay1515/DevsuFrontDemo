package starter.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;

public class GeneralQuestions {

    public static Question<String> valueString(Target target) {
        return actor -> Text.of(target).answeredBy(actor);
    }

}
