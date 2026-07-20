package com.example.springStudent.service;

import com.example.springStudent.entity.Student;
import com.example.springStudent.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public Student addStudent(Student student){
        return repository.save(student);
    }

    public List<Student> getAllStudent(){
        return repository.findAll();
    }

    public Student getStudentById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Student getStudentByName(String name){
        return repository.findByName(name);
    }


    public Student updateStudent(Long id, Student updateStudent){
        Student student = repository.findById(id).orElseThrow(()-> new  RuntimeException("Student not found"));

        student.setName(updateStudent.getName());
        student.setAge(updateStudent.getAge());
        student.setEmail(updateStudent.getEmail());
        student.setPhoneNumber(updateStudent.getPhoneNumber());

        return repository.save(student);
    }

    public String deleteStudentById(Long id){
        Student student = repository.findById(id).orElse(null);

        repository.deleteById(id);
        return "Student deleted";
    }

    public Page<Student> getStudentByPage(int page, int size){
        return repository.findAll(PageRequest.of(page, size));
    }

    public List<Student> getStudentSorted(){
        return repository.findAll(Sort.by("name"));
    }

    public Page<Student> getSortPageStudent(int page, int size){
        return repository.findAll(PageRequest.of(page, size, Sort.by("name").ascending()));
    }
}
