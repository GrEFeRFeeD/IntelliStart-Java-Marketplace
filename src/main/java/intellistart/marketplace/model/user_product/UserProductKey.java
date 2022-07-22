package intellistart.marketplace.model.user_product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProductKey {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "product_id")
    private Long productId;

}
