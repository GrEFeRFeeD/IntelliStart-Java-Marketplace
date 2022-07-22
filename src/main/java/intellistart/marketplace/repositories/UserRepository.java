package intellistart.marketplace.repositories;

import intellistart.marketplace.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
