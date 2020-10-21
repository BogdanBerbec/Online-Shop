package ro.bogdan.onlineshop.product;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

//    public static final List<Product> PRODUCTS = Lists.newArrayList(
//            new Product(1, "Geaca", "smechera", 130.35, Product.Gender.MALE),
//            new Product(2, "Blugi", "slim fit", 170, Product.Gender.BOY),
//            new Product(3, "Tricou", "larg ", 60, Product.Gender.GIRL)
//    );

    private final ProductDataDao productDataDao;

    @Autowired
    public ProductService(ProductDataDao productDataDao) {
        this.productDataDao = productDataDao;
    }

    public List<Product> getAllProducts() {
        return productDataDao.selectAllProducts();
    }

    public Product getProductById(int id) {
         return productDataDao.selectAllProducts()
                .stream()
                .filter(product -> product.getProductId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("Product with id %s not found", id)));
    }
}
