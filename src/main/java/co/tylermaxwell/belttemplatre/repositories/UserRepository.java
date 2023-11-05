package co.tylermaxwell.belttemplatre.repositories;

import co.tylermaxwell.belttemplatre.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
