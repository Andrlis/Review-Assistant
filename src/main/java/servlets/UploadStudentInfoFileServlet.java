package servlets;

import dao.DataBaseCore;
import data.Student;
import data.StudentFactory;
import data.group.Group;
import data.group.SubGroup;
import data.lecturer.Lecturer;
import exceptions.DataBaseQueryException;
import logics.GroupLogic;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import resources.StudInfoParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


@MultipartConfig
@WebServlet("/UploadStudentInfoFileServlet")
public class UploadStudentInfoFileServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String             lecturerId            = null;
        String             subgroupIdentificator = null;
        ArrayList<Student> studentArrayList      = new ArrayList<>();

        if (ServletFileUpload.isMultipartContent(request)) {

            List<FileItem> multiparts = null;
            try {
                multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }

            for (FileItem item : multiparts) {
                if (item.isFormField()) {
                    String fieldName  = item.getFieldName();
                    String fieldValue = item.getString();
                    if (fieldName.equals("number")) {
                        subgroupIdentificator = fieldValue;
                    } else if (fieldName.equals("lecturer")) {
                        lecturerId = fieldValue;
                    }
                } else {
                    File uploadedFile = new File(item.getName());
                    try {
                        item.write(uploadedFile);
                        studentArrayList = StudInfoParser.parseStudentInfo(uploadedFile);
                    } catch (Exception e) {
                    }
                }
            }
        }

        try {
            DataBaseCore dataBaseCore = DataBaseCore.getInstance();
            GroupLogic   groupLogic   = new GroupLogic();
            String       s[]          = subgroupIdentificator.split("_");
            Group        group        = groupLogic.getByNumber(s[0]);
            SubGroup     subGroup;

            if (s.length == 2) {
                subGroup = group.getSubGroup(s[1]);
            } else {
                subGroup = new SubGroup();
                subGroup.setSubGroupNumber("" + group.getNumberForNextSubgroup());
                subGroup.setGroup(group);
                group.addSubGroup(subGroup);
                dataBaseCore.create(subGroup);
            }

            Lecturer lecturer;
            lecturer = (Lecturer) dataBaseCore.getById(Lecturer.class, Integer.parseInt(lecturerId));
            subGroup.setLecturer(lecturer);

            StudentFactory studentFactory = new StudentFactory();
            for (Student stud : studentArrayList){
                studentFactory.addMarksToStudent(stud, subGroup);
                //subGroup.addStudent(stud);

                //dataBaseCore.create(stud);
            }

           subGroup.addStudents(studentArrayList);



           dataBaseCore.update(subGroup);
        } catch (DataBaseQueryException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/Welcome");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
