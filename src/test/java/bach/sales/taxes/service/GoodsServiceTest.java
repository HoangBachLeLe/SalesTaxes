package bach.sales.taxes.service;

import static bach.sales.taxes.modell.Origin.IMPORTED;
import static bach.sales.taxes.modell.Origin.NOTIMPORTED;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import bach.sales.taxes.modell.Origin;
import bach.sales.taxes.repository.GoodsRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GoodsServiceTest {

    @Mock
    private GoodsRepository goodsRepository;

    @InjectMocks
    private GoodsService goodsService;

    @Test
    void findAllGoods() {
        goodsService.findAllGoods();

        verify(goodsRepository).findAllGoods();
    }

    @Test
    void calculateSalesTaxes() {
        goodsService.calculateSalesTaxes();

        verify(goodsRepository).findAllGoods();
    }

    @Test
    void calculateTotalPrice() {
        goodsService.calculateTotalPrice();

        verify(goodsRepository).findAllGoods();
    }

    @Test
    void saveGoods() {
        String goodsName = "chocolate bar";
        String category = "food";
        Origin origin = IMPORTED;
        BigDecimal price = BigDecimal.valueOf(1.5);

        goodsService.saveGoods(goodsName, category, origin, price);

        verify(goodsRepository).saveGoods(goodsName, category, origin, price);
    }

    @Test
    void deleteGoodsById() {
        long goodsId = 1L;

        goodsService.deleteGoodsById(goodsId);

        verify(goodsRepository).deleteGoodsById(goodsId);
    }

    @Test
    void addGoodsByInputString1() {
        String inputString = "1 imported bottle of perfume at 47.50";

        goodsService.addGoodsByInputString(inputString);

        verify(goodsRepository).saveGoods("imported bottle of perfume", "others", IMPORTED, BigDecimal.valueOf(47.50));
    }

    @DisplayName("Add 8 goods")
    @Test
    void addGoodsByInputString2() {
        String inputString = "8 box of chocolates at 10.00";

        goodsService.addGoodsByInputString(inputString);

        verify(goodsRepository, times(8))
                .saveGoods("box of chocolates", "food", NOTIMPORTED, BigDecimal.valueOf(10.00));
    }
}