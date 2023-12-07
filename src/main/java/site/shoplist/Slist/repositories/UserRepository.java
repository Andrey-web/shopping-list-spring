package site.shoplist.Slist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import site.shoplist.Slist.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
}
