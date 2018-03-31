package resources.Controllers;

import data.User;
import resources.Hibernate.Interfaces.DataBaseCoreInterface;

public class UserController extends DefaultController<User> {
    public UserController() {
        super(User.class);
    }

    public UserController(DataBaseCoreInterface core) {
        super(User.class, core);
    }

    public User getByUserName(String userName){
        return (User) dataBaseCore.getByCriteria(User.class, "username", userName);
    }

    public boolean passwordIsCorrect(String userName, String password) {
        //TODO MD5
        Integer count = dataBaseCore.getNumberCriteria(User.class,
                "username", userName, "password", password);

        return count == 1;
    }

    public boolean userNameIsFree(String userName){
        Integer count = dataBaseCore.getNumberCriteria(User.class,
                "username", userName);

        return count == 0;
    }
}
