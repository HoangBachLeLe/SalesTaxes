package bach.sales.taxes.modell;

import static bach.sales.taxes.modell.Origin.IMPORTED;
import static bach.sales.taxes.modell.Origin.NOTIMPORTED;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BookTest {

    @Test
    void validInstantiation() {
        long id = 1;
        String goodsName = "Harry Potter";
        BigDecimal price = BigDecimal.valueOf(12.49);
        Origin origin = NOTIMPORTED;
        Book book = new Book(id, goodsName, price, origin);

        assertThat(book).isNotNull();
        assertThat(book.getGoodsName()).isEqualTo(goodsName);
        assertThat(book.getPrice()).isEqualTo(price);
        assertThat(book.getOrigin()).isEqualTo(origin);
    }

    @DisplayName("Book not imported")
    @Test
    void calculateImportTax1() {
        long id = 1;
        String goodsName = "Harry Potter";
        BigDecimal price = BigDecimal.valueOf(12.49);
        Origin origin = NOTIMPORTED;
        Book book = new Book(id, goodsName, price, origin);

        BigDecimal salesTaxes = book.calculateSalesTaxes();

        assertThat(salesTaxes).isEqualTo("0.00");
    }

    @DisplayName("Book imported")
    @Test
    void calculateImportTax2() {
        long id = 1;
        String goodsName = "Harry Potter";
        BigDecimal price = BigDecimal.valueOf(12.49);
        Origin origin = IMPORTED;
        Book book = new Book(id, goodsName, price, origin);

        BigDecimal salesTaxes = book.calculateSalesTaxes();

        assertThat(salesTaxes).isEqualTo(BigDecimal.valueOf(0.65));
    }
}