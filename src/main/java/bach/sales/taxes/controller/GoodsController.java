package bach.sales.taxes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SuppressWarnings("PMD.AtLeastOneConstructor")
@Controller
public class GoodsController {

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }
}
