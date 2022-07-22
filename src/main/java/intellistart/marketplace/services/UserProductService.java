package intellistart.marketplace.services;

import intellistart.marketplace.model.product.Product;
import intellistart.marketplace.model.user.User;
import intellistart.marketplace.model.user_product.UserProduct;
import intellistart.marketplace.model.user_product.UserProductKey;
import intellistart.marketplace.repositories.UserProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserProductService {

    private UserProductRepository userProductRepository;

    @Autowired
    public UserProductService(UserProductRepository userProductRepository) {
        this.userProductRepository = userProductRepository;
    }

    public void saveUserProduct(UserProduct userProduct) {
        userProductRepository.save(userProduct);
    }

    public void addUserProduct(User user, Product product) {
        UserProductKey userProductKey = new UserProductKey(user.getId(), product.getId());
        Optional<UserProduct> userProductOptional = userProductRepository.findById(userProductKey);

        UserProduct userProduct;
        if (userProductOptional.isPresent()) {
            userProduct = userProductOptional.get();
            userProduct.setCount(userProduct.getCount() + 1);
        } else {
            userProduct = new UserProduct(userProductKey, user, product, 1);
        }
        saveUserProduct(userProduct);
    }

    public List<UserProduct> getUserProductsByProduct(Product product) {
        return userProductRepository.findUserProductsByProduct(product);
    }

    public List<UserProduct> getUserProductsByUser(User user) {
        return userProductRepository.findUserProductsByUser(user);
    }

    public void deleteUserProductsByUser(User user) {
        List<UserProduct> userProducts = userProductRepository.findUserProductsByUser(user);
        userProductRepository.deleteAll(userProducts);
    }

    public void deleteUserProductsByProduct(Product product) {
        List<UserProduct> userProducts = userProductRepository.findUserProductsByProduct(product);
        userProductRepository.deleteAll(userProducts);
    }

}
