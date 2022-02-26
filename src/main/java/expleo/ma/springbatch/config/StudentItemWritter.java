package expleo.ma.springbatch.config;

import expleo.ma.springbatch.entites.Student;
import expleo.ma.springbatch.repositories.StudentRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class StudentItemWritter implements ItemWriter<Student> {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void write(List<? extends Student> list) throws Exception {
        studentRepository.saveAll(list);
    }
}
