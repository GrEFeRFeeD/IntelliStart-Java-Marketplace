package intellistart.marketplace.repositories;

import intellistart.marketplace.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
