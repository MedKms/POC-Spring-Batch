package expleo.ma.springbatch.config;

import expleo.ma.springbatch.entites.Student;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class StudentItemProcessor implements ItemProcessor<Student,Student> {
    @Override
    public Student process(Student student) throws Exception {
        return student;
    }
}
