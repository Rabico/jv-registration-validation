package core.basesyntax.dao;

import core.basesyntax.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class StorageDaoImplTest {
    private StorageDaoImpl storageDao;

    @BeforeEach
    void beforeEach() {
        storageDao = new StorageDaoImpl();
    }

    @Test
    void nullLoginException() {
        User actual = new User(null, "abcdef", 25);
        assertThrows(RegistrationException.class, () -> storageDao.add(actual));
    }

    @Test
    void nullPasswordException() {
        User actual = new User("abcdef", null, 25);
        assertThrows(RegistrationException.class, () -> storageDao.add(actual));
    }

    @Test
    void nullAgeException() {
        User actual = new User("abcdef", "abcdef", null);
        assertThrows(RegistrationException.class, () -> storageDao.add(actual));
    }

    @Test
    void toSmallLoginException() {
        User actual = new User("abc", "abcdef", 25);
        assertThrows(RegistrationException.class, () -> storageDao.add(actual));
    }

    @Test
    void toSmallPasswordException() {
        User actual = new User("abcdef", "abc", 25);
        assertThrows(RegistrationException.class, () -> storageDao.add(actual));
    }

    @Test
    void duplicateLoginException() {
        User actual = new User("abcdef", "abcdef", 25);
        storageDao.add(actual);
        assertThrows(RegistrationException.class, () -> storageDao.add(actual));
    }
}
