package intellistart.marketplace.model.user_product;

import intellistart.marketplace.model.product.Product;
import intellistart.marketplace.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProduct {

    @EmbeddedId
    private UserProductKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @NotNull(message = "User is mandatory")
    private User user;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    @NotNull(message = "Product is mandatory")
    private Product product;

    @NotNull(message = "Count is mandatory")
    @Min(value = 1, message = "Count value must not be lower than 1")
    private int count;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProduct that = (UserProduct) o;
        return count == that.count && id.equals(that.id) && user.equals(that.user) && product.equals(that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, product, count);
    }
}
