package bach.sales.taxes.service;

import static org.mockito.Mockito.verify;

import bach.sales.taxes.repository.GoodsRepository;
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
}