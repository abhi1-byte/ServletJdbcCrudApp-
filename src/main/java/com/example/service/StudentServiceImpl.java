package com.example.service;

import com.example.daofactory.StudentDaoFactory;
import com.example.dao.IStudentDao;
import com.example.dto.Student;

//Service Layer
public class StudentServiceImpl implements IStudentService {
    static IStudentDao studentDao;

    static {
        studentDao = StudentDaoFactory.getStudentDao();
    }

    @Override
    public Integer addStudent(Student s) {
        return studentDao.addStudent(s);
    }

    @Override
    public Student searchStudent(Integer sid) {
        Student std = studentDao.searchStudent(sid);
        return std;
    }

    @Override
    public Integer updateStudent(Student s) {
        Integer status = studentDao.updateStudent(s);
        return status;
    }

    @Override
    public Integer deleteStudent(Integer sid) {
        Integer i =studentDao.deleteStudent(sid);
        return i;
    }
}
