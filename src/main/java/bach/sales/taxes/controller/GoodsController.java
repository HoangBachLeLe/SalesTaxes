package bach.sales.taxes.controller;

import bach.sales.taxes.form.GoodsForm;
import bach.sales.taxes.modell.Origin;
import bach.sales.taxes.service.GoodsService;
import java.math.BigDecimal;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@Controller
public class GoodsController {

    private final GoodsService goodsService;

    @GetMapping("/")
    public String getIndex(final Model model) {
        model.addAttribute("goodsList", goodsService.findAllGoods());
        model.addAttribute("salesTaxes", goodsService.calculateSalesTaxes());
        model.addAttribute("totalPrice", goodsService.calculateTotalPrice());
        model.addAttribute("goodsForm", new GoodsForm(null, null, null, "0.0"));
        return "index";
    }

    @SuppressWarnings("PMD.OnlyOneReturn")
    @PostMapping("/addGoods")
    public String postAddGoods(@Valid final GoodsForm form, final BindingResult bindingResult, final Model model,
            final RedirectAttributes redirectAttri) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("goodsList", goodsService.findAllGoods());
            return "index";
        }

        goodsService.saveGoods(
                form.getGoodsName(),
                form.getCategory(),
                Origin.valueOf(form.getOrigin()),
                new BigDecimal(form.getPrice())
        );

        redirectAttri.addFlashAttribute("newGoodsAdded",
                "New goods was added: "
                        + form.getGoodsName() + ", "
                        + form.getCategory() + ", "
                        + form.getOrigin() + ", "
                        + form.getPrice()
        );
        return "redirect:/";
    }
}
