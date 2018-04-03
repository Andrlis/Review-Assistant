package resources.Controllers;

import data.User;
import resources.Hibernate.Exceptions.DataBaseCriteriaCountException;
import resources.Hibernate.Exceptions.DataBaseQueryException;
import resources.Hibernate.Interfaces.DataBaseCoreInterface;
import resources.MD5Hash;

public class UserController extends DefaultController {
    public UserController() {
        super();
    }

    public UserController(DataBaseCoreInterface core) {
        super(core);
    }

    @Override
    public Object saveToDataBase(Object object) throws DataBaseQueryException {
        User user = (User) object;
        user.setMD5Password(user.getPassword());
        return super.saveToDataBase(user);
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
