package bach.sales.taxes.service;

import bach.sales.taxes.modell.Goods;
import bach.sales.taxes.modell.Origin;
import bach.sales.taxes.repository.GoodsRepository;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GoodsService {

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    private final GoodsRepository goodsRepository;

    public List<Goods> findAllGoods() {
        return goodsRepository.findAllGoods();
    }

    public BigDecimal calculateSalesTaxes() {
        return this.findAllGoods()
                .stream()
                .map(Goods::calculateSalesTaxes)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateTotalPrice() {
        return this.findAllGoods()
                .stream()
                .map(Goods::calculatePriceWithTax)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void saveGoods(final String goodsName, final String category, final Origin origin, final BigDecimal price) {
        this.goodsRepository.saveGoods(goodsName, category, origin, price);
    }

    public void deleteGoodsById(final long goodsId) {
        this.goodsRepository.deleteGoodsById(goodsId);
    }

    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public void addGoodsByInputString(final String inputString) {
        final List<String> strings = new ArrayList<>(Arrays.asList(inputString.split(" ")));

        Origin origin;
        if (strings.contains("imported")) {
            origin = Origin.IMPORTED;
        } else {
            origin = Origin.NOTIMPORTED;
        }

        final BigDecimal price = BigDecimal.valueOf(Double.parseDouble(strings.get(strings.size() - 1)));

        strings.remove(strings.size() - 1);
        strings.remove(strings.size() - 1);

        final int quantity = Integer.parseInt(strings.remove(0));

        final String goodsName = String.join(" ", strings);

        final String category = this.calculateCategory(goodsName);

        for (int i = 0; i < quantity; i++) {
            this.goodsRepository.saveGoods(goodsName, category, origin, price);
        }
    }

    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    private String calculateCategory(final String goodsName) {
        String category;
        if (goodsName.contains("book")) {
            category = "books";
        } else if (goodsName.contains("chocolate") || goodsName.contains("chocolates")) {
            category = "food";
        } else if (goodsName.contains("headache")) {
            category = "medicalProducts";
        } else {
            category = "others";
        }

        return category;
    }
}
