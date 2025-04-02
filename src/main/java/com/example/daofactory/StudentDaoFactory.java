package com.example.daofactory;

import com.example.dao.IStudentDao;
import com.example.dao.StudentDaoImpl;

public class StudentDaoFactory {
    private StudentDaoFactory(){}

    private static IStudentDao studentDao = null;

    public static IStudentDao getStudentDao(){
        if(studentDao == null) {
            studentDao = new StudentDaoImpl();
        }
        return  studentDao;
    }
}
