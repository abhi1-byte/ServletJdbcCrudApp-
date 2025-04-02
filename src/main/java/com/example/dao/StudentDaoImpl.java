package com.example.dao;

import com.example.dto.Student;
import com.example.utils.HikariCpConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Persistence logic using JDBC API
public class StudentDaoImpl implements IStudentDao {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    @Override
    public Integer addStudent(Student s) {
        Integer rowsInserted = 0;
        String insertQuery = "INSERT INTO students (sname,sage,saddress) VALUES(?,?,?)";
        try {
            conn = HikariCpConnection.getConnection();
            if (conn != null) {
                pstmt = conn.prepareStatement(insertQuery);
            }
            /*if (pstmt != null) {
                while (true) {
                   //I've taken inputs again here because of batch query impl
                    System.out.println("Enter a student name: ");
                    String name = read.nextLine();
                    System.out.println("Enter a student age: ");
                    Integer age = read.nextInt();
                    read.nextLine();
                    System.out.println("Enter a student address: ");
                    String address = read.nextLine();*//*
                    pstmt.setString(1, sname);
                    pstmt.setInt(2, sage);
                    pstmt.setString(3, saddress);
                    pstmt.addBatch();
                    System.out.println("Do you want to add another record[YES/NO]: ");
                    String option = read.nextLine();
                    if (option.equalsIgnoreCase("no")) {
                        break;
                    }
                }
            }*/
            if (pstmt != null) {
                pstmt.setString(1, s.getSname());
                pstmt.setInt(2, s.getSage());
                pstmt.setString(3, s.getSaddress());
                //pstmt.executeBatch();
                rowsInserted = pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rowsInserted;
    }

    @Override
    public Student searchStudent(Integer sid) {
        Student std = null;
        String selectQuery = "SELECT sid,sname,sage,saddress from students where sid = ?";
        try {
            conn = HikariCpConnection.getConnection();
            if (conn != null) {
                pstmt = conn.prepareStatement(selectQuery);
            }
            if (pstmt != null) {
                pstmt.setInt(1, sid);
                rs = pstmt.executeQuery();
            }
            if (rs != null) {
                if (rs.next()) {
                    std = new Student();
                    std.setSid(rs.getInt(1));
                    std.setSname(rs.getString(2));
                    std.setSage(rs.getInt(3));
                    std.setSaddress(rs.getString(4));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return std;
    }

    @Override
    public Integer updateStudent(Student s) {
        String updateQuery = "UPDATE students set sname=?,sage=?,saddress=? where sid = ?";
        Integer i = null;
        try {
            conn = HikariCpConnection.getConnection();
            if (conn != null) {
                pstmt = conn.prepareStatement(updateQuery);
            }
            if (pstmt != null) {
                pstmt.setString(1, s.getSname());
                pstmt.setInt(2, s.getSage());
                pstmt.setString(3, s.getSaddress());
                pstmt.setInt(4, s.getSid());
                i = pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;
    }


    @Override
    public Integer deleteStudent(Integer sid) {
        String deleteQuery = "DELETE from students where sid = ?";
        Integer i = null;
        try {
            conn = HikariCpConnection.getConnection();
            if (conn != null) {
                pstmt = conn.prepareStatement(deleteQuery);
            }
            if (pstmt != null) {
                pstmt.setInt(1, sid);
                i = pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;
    }
}
