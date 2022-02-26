package expleo.ma.springbatch.repositories;

import expleo.ma.springbatch.entites.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {

}
