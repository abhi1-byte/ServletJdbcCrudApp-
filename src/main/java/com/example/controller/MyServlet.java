package com.example.controller;

import com.example.dto.Student;
import com.example.service.IStudentService;
import com.example.servicefactory.StudentServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/controller/*")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Request Path: " + request.getPathInfo());
        IStudentService studentService = StudentServiceFactory.getStudentService();
        if (request.getRequestURI().endsWith("addform")) {
            PrintWriter out = response.getWriter();
            Student s = new Student();
            s.setSage(Integer.parseInt(request.getParameter("sage")));
            s.setSname(request.getParameter("sname"));
            s.setSaddress(request.getParameter("saddress"));
            int status = studentService.addStudent(s);
            RequestDispatcher rd = null;
            if (status >= 1) {
                rd = request.getRequestDispatcher("/success.html");
                rd.forward(request, response);
            } else {
                rd = request.getRequestDispatcher("/failure.html");
                rd.forward(request, response);
            }
            out.close();
        }
        if (request.getRequestURI().endsWith("searchform")) {
            PrintWriter out = response.getWriter();
            Integer sid = (Integer.parseInt(request.getParameter("sid")));
            Student student = studentService.searchStudent(sid);
            if (student != null) {
                out.println("<h1 style='color:green; text-align:center;'>STUDENT DETAILS:</h1>");
                out.println("<center><table border=1 text-align:center><th>SID</th><th>SNAME</th><th>SAGE</th><th>SADDRESS</th>");
                out.println("<tr><td>" + student.getSid() + "</td><td>" + student.getSname() + "</td><td>" + student.getSage() + "</td><td>" + student.getSaddress() + "</td></tr>");
                out.println("</table></center>");
            } else {
                out.println("<h1 style='color:red; text-align:center;'>STUDENT DETAILS NOT FOUND...</h1>");
            }
            out.close();
        }
        if (request.getRequestURI().endsWith("deleteform")) {
            PrintWriter out = response.getWriter();
            Integer sid = (Integer.parseInt(request.getParameter("sid")));
            int status = studentService.deleteStudent(sid);
            RequestDispatcher rd = null;
            if (status == 1) {
                rd = request.getRequestDispatcher("/deletesuccess.html");
                rd.forward(request, response);
            } else if (status == -1) {
                rd = request.getRequestDispatcher("/deletefailure.html");
                rd.forward(request, response);
            } else if (status == 0) {
                rd = request.getRequestDispatcher("/deletenotfound.html");
                rd.forward(request, response);
            }
            out.close();
        }
        if (request.getRequestURI().endsWith("editform")) {
            String sid = request.getParameter("sid");
            Student student = studentService.searchStudent(Integer.parseInt(sid));
            PrintWriter out = response.getWriter();
            if (student != null) {
                // display student records as a form data so it is editable
                out.println("<body>");
                out.println("<center>");
                out.println("<form method='post' action='./controller/updateRecord'>");
                out.println("<table>");
                out.println("<tr><th>ID</th><td>" + student.getSid() + "</td></tr>");
                out.println("<input type='hidden' name='sid' value='" + student.getSid() + "'/>");
                out.println("<tr><th>NAME</th><td><input type='text' name='sname' value='" + student.getSname()
                        + "'/></td></tr>");
                out.println("<tr><th>AGE</th><td><input type='text' name='sage' value='" + student.getSage()
                        + "'/></td></tr>");
                out.println("<tr><th>ADDRESS</th><td><input type='text' name='saddress' value='" + student.getSaddress()
                        + "'/></td></tr>");
                out.println("<tr><td></td><td><input type='submit' value='update'/></td></tr>");
                out.println("</table>");
                out.println("</form>");
                out.println("</center>");
                out.println("</body>");
            } else {
                // display not found message
                out.println("<body>");
                out.println("<h1 style='color:red;text-align:center;'>Record not available for the given id :: " + sid
                        + "</h1>");
                out.println("</body>");
            }
            out.close();
        }

        if (request.getRequestURI().endsWith("updateRecord")) {
            PrintWriter out = response.getWriter();
            Student s = new Student();
            s.setSid((Integer.parseInt(request.getParameter("sid"))));
            s.setSage(Integer.parseInt(request.getParameter("sage")));
            s.setSname(request.getParameter("sname"));
            s.setSaddress(request.getParameter("saddress"));
            int status = studentService.updateStudent(s);
            RequestDispatcher rd = null;
            if (status == 1) {
                rd = request.getRequestDispatcher("/updationsuccess.html");
                rd.forward(request, response);
            } else if (status == -1) {
                rd = request.getRequestDispatcher("/updationfailure.html");
                rd.forward(request, response);
            }
            out.close();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try {
            processRequest(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}