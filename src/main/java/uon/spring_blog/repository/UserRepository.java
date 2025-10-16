package uon.spring_blog.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uon.spring_blog.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
     Optional<User> findByEmail(String email);
}
