package web.app.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.app.entity.User;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, String>{
    List<User> findByUsernameNotLike(String username);
}
