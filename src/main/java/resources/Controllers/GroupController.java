package resources.Controllers;

import data.group.Group;
import resources.Hibernate.Interfaces.DataBaseCoreInterface;

public class GroupController extends DefaultController<Group> {
    public GroupController() {
        super(Group.class);
    }

    public GroupController(DataBaseCoreInterface core) {
        super(Group.class, core);
    }

    public Group getByNumber(String number) {
        return (Group) dataBaseCore.getByCriteria(Group.class, "numberOfGroup", number);
    }
}
