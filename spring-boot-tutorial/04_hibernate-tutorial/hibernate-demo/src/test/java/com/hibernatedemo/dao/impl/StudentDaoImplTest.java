package com.hibernatedemo.dao.impl;

import com.hibernatedemo.dao.StudentDao;
import com.hibernatedemo.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentDaoImplTest {

    private StudentDao studentDao;

    @BeforeEach
    void setUp() {
        studentDao = new StudentDaoImpl();
    }

    @DisplayName("Test create a new student successfully")
    @Test
    void create() {
        Student student = new Student();
        student.setFullName("Ronaldo Chistino");
        student.setAge(20);
        boolean success = studentDao.create(student);
        assertTrue(success, "Student was not created");
    }

    @Test
    void getStudent() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {

    }
}