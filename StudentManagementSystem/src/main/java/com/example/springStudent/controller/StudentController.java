package com.example.springStudent.controller;

import com.example.springStudent.entity.Student;
import com.example.springStudent.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService service;

    @PostMapping
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student){
        Student saveStudent = service.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveStudent);

    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll(){
        List<Student> student = service.getAllStudent();
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id){
        Student student = service.getStudentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Student> getStudentByName(@PathVariable String name){
        Student student = service.getStudentByName(name);
        return ResponseEntity.ok(student);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student){
        Student student1 = service.updateStudent(id, student);
        return ResponseEntity.status(HttpStatus.OK).body(student1);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        service.deleteStudentById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/page")
    public Page<Student> getStudentByPage(@RequestParam int page, @RequestParam int size){
        return service.getStudentByPage(page, size);
    }

    @GetMapping("/sort")
    public List<Student> getSortStudent(){
        return service.getStudentSorted();
    }

    @GetMapping("/pagesort")
    public Page<Student> getSortPageStudent(@RequestParam int page, @RequestParam int size){
        return service.getSortPageStudent(page, size);
    }

}
