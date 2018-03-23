package resources;

import data.Student;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class StudInfoParserTest {
    private File testFile = null;

    @Before
    public void setUp() throws Exception {
         testFile = new File("C:\\Andrey\\IntellijIDEA\\TRTPOControlSystem\\TestFiles\\StudInfo1.xls");
    }

    @Test
    public void testParseFile() throws Exception {
        ArrayList<Student> testInfo = StudInfoParser.parseStudentInfo(testFile);

        assertNotEquals(true, testInfo.isEmpty());
        assertEquals(2, testInfo.size());
    }
}