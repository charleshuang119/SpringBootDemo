package com.charlesH.demo.controller;

import com.charlesH.demo.model.Student;
import com.charlesH.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.MediaTray;
import java.awt.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/students") // define the request Path
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Get Request
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void insertNewStudent(@RequestBody Student student){
        studentService.persistNewStudent(UUID.randomUUID(), student);
    }

    // Get Request
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{studentId}"
    )
    public Student getStudentById(@PathVariable("studentId") UUID studentId) {
        return studentService.getStudentById(studentId);

    }

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            path = "{studentId}"
    )
    public void updatetStudent(@PathVariable("studentId") UUID studentId, @RequestBody Student student){
        studentService.updateStudentById(studentId, student);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{studentId}"
    )
    public void deleteStudent(@PathVariable("studentId") UUID studentId){
        studentService.deleteStudentById(studentId);
    }
}
