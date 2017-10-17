package intercalationJavaHTML;

import data.Group.SubGroup;
import data.Student;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class StudentListTableGenerator extends TableGenerator {
    @Override
    protected FileOutputStream getFileOutputStream(String fileName) throws IOException {
        File file = new File("src\\main\\webapp\\tables\\subgroup_lists\\" + fileName + ".html");

        if (!file.exists())
            file.createNewFile();
        return new FileOutputStream(file, false);
    }

    @Override
    public void createTable(SubGroup subGroup) {
        StudentListTable table = new StudentListTable();
        setHeaderToTable(table);
        formTable(table, subGroup);
    }

    @Override
    protected void addStudentToTable(Table tab, Student student) {
        StudentListTable table = (StudentListTable) tab;

        table.addRow();

        table.addCell(Table.dataCell + " " + Table.editableCell + " " + StudentListTable.nameCell, student.getFulName());
        table.addCell(Table.dataCell + " " + Table.editableCell + " " + StudentListTable.eMailCell, student.geteMail());
        table.addCell(Table.dataCell + " " + Table.editableCell + " " + StudentListTable.gitHubCell, student.getGitURL());
    }

    protected void setHeaderToTable(StudentListTable table) {
        table.addRow();

        table.addCell(Table.headerCell + " " + StudentListTable.nameCell, "Студент");
        table.addCell(Table.headerCell + " " + StudentListTable.eMailCell, "eMail");
        table.addCell(Table.headerCell + " " + StudentListTable.gitHubCell, "GitHub URL");
    }
}
