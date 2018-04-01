package resources.Controllers;

import data.User;
import resources.Hibernate.Interfaces.DataBaseCoreInterface;
import resources.MD5Hash;

public class UserController extends DefaultController<User> {
    public UserController() {
        super(User.class);
    }

    public UserController(DataBaseCoreInterface core) {
        super(User.class, core);
    }

    @Override
    public User saveToDataBase(User object) {
        String password = object.getPassword();
        object.setPassword(MD5Hash.getHash(password));
        super.saveToDataBase(object);
        object.setPassword(password);
        return object;
    }

    public User getByUserName(String userName){
        return (User) dataBaseCore.getByCriteria(User.class, "username", userName);
    }

    public boolean passwordIsCorrect(String userName, String password) {
        String hashingPassword = MD5Hash.getHash(password);

        Integer count = dataBaseCore.getNumberCriteria(User.class,
                "username", userName, "password", hashingPassword);

        return count == 1;
    }

    public boolean userNameIsFree(String userName){
        Integer count = dataBaseCore.getNumberCriteria(User.class,
                "username", userName);

        return count == 0;
    }
}
