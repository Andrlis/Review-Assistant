package logics;

import data.User;
import dao.DataBaseCore;
import exceptions.DataBaseCriteriaCountException;
import exceptions.DataBaseQueryException;
import dao.DataBaseCoreInterface;
import resources.MD5Hash;

public class UserLogic {
    private DataBaseCoreInterface dataBaseCore;

    public UserLogic() {
        dataBaseCore = DataBaseCore.getInstance();
    }

    public UserLogic(DataBaseCoreInterface core) {
        dataBaseCore = core;
    }

    public User saveToDataBase(User object) throws DataBaseQueryException {
        object.setMD5Password(object.getPassword());
        return (User) dataBaseCore.create(object);
    }

    public User getByUserName(String userName) throws DataBaseQueryException, DataBaseCriteriaCountException {
        return (User) dataBaseCore.getByCriteria(User.class, "username", userName);
    }

    public boolean passwordIsCorrect(String userName, String password) throws DataBaseQueryException, DataBaseCriteriaCountException {
        String hashingPassword = MD5Hash.getHash(password);

        Integer count = dataBaseCore.getNumberCriteria(User.class,
                "username", userName, "password", hashingPassword);

        return count == 1;
    }

    public boolean userNameIsFree(String userName) throws DataBaseQueryException, DataBaseCriteriaCountException {
        Integer count = dataBaseCore.getNumberCriteria(User.class,
                "username", userName);

        return count == 0;
    }
}
