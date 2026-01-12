package core.basesyntax.dao;

import core.basesyntax.db.Storage;
import core.basesyntax.model.User;

public class StorageDaoImpl implements StorageDao {
    private static Long index = 0L;
    private static final Integer MIN_AGE = 18;
    private static final int MIN_CHAR = 6;

    @Override
    public User add(User user) {
        if (user.getLogin() == null) {
            throw new RegistrationException("Login can't be null");
        }
        if (user.getPassword() == null) {
            throw new RegistrationException("Password can't be null");
        }
        if (user.getAge() == null) {
            throw new RegistrationException("Age can't be null");
        }
        if (user.getAge() < MIN_AGE) {
            throw new RegistrationException("Not valid age: "
                    + user.getAge() + ". Min allowed age is " + MIN_AGE);
        }
        if (user.getPassword().length() < MIN_CHAR) {
            throw new RegistrationException("To short password: "
                    + user.getPassword() + ". Min allowed password is " + MIN_CHAR);
        }
        if (user.getLogin().length() < MIN_CHAR) {
            throw new RegistrationException("To short login age: "
                    + user.getLogin() + ". Min allowed login is " + MIN_CHAR);
        }
        for (User existingUser : Storage.people) {
            if (existingUser.getLogin().equals(user.getLogin())) {
                throw new RegistrationException("Login already exists: " + user.getLogin());
            }

        }

        user.setId(++index);
        Storage.people.add(user);
        return user;
    }

    @Override
    public User get(String login) {
        for (User user : Storage.people) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }
}
