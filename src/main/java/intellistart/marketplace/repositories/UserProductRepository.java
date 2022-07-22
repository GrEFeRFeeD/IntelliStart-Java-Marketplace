package intellistart.marketplace.repositories;

import intellistart.marketplace.model.product.Product;
import intellistart.marketplace.model.user.User;
import intellistart.marketplace.model.user_product.UserProduct;
import intellistart.marketplace.model.user_product.UserProductKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProductRepository extends JpaRepository<UserProduct, UserProductKey> {
    List<UserProduct> findUserProductsByProduct(Product product);
    List<UserProduct> findUserProductsByUser(User user);
}
