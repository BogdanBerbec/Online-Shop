package ro.bogdan.onlineshop.product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    List<Product> selectAllProducts();

//    Optional<Product> selectProductById(int id);

    int insertProduct(Product product);

    int updateProduct(int id, Product product);

    int deleteProduct(int id);
}
