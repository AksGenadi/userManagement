package com.genadi.UsersManagement.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.genadi.UsersManagement.bean.User;
import com.genadi.UsersManagement.bean.UserLoginData;
import com.genadi.UsersManagement.dal.IUserRepository;
import com.genadi.UsersManagement.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.rmi.ServerException;


@Service
public class UsersLogic {
    private IUserRepository userRepository;

    @Autowired
    public UsersLogic(IUserRepository userRepository) {
        this.userRepository = userRepository;
            }


    public User getUser(long userId) {
        return userRepository.findById(userId).get();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void removeUser(long userId) {
        userRepository.deleteById(userId);
    }


    @Transactional(rollbackOn = {Exception.class})
    public void bedTransactionRollBackExample() throws ServerException {
//        User user1 = new User("Avi12", 20, "Tel aviv", "0456378192", 1);
//        User user2 = new User("Avi12", 20, "Tel aviv", "0456378192", 1);
//        try {
//            userRepository.save(user1);
//            userRepository.save(user2);
//        } catch (Exception e) {
//            throw new ServerException(e.getMessage());
//        }
    }


    public String login(String userName, String password) {
        // mocking a successfull login
        User user = userRepository.findByUserName(userName);
        if (user == null ) return  "wrong user or password";
        if (!user.getPassword().equals(password)) return "wrong user or password";

        UserLoginData userLoginData = new UserLoginData(user.getId(),user.getUserName(), password, user.getUserType(), user.getCompanyId());

        // Reaching here means - a successful login
        ObjectMapper objectMapper = new ObjectMapper();

        // Converting the userData (java object) into a JSON string
//        String jsonLoginDetails = objectMapper.writeValueAsString(loginDetails);
        String token = JWTUtils.createJWT(userLoginData);
        return token;
    }

//    public static String createJWT(UserLoginData userLoginData) throws JsonProcessingException{
//        ObjectMapper objectMapper=new ObjectMapper();
//        String jsonUserData= objectMapper.writeValueAsString(userLoginData);
//        String strUserId= String.valueOf(userLoginData.getId());
//        String strCompanyId=String.valueOf(userLoginData.getCompanyId());
//        String token=createJWT(strUserId,userLoginData.getUserType(), jsonUserData,strCompanyId,0);
//        return token;
//    }



}