package ehtp.tp.architecturemonolithique.architecture_monolithique.controller;


import ehtp.tp.architecturemonolithique.architecture_monolithique.domain.Student;
import ehtp.tp.architecturemonolithique.architecture_monolithique.repos.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/findAll")
    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }

    @PostMapping("/insert")
    public Student insert(@RequestBody Student student){
        return studentRepository.save(student);
    }
}
