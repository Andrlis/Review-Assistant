package logics;

import data.group.Group;
import dao.DataBaseCore;
import exceptions.DataBaseCriteriaCountException;
import exceptions.DataBaseQueryException;
import dao.DataBaseCoreInterface;

import java.util.ArrayList;
import java.util.List;

public class GroupLogic {
    private DataBaseCoreInterface dataBaseCore;

    public GroupLogic() {
        dataBaseCore = DataBaseCore.getInstance();
    }

    public GroupLogic(DataBaseCoreInterface core) {
        dataBaseCore = core;
    }

    public Group getByNumber(String number) {
        try {
            return (Group) dataBaseCore.getByCriteria(Group.class, "numberOfGroup", number);
        } catch (DataBaseQueryException | DataBaseCriteriaCountException e) {
            return null;
        }
    }

    public List<Group> getAll() throws DataBaseQueryException {
        List<Object> objects = dataBaseCore.getAll(Group.class);
        List<Group> groups = new ArrayList<>();

        for(Object object : objects){
            groups.add((Group) object);
        }

        return groups;
    }
}
