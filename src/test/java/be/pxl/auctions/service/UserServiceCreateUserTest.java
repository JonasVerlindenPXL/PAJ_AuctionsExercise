package be.pxl.auctions.service;

import be.pxl.auctions.dao.UserDao;
import be.pxl.auctions.model.User;
import be.pxl.auctions.rest.resource.UserCreateResource;
import be.pxl.auctions.util.exception.DuplicateEmailException;
import be.pxl.auctions.util.exception.InvalidDateException;
import be.pxl.auctions.util.exception.InvalidEmailException;
import be.pxl.auctions.util.exception.RequiredFieldException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class UserServiceCreateUserTest {

    // TODO add unit tests for all possible scenario's of the createUser method


    private static final long USER_ID = 5L;

    @Mock
    private UserDao userDao;
    @InjectMocks
    private UserService userService;
    private UserCreateResource user;

    @BeforeEach
    public void init() {
        user = new UserCreateResource();
        user.setFirstName("Mark");
        user.setLastName("Zuckerberg");
        user.setDateOfBirth("26/01/2021");
        user.setEmail("mark@facebook.com");
    }
    @Test
    public void throwsRequiredFieldExceptionWhenLastNameNotGiven() {
        user.setLastName(null);
        assertThrows(RequiredFieldException.class, () -> userService.createUser(user));
    }
    @Test
    public void throwsRequiredFieldExceptionWhenEmailNotGiven() {
        user.setEmail(null);
        assertThrows(RequiredFieldException.class, () -> userService.createUser(user));
    }
    @Test
    public void throwsRequiredFieldExceptionWhenDateOfBirthNotGiven() {
        user.setDateOfBirth(null);
        assertThrows(RequiredFieldException.class, () ->userService.createUser(user));
    }
    @Test
    public void throwsInvalidExceptionWhenInvalidEmailGiven() {
        user.setEmail("sadsa");
        assertThrows(InvalidEmailException.class, () ->userService.createUser(user));
    }
    @Test
    public void throwsRequiredFieldExceptionWhenDateOfBirthInFuture() {
        user.setDateOfBirth("24/04/2050");
        assertThrows(InvalidDateException.class, () -> userService.createUser(user));
    }

    @Test
    public void throwsRequiredFieldExceptionWhenEmailNotUnique() {
        when(userDao.findUserByEmail("mark@facebook.com")).thenReturn(Optional.of(new User()));
        assertThrows(DuplicateEmailException.class, () -> userService.createUser(user));
    }


}
