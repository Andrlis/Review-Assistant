package resources.Hibernate;

import data.lab.Lab;
import org.junit.Test;

import static org.junit.Assert.*;

public class LabsHibernateShellTest {

    @Test
    public void deleteLabDescription() throws HibernateShellQueryException {
        LabsHibernateShell labsHibernateShell = new LabsHibernateShell();

        Integer b = null;
        b = labsHibernateShell.getNumberOfNextLab();

        labsHibernateShell.addLab("test");
        labsHibernateShell.deleteLabDescription(b);

        Integer a = null;
        a = labsHibernateShell.getNumberOfNextLab();

        assertEquals(b, a);
    }
}