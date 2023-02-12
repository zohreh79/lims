package e.hospital.lims.dao;

import e.hospital.lims.domain.Doctor;
import e.hospital.lims.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorDao extends JpaRepository<Doctor, Long> {

    Doctor findByUser(User user);
}
