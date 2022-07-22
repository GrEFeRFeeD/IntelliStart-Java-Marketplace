package intellistart.marketplace.controllers;

import intellistart.marketplace.exceptions.NotEnoughBalanceException;
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
public class UserController {

    private UserService userService;
    private ProductService productService;

    @Autowired
    public UserController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users/{id}/purchase")
    public ResponseEntity<String> purchaseProduct(@PathVariable(name = "id") Long userId,
                                                  @RequestBody Long productId) {
        User user;
        try {
            user = userService.getUserById(userId);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

        Product product;
        try {
            product = productService.getProductById(productId);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

        try {
            userService.purchaseProduct(user, product);
        } catch (NotEnoughBalanceException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

        return ResponseEntity.ok("Purchase successful");
    }

    @GetMapping(value = "/users", params = "productId")
    public ResponseEntity<?> getUsersByProduct(@RequestParam(name = "productId") Long productId) {

        Product product;
        try {
            product = productService.getProductById(productId);
        } catch (NotEnoughBalanceException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }


    }
}
