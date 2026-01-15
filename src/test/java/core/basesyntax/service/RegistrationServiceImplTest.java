package core.basesyntax.service;

import core.basesyntax.db.Storage;
import core.basesyntax.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegistrationServiceImplTest {
    private RegistrationServiceImpl registrationService;

    @BeforeEach
    void beforeEach() {
        registrationService = new RegistrationServiceImpl();
        Storage.people.clear();
    }

    @Test
    void add_nullLogin_throwsException() {
        User actual = new User(null, "abcdef", 25);
        assertThrows(RegistrationException.class, () -> registrationService.register(actual));
    }

    @Test
    void add_nullPassword_throwsException() {
        User actual = new User("abcdef", null, 25);
        assertThrows(RegistrationException.class, () -> registrationService.register(actual));
    }

    @Test
    void add_nullAge_ThrowsException() {
        User actual = new User("abcdef", "abcdef", null);
        assertThrows(RegistrationException.class, () -> registrationService.register(actual));
    }

    @Test
    void add_toShortLogin_throwsException() {
        User actual = new User("abcde", "abcdef", 25);
        assertThrows(RegistrationException.class, () -> registrationService.register(actual));
    }

    @Test
    void add_toShortPassword_throwsException() {
        User actual = new User("abcdef", "abcde", 25);
        assertThrows(RegistrationException.class, () -> registrationService.register(actual));
    }

    @Test
    void add_duplicateLogin_throwsException() {
        User actual = new User("abcdef", "abcdef", 25);
        Storage.people.add(actual);
        assertThrows(RegistrationException.class, () -> registrationService.register(actual));
    }

    @Test
    void add_negativeAge_ThrowsException() {
        User actual = new User("abcdef", "abcdef", -10);
        assertThrows(RegistrationException.class, () -> registrationService.register(actual));
    }

    @Test
    void add_toSmallAge_ThrowsException() {
        User actual = new User("abcdef", "abcdef", 17);
        assertThrows(RegistrationException.class, () -> registrationService.register(actual));
    }

    @Test
    void add_correctUser_Ok() {
        User actual = new User("abcdef", "abcdef", 20);
        registrationService.register(actual);
        assertTrue(actual.equals(registrationService.getUser(actual.getLogin())));
    }

    @Test
    void add_emptyLogin_throwsException() {
        User actual = new User("", "abcdef", 25);
        assertThrows(RegistrationException.class, () -> registrationService.register(actual));
    }

    @Test
    void add_emptyPassword_throwsException() {
        User actual = new User("abcdef", "", 25);
        assertThrows(RegistrationException.class, () -> registrationService.register(actual));
    }

}
