package ehtp.tp.architecturemonolithique.architecture_monolithique.repos;

import ehtp.tp.architecturemonolithique.architecture_monolithique.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Integer> {
}
