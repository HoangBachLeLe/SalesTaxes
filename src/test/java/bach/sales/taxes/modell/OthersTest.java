package bach.sales.taxes.modell;

import static bach.sales.taxes.modell.Origin.IMPORTED;
import static bach.sales.taxes.modell.Origin.NOTIMPORTED;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OthersTest {

    @Test
    void validInstantiation() {
        String goodsName = "music CD";
        BigDecimal price = BigDecimal.valueOf(14.99);
        Origin origin = NOTIMPORTED;
        Food food = new Food(goodsName, price, origin);

        assertThat(food).isNotNull();
        assertThat(food.getGoodsName()).isEqualTo(goodsName);
        assertThat(food.getPrice()).isEqualTo(price);
        assertThat(food.getOrigin()).isEqualTo(origin);
    }

    @DisplayName("'Others' not imported")
    @Test
    void calculateSalesTaxe1() {
        String goodsName = "music CD";
        BigDecimal price = BigDecimal.valueOf(14.99);
        Origin origin = NOTIMPORTED;
        Others others = new Others(goodsName, price, origin);

        BigDecimal salesTaxes = others.calculateSalesTaxes();

        assertThat(salesTaxes).isEqualTo("1.50");
    }

    @DisplayName("'Others' imported")
    @Test
    void calculateSalesTaxe2() {
        String goodsName = "music CD";
        BigDecimal price = BigDecimal.valueOf(14.99);
        Origin origin = IMPORTED;
        MedicalProduct medicalProduct = new MedicalProduct(goodsName, price, origin);

        BigDecimal salesTaxes = medicalProduct.calculateSalesTaxes();

        assertThat(salesTaxes).isEqualTo("0.75");
    }
}