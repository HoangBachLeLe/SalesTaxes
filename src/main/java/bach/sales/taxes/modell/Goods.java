package bach.sales.taxes.modell;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface Goods {

    double BASICSALESTAX = 0.1;
    double IMPORTTAX = 0.05;
    BigDecimal INCREMENT = BigDecimal.valueOf(0.05);

    BigDecimal calculateSalesTaxes();

    BigDecimal calculatePriceWithTax();

    default BigDecimal roundUpPrice(final BigDecimal price) {
        return price //NOPMD
                .divide(INCREMENT, 0, RoundingMode.UP)
                .multiply(INCREMENT);
    }
}
