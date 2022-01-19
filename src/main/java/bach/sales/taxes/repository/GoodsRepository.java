package bach.sales.taxes.repository;

import bach.sales.taxes.modell.Goods;
import bach.sales.taxes.repository.mapper.GoodsRowMapper;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class GoodsRepository {

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    private final JdbcTemplate jdbc;

    @SuppressWarnings("PMD.AvoidFinalLocalVariable")
    public List<Goods> findAllGoods() {
        final String sql = "SELECT * FROM goods";
        return jdbc.query(sql, new GoodsRowMapper());
    }
}
