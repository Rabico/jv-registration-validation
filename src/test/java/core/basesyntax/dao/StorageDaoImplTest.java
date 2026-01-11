package core.basesyntax.dao;

import core.basesyntax.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StorageDaoImplTest {
    private StorageDaoImpl storageDao;
    @BeforeEach
    void beforeEach() {
        storageDao = new StorageDaoImpl();
    }
    @Test
    void nullLoginException() {
        User user = new User(null, "abcdefg", 25);
        assertThrows(RegistrationException.class, () -> storageDao.add(user));
    }
    @Test
    void nullPasswordException() {
        User user = new User("abcdefg", null, 25);
        assertThrows(RegistrationException.class, () -> storageDao.add(user));
    }
    @Test
    void nullAgeException() {
        User user = new User("abcdefg", "abcdefg", null);
        assertThrows(RegistrationException.class, () -> storageDao.add(user));
    }

}