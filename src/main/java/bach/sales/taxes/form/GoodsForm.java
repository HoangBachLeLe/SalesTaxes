package bach.sales.taxes.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class GoodsForm {

    @NotEmpty(message = "The name must not be empty!")
    private final String goodsName;
    @Pattern(regexp = "others|books|food|medicalProducts")
    private final String category;
    @Pattern(regexp = "IMPORTED|NOTIMPORTED")
    private final String origin;
    @Pattern(regexp = "^\\d*\\.\\d+|\\d+\\.\\d*$", message = "The number must be a decimal number! Do not forget the dot!")
    @Positive(message = "The price must be positive!")
    private final String price;
}
