package restapi_datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restapi_datajpa.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
