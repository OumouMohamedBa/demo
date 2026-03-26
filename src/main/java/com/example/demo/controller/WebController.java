package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {
    
    @Autowired
    private StudentService studentService;
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "index";
    }
    
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Void> addStudent(@RequestBody Student student) {
        studentService.createStudent(student);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/edit/{id}")
    @ResponseBody
    public ResponseEntity<Void> editStudent(@PathVariable Long id, @RequestBody Student student) {
        studentService.updateStudent(id, student);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/";
    }
}
