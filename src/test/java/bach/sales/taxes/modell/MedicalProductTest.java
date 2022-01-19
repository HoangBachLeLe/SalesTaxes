package bach.sales.taxes.modell;

import static bach.sales.taxes.modell.Origin.IMPORTED;
import static bach.sales.taxes.modell.Origin.NOTIMPORTED;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MedicalProductTest {

    @Test
    void validInstantiation() {
        String goodsName = "packet of headache pills";
        BigDecimal price = BigDecimal.valueOf(0.85);
        Origin origin = NOTIMPORTED;
        MedicalProduct medicalProduct = new MedicalProduct(goodsName, price, origin);

        assertThat(medicalProduct).isNotNull();
        assertThat(medicalProduct.getGoodsName()).isEqualTo(goodsName);
        assertThat(medicalProduct.getPrice()).isEqualTo(price);
        assertThat(medicalProduct.getOrigin()).isEqualTo(origin);
    }

    @DisplayName("MedicalProduct not imported")
    @Test
    void calculateImportTax1() {
        String goodsName = "packet of headache pills";
        BigDecimal price = BigDecimal.valueOf(0.85);
        Origin origin = NOTIMPORTED;
        MedicalProduct medicalProduct = new MedicalProduct(goodsName, price, origin);

        BigDecimal salesTaxes = medicalProduct.calculateSalesTaxes();

        assertThat(salesTaxes).isEqualTo("0.00");
    }

    @DisplayName("MedicalProduct imported")
    @Test
    void calculateImportTax2() {
        String goodsName = "packet of headache pills";
        BigDecimal price = BigDecimal.valueOf(0.85);
        Origin origin = IMPORTED;
        MedicalProduct medicalProduct = new MedicalProduct(goodsName, price, origin);

        BigDecimal salesTaxes = medicalProduct.calculateSalesTaxes();

        assertThat(salesTaxes).isEqualTo(BigDecimal.valueOf(0.05));
    }

}