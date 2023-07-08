package com.example.Jpatutorials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class Studentcontroller {

    @Autowired
    StudentService studentService;
    @PostMapping("/addstudent")
    public ResponseEntity addStudent(@RequestBody Student student )
    {
        String response =studentService.addStudent(student);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getstudent")
    public ResponseEntity getstudent( @RequestParam("rollNo") int rollNo)
    {
        Student student = studentService.getstudent(rollNo);

        if(student==null) return new ResponseEntity<>("Invalid RollNo",HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(student,HttpStatus.FOUND);
    }

    //  delete a student by rollNo

    @DeleteMapping("/deletestudent")
    public ResponseEntity deletestudent(@RequestParam("rollNo") int rollNo)
    {
        boolean rollnoexists= studentService.deletestudent(rollNo);

        if(rollnoexists) return new ResponseEntity<>("rollNo deleted",HttpStatus.OK);

        return new ResponseEntity<>("RollNo not found",HttpStatus.NOT_FOUND);
    }

    // update a age of the student by giving a rollNo
    @PutMapping("/updateAge")
    public ResponseEntity updateAge (@RequestParam("rollNo") int rollNo,@RequestParam("age") int age)
    {
        Student student =studentService.updateAge(rollNo,age);

        if(student ==null) return new ResponseEntity<>("RollNo not found",HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }

    //find  the  student whose age is >25



    //delete all the students
    @DeleteMapping("/deleteAll")
    public ResponseEntity deleteAll()
    {
        studentService.deleteAll();
        return new ResponseEntity<>("Deleted all RollNo",HttpStatus.OK);
    }

    // find all the students with the same name
}
