package intellistart.marketplace.services;

import intellistart.marketplace.model.product.Product;
import intellistart.marketplace.model.user.User;
import intellistart.marketplace.model.user_product.UserProduct;
import intellistart.marketplace.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private UserProductService userProductService;

    @Autowired
    public ProductService(ProductRepository productRepository, UserProductService userProductService) {
        this.productRepository = productRepository;
        this.userProductService = userProductService;
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProductById(Long productId) throws IllegalArgumentException{
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            return product.get();
        }

        throw new IllegalArgumentException("Product with id " + productId + " was not found");
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByUser(User user) {
        List<UserProduct> userProductsByUser = userProductService.getUserProductsByUser(user);
        List<Product> productsByUser = userProductsByUser.stream()
                .map(UserProduct::getProduct)
                .toList();
        return productsByUser;
    }

    public void deleteProduct(Product product) {
        userProductService.deleteUserProductsByProduct(product);
        productRepository.delete(product);
    }
}
