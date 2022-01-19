package bach.sales.taxes.repository.mapper;

import static bach.sales.taxes.modell.Origin.IMPORTED;
import static bach.sales.taxes.modell.Origin.NOTIMPORTED;

import bach.sales.taxes.modell.Book;
import bach.sales.taxes.modell.Food;
import bach.sales.taxes.modell.Goods;
import bach.sales.taxes.modell.Others;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings({"PMD.AtLeastOneConstructor", "PMD.StdCyclomaticComplexity", "PMD.ModifiedCyclomaticComplexity"})
public class GoodsRowMapper implements RowMapper<Goods> {

    @Override
    @SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.AvoidDuplicateLiterals"})
    public Goods mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
        Goods goods = null;
        final String produktName = resultSet.getString("category");

        if ("others".equals(produktName)) {
            goods = new Others(
                    resultSet.getString("goods_name"),
                    resultSet.getBigDecimal("price"),
                    Objects.equals(resultSet.getString("origin"), "imported") ? IMPORTED : NOTIMPORTED
            );
        }
        if ("books".equals(produktName)) {
            goods = new Book(
                    resultSet.getString("goods_name"),
                    resultSet.getBigDecimal("price"),
                    Objects.equals(resultSet.getString("origin"), "imported") ? IMPORTED : NOTIMPORTED
            );
        }
        if ("food".equals(produktName)) {
            goods = new Food(
                    resultSet.getString("goods_name"),
                    resultSet.getBigDecimal("price"),
                    Objects.equals(resultSet.getString("origin"), "imported") ? IMPORTED : NOTIMPORTED
            );
        }
        if ("medicalProducts".equals(produktName)) {
            goods = new Food(
                    resultSet.getString("goods_name"),
                    resultSet.getBigDecimal("price"),
                    Objects.equals(resultSet.getString("origin"), "imported") ? IMPORTED : NOTIMPORTED
            );
        }
        return goods;
    }
}
