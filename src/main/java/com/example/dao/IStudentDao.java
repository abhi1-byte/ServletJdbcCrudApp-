package com.example.dao;

import com.example.dto.Student;

public interface IStudentDao {
    public Integer addStudent(Student s);
    public Student searchStudent(Integer sid);
    public Integer updateStudent(Student s);
    public Integer deleteStudent(Integer sid);
}
