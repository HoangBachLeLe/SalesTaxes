package bach.sales.taxes.modell;

import static bach.sales.taxes.modell.Origin.IMPORTED;
import static bach.sales.taxes.modell.Origin.NOTIMPORTED;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FoodTest {

    @Test
    void validInstantiation() {
        String goodsName = "chocolate bar";
        BigDecimal price = BigDecimal.valueOf(0.85);
        Origin origin = NOTIMPORTED;
        Food food = new Food(goodsName, price, origin);

        assertThat(food).isNotNull();
        assertThat(food.getGoodsName()).isEqualTo(goodsName);
        assertThat(food.getPrice()).isEqualTo(price);
        assertThat(food.getOrigin()).isEqualTo(origin);
    }

    @DisplayName("Food not imported")
    @Test
    void calculateImportTax1() {
        String goodsName = "chocolate bar";
        BigDecimal price = BigDecimal.valueOf(0.85);
        Origin origin = NOTIMPORTED;
        Food food = new Food(goodsName, price, origin);

        BigDecimal salesTaxes = food.calculateSalesTaxes();

        assertThat(salesTaxes).isEqualTo("0.00");
    }

    @DisplayName("Food imported")
    @Test
    void calculateImportTax2() {
        String goodsName = "chocolate bar";
        BigDecimal price = BigDecimal.valueOf(0.85);
        Origin origin = IMPORTED;
        Food food = new Food(goodsName, price, origin);

        BigDecimal salesTaxes = food.calculateSalesTaxes();

        assertThat(salesTaxes).isEqualTo(BigDecimal.valueOf(0.05));
    }
}