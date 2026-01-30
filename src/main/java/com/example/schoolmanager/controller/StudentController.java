package com.example.schoolmanager.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.example.schoolmanager.service.StudentService;
import com.example.schoolmanager.model.Student;

@RestController
@RequestMapping("/api/students")
@CrossOrigin // cho phép frontend gọi
public class StudentController {

    @Autowired
    private StudentService service;

    // 1. API thêm sinh viên
    @PostMapping
    public Student addStudent(@RequestBody Student student) {

        return service.addStudent(student);
    }

    // 2. API xóa sinh viên
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable UUID id) {
        service.deleteStudent(id);
        return "Student with ID " + id + " has been deleted.";
    }

    // 3. Tim kiếm sinh viên theo tên
    @GetMapping("/search")
    public List<Student> searchByName(@RequestParam String name) {
        return service.findByName(name);
    }

    // 4. API lấy sinh viên theo ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable UUID id) {
        return service.getStudentById(id);
    }

    // 5. API lấy danh sách sinh viên
    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAll();
    }

    // 6. API cập nhật sinh viên
    @PostMapping("/update/{id}")
    public Student updateStudent(@PathVariable UUID id, @RequestBody Student studentData) {
        Student existingStudent = service.getStudentById(id);

        if (existingStudent != null) {
            // Cập nhật dữ liệu mới vào sinh viên cũ
            existingStudent.setName(studentData.getName());
            existingStudent.setEmail(studentData.getEmail());

            // Lưu lại xuống database
            return service.addStudent(existingStudent);
        }
        return null;
    }

}