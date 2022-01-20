package bach.sales.taxes.form;

import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class InputStringForm {

    @Pattern(regexp = "[1-9]+\\s([a-zA-Z]+\\s)+at\\s\\d+\\.\\d+", message = "[1-9]+\\s([a-zA-Z]+\\s)+at\\s\\d+\\.\\d+")
    private final String inputString;
}
