package bach.sales.taxes.controller;

import bach.sales.taxes.form.GoodsForm;
import bach.sales.taxes.form.InputStringForm;
import bach.sales.taxes.modell.Origin;
import bach.sales.taxes.service.GoodsService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.math.BigDecimal;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@Controller
public class GoodsController {

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    private final GoodsService goodsService;

    @GetMapping("/")
    public String getIndex(final Model model) {
        model.addAttribute("goodsList", goodsService.findAllGoods());
        model.addAttribute("salesTaxes", goodsService.calculateSalesTaxes());
        model.addAttribute("totalPrice", goodsService.calculateTotalPrice());
        model.addAttribute("goodsForm", new GoodsForm("", "", "", "0.0"));
        model.addAttribute("inputStringForm", new InputStringForm("1 imported box of chocolates at 10.00"));
        return "index";
    }

    @SuppressWarnings("PMD.OnlyOneReturn")
    @PostMapping("/addGoods")
    public String postAddGoods(@Valid final GoodsForm form, final BindingResult bindingResult, final Model model,
            final RedirectAttributes redirectAttri) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("inputStringForm", new InputStringForm("1 imported box of chocolates at 10.00"));
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

    @PostMapping("/deleteGoods/{id}")
    public String postDeleteGoods(@PathVariable("id") final int goodsId) {
        goodsService.deleteGoodsById(goodsId);
        return "redirect:/";
    }

    @SuppressWarnings("PMD.OnlyOneReturn")
    @PostMapping("/addGoodsByInputString")
    public String addGoodsByInputString(@Valid final InputStringForm form, final BindingResult bindingResult, final Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("goodsForm", new GoodsForm("", "", "", "0.0"));
            model.addAttribute("goodsList", goodsService.findAllGoods());
            return "index";
        }
        goodsService.addGoodsByInputString(form.getInputString());
        return "redirect:/";
    }
}
