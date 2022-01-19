package bach.sales.taxes.controller;

import bach.sales.taxes.service.GoodsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class GoodsController {

    private final GoodsService goodsService;

    @GetMapping("/")
    public String getIndex(final Model model) {
        model.addAttribute("goodsList", goodsService.findAllGoods());
        return "index";
    }
}
