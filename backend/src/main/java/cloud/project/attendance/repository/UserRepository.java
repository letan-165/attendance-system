package cloud.project.attendance.repository;

import cloud.project.attendance.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByUsername(String name);
    boolean existsByUsername(String name);
}
