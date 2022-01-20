package bach.sales.taxes.service;

import bach.sales.taxes.modell.Goods;
import bach.sales.taxes.modell.Origin;
import bach.sales.taxes.repository.GoodsRepository;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GoodsService {

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
}
