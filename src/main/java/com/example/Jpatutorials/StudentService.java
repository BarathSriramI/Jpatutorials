package com.example.Jpatutorials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public String addStudent(Student student)
    {
        studentRepository.save(student);
        return "Student added successfully";
    }

    public Student getstudent(int rollNo)
    {
       Optional<Student> student = studentRepository.findById(rollNo);

       if(student.isPresent()) return student.get();
       return null;
    }

    public boolean deletestudent(int rollNo)
    {
        boolean isExists= studentRepository.existsById(rollNo);

        if(isExists)
        {
            studentRepository.deleteById(rollNo);
            return true;
        }
        return false;
    }

    public Student updateAge(int rollNo, int age)
    {
        boolean isExists= studentRepository.existsById(rollNo);

        if(isExists)
        {
            Optional<Student> student= studentRepository.findById(rollNo);
            Student s =student.get();
            s.setAge(age);

            studentRepository.save(s);
            Optional<Student> student1= studentRepository.findById(rollNo);
            return student1.get();
        }
        return null;
    }

    public String deleteAll() {
        studentRepository.deleteAll();
        return "Deleted all rollNo";
    }
}
