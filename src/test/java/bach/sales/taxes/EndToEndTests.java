package bach.sales.taxes;

import static java.util.Map.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EndToEndTests {

    @Autowired
    TestRestTemplate template;

    @Test
    void testWorkflow() {
        ResponseEntity<String> response;

        // check goods in database
        // make sure headache pills is not in table
        response = this.template.getForEntity("/", String.class);
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody())
                .contains("Sales Taxes")
                .contains("chocolate bar")
                .contains("Food")
                .contains("no")
                .contains("0.85")
                .doesNotContain("headache pills");

        // add headache pills to table
        response = this.template.postForEntity(
                "/addGoods",
                formData(of(
                        "goodsName", "headache pills",
                        "category", "medicalProducts",
                        "origin", "IMPORTED",
                        "price", "12.2"
                )),
                String.class
        );
        assertThat(response.getStatusCode()).isEqualTo(FOUND);

        // check if headache pills is in table
        response = this.template.getForEntity("/", String.class);
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody())
                .contains("headache pills")
                .contains("12.85");

        // delete headache pills
        long goodsId = 4;
        response = template.postForEntity(
                "/deleteGoods/{id}", null, null, goodsId
        );
        assertThat(response.getStatusCode()).isEqualTo(FOUND);

        // check if headache pills is not in table
        response = this.template.getForEntity("/", String.class);
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody())
                .doesNotContain("headache pills");

        // add imported box of white chocolates to table
        response = this.template.postForEntity(
                "/addGoodsByInputString",
                formData(of(
                        "inputString", "1 imported box of white chocolates at 11.11"
                )),
                String.class
        );
        assertThat(response.getStatusCode()).isEqualTo(FOUND);

        // check if imported box of chocolates is in table
        response = this.template.getForEntity("/", String.class);
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody())
                .contains("imported box of white chocolates")
                .contains("11.71");
    }

    @Test
    void notFound() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.TEXT_HTML));
        HttpEntity<?> request = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = template.exchange("/invalidPage", HttpMethod.GET, request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
        assertThat(response.getBody())
                .contains("An error has occurred!")
                .contains("Go back to the main page.");
    }

    private static MultiValueMap<String, String> formData(Map<String, String> form) {
        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        form.forEach(request::add);
        return request;
    }
}
