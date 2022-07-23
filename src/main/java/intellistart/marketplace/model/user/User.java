package intellistart.marketplace.model.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import intellistart.marketplace.model.user_product.UserProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long id;

    @NotNull(message = "First name is mandatory")
    @NotBlank(message = "First name is mandatory")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Last name is mandatory")
    @NotBlank(message = "Last name is mandatory")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Balance is mandatory")
    @Min(value = 0, message = "Balance value must not be lower than 0")
    private double balance;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<UserProduct> userProducts = new HashSet<>();

    public void addUserProduct(UserProduct userProduct) {
        userProducts.add(userProduct);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Double.compare(user.balance, balance) == 0 && id.equals(user.id) && firstName.equals(user.firstName) && lastName.equals(user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, balance);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
