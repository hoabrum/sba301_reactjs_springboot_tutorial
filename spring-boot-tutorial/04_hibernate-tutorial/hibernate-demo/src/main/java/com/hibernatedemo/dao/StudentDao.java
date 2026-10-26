package com.hibernatedemo.dao;

import com.hibernatedemo.entity.Student;

public interface StudentDao {

    boolean create(Student student);

    Student getStudent(int id);

    boolean update(Student student);

    boolean delete(int id);
}
