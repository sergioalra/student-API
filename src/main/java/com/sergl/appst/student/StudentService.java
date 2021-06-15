package com.sergl.appst.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    //Dependency Injection
    @Autowired
    // constructor like 'StudentController'
    public StudentService( StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents()
    {
        return studentRepository.findAll();

    }

    public Student addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()) {
            throw new IllegalStateException("Email exists already");
        }
        return studentRepository.save(student);
    }

    @Transactional
    public Student studentUpdate(Student studentUpdate)
    {
        Student student = studentRepository.findById(studentUpdate.getId())
                .orElseThrow(() ->new IllegalStateException("This id"+
                        studentUpdate.getId()+" not exists"));

        if (studentUpdate.getName()!= null && studentUpdate.getName().length() >0
        && !Objects.equals(studentUpdate.getName(),student.getName()))
            student.setName(studentUpdate.getName());

        if (studentUpdate.getEmail()!= null && studentUpdate.getEmail().length() >0
        && !Objects.equals(studentUpdate.getEmail(),student.getEmail()))
        {
            Optional<Student> studentOptional = studentRepository.
                    findStudentByEmail(studentUpdate.getEmail());

            if(studentOptional.isPresent())
            throw new IllegalStateException("Email exists already");

            student.setEmail(studentUpdate.getEmail());
        }


        if (studentUpdate.getDob()!= null &&
        !Objects.equals(studentUpdate.getDob(),student.getDob()))
            student.setDob(studentUpdate.getDob());

//        for this case is not required save
//        studentRepository.save(student);

        return student;
    }

    public void deleteStudent(Long studentId) {
       boolean exists = studentRepository.existsById(studentId);

       if (!exists){
           throw new IllegalStateException("This id:"+studentId+" does not exists");
       }
        studentRepository.deleteById(studentId);
    }
}
