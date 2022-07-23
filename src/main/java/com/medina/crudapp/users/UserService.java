package com.medina.crudapp.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public List<User> listAll() {
        return (List<User>) repo.findAll();
    }
    public void save(User user) {
        repo.save(user);
    }
    public User get(Integer id) throws UserNotFoundException{
        Optional<User> userID = repo.findById(id);
        if(userID.isPresent()) {
            return userID.get();
        }
        throw new UserNotFoundException("This User's ID is not found " + id);
    }
    public void delete(Integer id) throws UserNotFoundException {
        Long count = repo.countById(id);

        if(count == null || count == 0) {
            throw new UserNotFoundException("User with ID: " + id + " not found.");
        }
        repo.deleteById(id);
    }

}
