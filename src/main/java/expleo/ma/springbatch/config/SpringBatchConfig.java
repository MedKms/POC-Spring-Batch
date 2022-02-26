package expleo.ma.springbatch.config;

import expleo.ma.springbatch.entites.Student;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;


@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    private static final String STEP_NAME="myStep";
    private static final String JOB_NAME="myJob";
    private static final String FLAT_FILE_NAME="flatFileItemReader";

    @Value("${line.delimiter}")
    private String delimiter;


    @Autowired private JobBuilderFactory jobBuilderFactory;
    @Autowired private StepBuilderFactory stepBuilderFactory;
    @Autowired private ItemReader<Student> studentItemReader;
    @Autowired private ItemWriter<Student> studentItemWriter;
    @Autowired private ItemProcessor<Student,Student> studentItemProcessor;

    @Bean
    public Job studentJob(){
        Step step=stepBuilderFactory.get(STEP_NAME)
                .<Student,Student>chunk(5)
                .reader(studentItemReader)
                .processor(studentItemProcessor)
                .writer(studentItemWriter)
                .build();
        return jobBuilderFactory.get(JOB_NAME)
                .start(step).build();
    }

    @Bean
    public FlatFileItemReader<Student> fileItemReader(@Value("${inputFile}") Resource inputFile){
        FlatFileItemReader<Student> fileItemReader=new FlatFileItemReader<>();
        fileItemReader.setName(FLAT_FILE_NAME);
        fileItemReader.setLinesToSkip(1);
        fileItemReader.setResource(inputFile);
        fileItemReader.setLineMapper(lineMapper());
        return fileItemReader;

    }
    @Bean
    public LineMapper<Student> lineMapper() {
        DefaultLineMapper<Student> lineMapper=new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(delimiter);
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id","firstName","lastName","email","age");
        lineMapper.setLineTokenizer(lineTokenizer);
        BeanWrapperFieldSetMapper<Student> fieldSetMapper=new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Student.class);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

}
