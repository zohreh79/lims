package e.hospital.lims.dao;

import e.hospital.lims.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    User findUserByUsername(String username);
}
