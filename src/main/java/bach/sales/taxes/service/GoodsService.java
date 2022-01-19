package bach.sales.taxes.service;

import bach.sales.taxes.modell.Goods;
import bach.sales.taxes.repository.GoodsRepository;
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
}
