package ro.bogdan.onlineshop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductDataDao implements ProductDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDataDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> selectAllProducts() {
        String sql = "" +
                "SELECT " +
                " product_id, " +
                " name, " +
                " description, " +
                " price, " +
                " gender " +
                "FROM product";
        return jdbcTemplate.query(sql, mapProductFromDb());
    }

    private RowMapper<Product> mapProductFromDb() {
        return (resultSet, i) -> {
            int studentId = resultSet.getInt("product_id");
            String firstName = resultSet.getString("name");
            String lastName = resultSet.getString("description");
            double email = resultSet.getDouble("price");

            String genderStr = resultSet.getString("gender").toUpperCase();
            Product.Gender gender = Product.Gender.valueOf(genderStr);
            return new Product(
                    studentId, firstName, lastName, email, gender
            );
        };
    }

//    @Override
//    public Optional<Product> selectProductById(int id) {
//        return selectAllProducts()
//                .stream()
//                .filter(product -> product.getProductId() == id)
//                .findFirst();
//    }

    @Override
    public int insertProduct(Product product) {
        return 0;
    }

    @Override
    public int updateProduct(int id, Product product) {
        return 0;
    }

    @Override
    public int deleteProduct(int id) {
        return 0;
    }
}
