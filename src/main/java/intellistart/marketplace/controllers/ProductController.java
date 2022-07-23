package intellistart.marketplace.controllers;

import intellistart.marketplace.model.product.Product;
import intellistart.marketplace.model.user.User;
import intellistart.marketplace.services.ProductService;
import intellistart.marketplace.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping(value = "/products", params = "userId")
    public ResponseEntity<?> getProductsByUser(@RequestParam(name = "userId") Long userId,
                                               @RequestParam(name = "withCount", required = false) String withCount) {

        User user;
        try {
            user = userService.getUserById(userId);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

        if (withCount == null) {
            return ResponseEntity.ok(productService.getProductsByUser(user));
        } else {
            return ResponseEntity.ok(productService.getProductsByUserWithCount(user));
        }
    }

    @PostMapping("/products")
    public ResponseEntity<String> addProduct(@Valid @RequestBody Product product) {

        product.setId(null);
        productService.saveProduct(product);
        return ResponseEntity.ok("Product " + product.getName() + " was successfully added");
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") Long productId) {

        Product product;
        try {
            product = productService.getProductById(productId);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

        productService.deleteProduct(product);
        return ResponseEntity.ok("Product " + product.getName() + " was successfully deleted");
    }
}
