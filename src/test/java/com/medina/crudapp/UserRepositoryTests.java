package com.medina.crudapp;

import com.medina.crudapp.users.User;
import com.medina.crudapp.users.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repo;

    /**
     * Test method to test the add User feature to the User Repository.
     */

    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("Bob123@email.com");
        user.setPassword("Bob123456");
        user.setFirstName("Bob");
        user.setLastName("Milkman");


        User savedUser = repo.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);

    }

    /**
     * Test method to print all the users in the Iterable.
     */
    @Test
    public void testListAll() {
        Iterable<User> users = repo.findAll();

        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user : users) {
            System.out.println(user);
        }

    }

    /**
     * Test method to update password for User.
     */
    @Test
    public void testUpdate() {
        Integer userID = 3;
        Optional<User> optionalUser = repo.findById(userID);
        User user = optionalUser.get();
        user.setPassword("thispassword");
        repo.save(user);

        User updatedUser = repo.findById(userID).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("thispassword");

    }

    /**
     * Test method to get specified User with the ID value.
     */
    @Test
    public void testGet() {
        Integer userID = 2;
        Optional<User> optionalUser = repo.findById(userID);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());

    }

    /**
     * Test method to test delete User feature.
     */
    @Test
    public void testDelete() {
        Integer userID = 6;
        repo.deleteById(userID);

        Optional<User> optionalUser = repo.findById(userID);
        Assertions.assertThat(optionalUser).isNotPresent();


    }


}
