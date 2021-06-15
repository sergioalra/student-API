package com.sergl.appst.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/student")
public class StudentController {

    private final StudentService studentService;
    //Dependency Injection
    @Autowired
    public StudentController(StudentService studentService_) {
        this.studentService = studentService_;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents()
    {
        List<Student> studentsAll = studentService.getStudents();
        return new ResponseEntity<List<Student>>(studentsAll, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> registerNewStudent(@RequestBody Student student)
    {
        return new ResponseEntity<Student>(studentService.addNewStudent(student), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Student> studentUpdate(@RequestBody Student studentUpdate)
    {
       return new ResponseEntity<Student>(studentService.studentUpdate(studentUpdate),HttpStatus.OK);
    }

}
