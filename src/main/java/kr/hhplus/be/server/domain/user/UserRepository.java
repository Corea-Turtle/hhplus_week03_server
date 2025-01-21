package kr.hhplus.be.server.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository{
    Optional<User> findById(Long id);
    List<User> findAll();
    void save(User user);
}
