package bach.sales.taxes.controller;

import static bach.sales.taxes.modell.Origin.IMPORTED;
import static bach.sales.taxes.modell.Origin.NOTIMPORTED;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import bach.sales.taxes.form.GoodsForm;
import bach.sales.taxes.modell.Book;
import bach.sales.taxes.modell.Goods;
import bach.sales.taxes.modell.Origin;
import bach.sales.taxes.service.GoodsService;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = GoodsController.class)
class GoodsControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    GoodsService goodsService;

    @Test
    void getIndex() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("goodsForm", new GoodsForm(null, null, null, "0.0")))
                .andExpect(content().string(containsString("Problem 1: Sales Taxes")));

        verify(goodsService).findAllGoods();
        verify(goodsService).calculateSalesTaxes();
        verify(goodsService).calculateTotalPrice();
    }

    @Test
    void postAddGoodsValid() throws Exception {
        mvc.perform(post("/addGoods")
                        .param("goodsName", "chocolate bar")
                        .param("category", "food")
                        .param("origin", "NOTIMPORTED")
                        .param("price", "10.0"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"))
                .andExpect(view().name("redirect:/"));

        verify(goodsService).saveGoods("chocolate bar", "food", NOTIMPORTED, BigDecimal.valueOf(10.0));
    }

    @Test
    void postAddGoodsInvalid() throws Exception {
        mvc.perform(post("/addGoods")
                        .param("goodsName", "")
                        .param("category", "")
                        .param("origin", "")
                        .param("price", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(content().string(containsString("Problem 1: Sales Taxes")))
                .andExpect(content().string(containsString("The name must not be empty!")))
                .andExpect(content().string(containsString("The number must be a decimal number! Do not forget the dot!")))
                .andExpect(content().string(containsString("The price must be positive!")))
                .andExpect(content().string(not(containsString("New goods was added: chocolate bar, food, NOTIMPORTED, 10.0"))));

        verify(goodsService, never()).saveGoods(anyString(), anyString(), any(Origin.class), any(BigDecimal.class));
    }

    @Test
    void postDeleteGoods() throws Exception {
        long id = 1;
        String goodsName = "Harry Potter";
        BigDecimal price = BigDecimal.valueOf(12.49);
        Origin origin = NOTIMPORTED;
        Book book = new Book(id, goodsName, price, origin);

        when(goodsService.findAllGoods()).thenReturn(List.of(book));

        mvc.perform(post("/deleteGoods/{id}", id))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"))
                .andExpect(view().name("redirect:/"));

        verify(goodsService).deleteGoodsById(id);
    }

    @Test
    void postAddGoodsByInputStringValid() throws Exception {
        mvc.perform(post("/addGoodsByInputString")
                        .param("inputString", "3 imported bottle of perfume at 27.99"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"))
                .andExpect(view().name("redirect:/"));

        verify(goodsService).addGoodsByInputString("3 imported bottle of perfume at 27.99");
    }

    @Test
    void postAddGoodsByInputStringInvalid() throws Exception {
        mvc.perform(post("/addGoodsByInputString")
                        .param("inputString", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(content().string(containsString("[1-9]+\\s([a-zA-Z]+\\s)+at\\s\\d+\\.\\d+")));

        verify(goodsService, never()).addGoodsByInputString(anyString());
    }
}