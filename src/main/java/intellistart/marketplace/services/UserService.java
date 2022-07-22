package intellistart.marketplace.services;

import intellistart.marketplace.exceptions.NotEnoughBalanceException;
import intellistart.marketplace.model.product.Product;
import intellistart.marketplace.model.user.User;
import intellistart.marketplace.model.user_product.UserProduct;
import intellistart.marketplace.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserProductService userProductService;

    @Autowired
    public UserService(UserRepository userRepository, UserProductService userProductService) {
        this.userRepository = userRepository;
        this.userProductService = userProductService;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUserById(Long userId) throws IllegalArgumentException{
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }

        throw new IllegalArgumentException("User with id " + userId + " was not found");
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersByProduct(Product product) {
        List<UserProduct> userProductsByProduct = userProductService.getUserProductsByProduct(product);
        List<User> usersByProduct = userProductsByProduct.stream()
                .map(UserProduct::getUser)
                .toList();
        return usersByProduct;
    }

    public void purchaseProduct(User user, Product product) throws NotEnoughBalanceException {
        if (user.getBalance() < product.getPrice()) {
            throw new NotEnoughBalanceException("User with id " + user.getId() + " have not enough balance ("
                    + user.getBalance() + ") to purchase product with id " + product.getId() + " which costs "
                    + product.getPrice());
        }

        user.setBalance(user.getBalance() - product.getPrice());
        userProductService.addUserProduct(user, product);
        saveUser(user);
    }

    public void deleteUser(User user) {
        userProductService.deleteUserProductsByUser(user);
        userRepository.delete(user);
    }
}
