package com.genadi.UsersManagement.dal;

import com.genadi.UsersManagement.bean.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {

//    @Query("select u from users u where name=:userName and password=:password")
//    public UserLoginData login (@Param("userName")String userName, @Param("password")String password);
    User findByUserName(String userName);


}
