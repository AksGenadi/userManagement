package com.genadi.UsersManagement.controller;

import com.genadi.UsersManagement.bean.User;
import com.genadi.UsersManagement.bean.UserLoginData;
import com.genadi.UsersManagement.dal.IUserRepository;
import com.genadi.UsersManagement.logic.UsersLogic;
import com.genadi.UsersManagement.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.rmi.ServerException;

@RestController
@RequestMapping("/users")
public class UsersController {

    private IUserRepository userRepository;
    private UsersLogic usersLogic;

    @Autowired
    public UsersController(IUserRepository userRepository, UsersLogic usersLogic) {
        this.userRepository = userRepository;
        this.usersLogic = usersLogic;

    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("{userId}")
    public User getUser(@PathVariable("userId") long id) {
        return userRepository.findById(id).get();
    }

    @GetMapping
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }


    @PutMapping("{userId}")
    public User updateUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/{userId}")
    public void removeUser(@PathVariable("userId") long userId) {
        userRepository.deleteById(userId);
    }

    @PostMapping("/twousers")
    public void getTransaction() throws ServerException {
        this.usersLogic.bedTransactionRollBackExample();
    }

    @GetMapping("/login")
    public String login(@Param("userName") String userName, @Param("password") String password) {
        return usersLogic.login(userName, password);
    }

    @GetMapping("/loggedin")
    public UserLoginData getLoginData(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        token = token.replace("Bearer ", "");
        Claims claims = JWTUtils.decodeJWT(token);
        UserLoginData userLoginData = new UserLoginData();
        userLoginData.setUserName(claims.getIssuer());
        userLoginData.setCompanyId(Long.valueOf(claims.getAudience()));
        return userLoginData;
    }

}
