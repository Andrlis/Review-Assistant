package intercalationJavaHTML;

import data.group.SubGroup;
import data.Student;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import resources.Hibernate.HibernateShellQueryException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

abstract public class TableGenerator {
    protected abstract FileOutputStream getFileOutputStream(String fileName) throws IOException;
    public abstract void createTable(SubGroup subGroup);
    protected abstract void addStudentToTable(Table table, Student student) throws HibernateShellQueryException;
    protected void formTable(Table table, SubGroup subGroup) {
        try {
            if (subGroup == null)
                return;
            String tableId = "table-" + subGroup.getGroup().getNumberOfGroup() + "-" + subGroup.getSubGroupNumber();
            table.setId(tableId);

            //setHeaderToTable(table, subGroup);

            List<Student> studentList = subGroup.getStudentsList();
            for (Student student: studentList)
                addStudentToTable(table, student);

            // Pretty print the document to System.out
            //file.
            FileOutputStream fileOutputStream = getFileOutputStream(tableId);
            OutputFormat     format           = OutputFormat.createPrettyPrint();
            XMLWriter        writer           = new XMLWriter( fileOutputStream, format );
            writer.write(table.getDocument());
            fileOutputStream.close();
            writer.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HibernateShellQueryException e) {
            e.printStackTrace();
        }
    }
}
