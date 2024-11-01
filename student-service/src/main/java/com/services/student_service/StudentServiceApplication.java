package com.services.student_service;

import com.services.student_service.domain.model.Student;
import com.services.student_service.domain.model.StudentString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class StudentServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		StudentString studentString = new StudentString("1", "hola", null, null, null);
		log.info("StudentString {}", studentString);

		Student student = fromStundentStringToStudent(studentString);

		log.info("Student {}", student.toString());


	}

	public Student fromStundentStringToStudent(StudentString string){
		return Student.build()
				.id(string.getId())
				.firstname(string.getFirstname())
				.lastname(string.getLastname())
				.age(string.getAge())
				.address(string.getAddress())
				.build();



	}
}
