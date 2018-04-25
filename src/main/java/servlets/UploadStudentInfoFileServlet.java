package servlets;

import data.Student;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import resources.StudInfoParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.ArrayList;
import java.util.List;




@MultipartConfig
@WebServlet("/UploadStudentInfoFileServlet")
public class UploadStudentInfoFileServlet extends HttpServlet {

    private static String getValue(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream),1);
        StringBuilder  value  = new StringBuilder();
        char[]         buffer = new char[1024];
        for (int length = 0; (length = reader.read(buffer)) > 0;) {
            value.append(buffer, 0, length);
        }
        return value.toString();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);

                for(FileItem item : multiparts){
                    if(item.getFieldName().equals("teacher")){
                        item.getName();
                    } else if(!item.isFormField()){

                        File uploadedFile = new File(item.getName());
                        item.write(uploadedFile);

                        ArrayList<Student> test = StudInfoParser.parseStudentInfo(uploadedFile);

                    }
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        response.sendRedirect("/Welcome");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
