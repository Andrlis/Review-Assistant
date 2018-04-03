package resources.Controllers;

import data.group.Group;
import resources.Hibernate.Exceptions.DataBaseCriteriaCountException;
import resources.Hibernate.Exceptions.DataBaseQueryException;
import resources.Hibernate.Interfaces.DataBaseCoreInterface;

import java.util.ArrayList;
import java.util.List;

public class GroupController extends DefaultController {
    public GroupController() {
        super();
    }

    public GroupController(DataBaseCoreInterface core) {
        super();
    }

    public Group getByNumber(String number) {
        try {
            return (Group) dataBaseCore.getByCriteria(Group.class, "numberOfGroup", number);
        } catch (DataBaseQueryException | DataBaseCriteriaCountException e) {
            return null;
        }
    }

    public List<Group> getAll() throws DataBaseQueryException {
        List<Object> objects = this.getAll(Group.class);
        List<Group> groups = new ArrayList<>();

        for(Object object : objects){
            groups.add((Group) object);
        }

        return groups;
    }
}
