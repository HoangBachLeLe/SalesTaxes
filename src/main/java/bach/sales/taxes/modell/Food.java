package bach.sales.taxes.modell;

import static bach.sales.taxes.modell.Origin.IMPORTED;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@SuppressWarnings("PMD.ShortClassName")
public class Food implements Goods {

    @SuppressWarnings("PMD.ShortVariable")
    private final Long id;
    private final String goodsName;
    private final BigDecimal price;
    private final Origin origin;

    @Override
    public String getCategory() {
        return this.getClass().getSimpleName();
    }

    @Override
    public BigDecimal calculateSalesTaxes() {
        return this.roundUpPrice(this.calculateImportTax());
    }

    @Override
    public BigDecimal calculatePriceWithTax() {
        return this.price.add(this.calculateSalesTaxes());
    }

    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    private BigDecimal calculateImportTax() {
        BigDecimal tax = BigDecimal.ZERO;
        if (this.origin.equals(IMPORTED)) {
            tax = this.price.multiply(BigDecimal.valueOf(IMPORTTAX));
        }
        return tax;
    }
}
