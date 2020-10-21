package ro.bogdan.onlineshop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ro.bogdan.onlineshop.authentication.AppUser;
import ro.bogdan.onlineshop.authentication.AppUserService;
import ro.bogdan.onlineshop.security.AppUserRole;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;
    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ProductController(ProductService productService, AppUserService appUserService, PasswordEncoder passwordEncoder) {
        this.productService = productService;
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("products")
    public ModelAndView getAllProducts() {
        ModelAndView modelAndView = new ModelAndView("products.html");
        List<Product> allProducts = productService.getAllProducts();
        modelAndView.addObject("products", allProducts);
        modelAndView.addObject("totalProducts", allProducts.size());
        return modelAndView;
    }

    @GetMapping("products/{id}")
    @ResponseBody
    public Product getProductById(@PathVariable("id") int id) {
        return productService.getProductById(id);
    }

    @GetMapping("/login")
    public ModelAndView modelAndView() {
//        appUserService.insertUser(new AppUser(
//                1,
//                "bogdanoctavian",
//                passwordEncoder.encode("password"),
//                AppUserRole.MANAGER.getGrantedAuthorities(),
//                true,
//                true,
//                true,
//                true
//        ));
//        appUserService.insertUser(new AppUser(
//                2,
//                "andreipopescu",
//                passwordEncoder.encode("password"),
//                AppUserRole.ADMIN.getGrantedAuthorities(),
//                true,
//                true,
//                true,
//                true
//        ));
//        appUserService.insertUser(new AppUser(
//                3,
//                "alexioan",
//                passwordEncoder.encode("password"),
//                AppUserRole.USER.getGrantedAuthorities(),
//                true,
//                true,
//                true,
//                true
//        ));
//        System.out.println(appUserService.selectUserByUserId(1));
        return new ModelAndView("login.html");
    }
}
