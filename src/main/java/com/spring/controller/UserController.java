package com.spring.controller;

import com.spring.dao.UserDao;
import com.spring.dto.ResultMsg;
import com.spring.entity.UserEntity;
import com.spring.model.ResultStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {  
    @Autowired
    private UserDao userDao;
      
    @RequestMapping("getuser")  
    public Object getUser(long id)
    {  
        UserEntity userEntity = userDao.findUserInfoById(id);
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userEntity);
        return resultMsg;  
    }  
      
    @RequestMapping("getusers")  
    public Object getUsers(String role)  
    {  
        List<UserEntity> userEntities = userDao.findUserInfoByRole(role);
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userEntities);  
        return resultMsg;  
    }  
      
    @Modifying
    @RequestMapping("adduser")  
    public Object addUser(@RequestBody UserEntity userEntity)
    {
        userDao.save(userEntity);
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), userEntity);  
        return resultMsg;  
    }  
      
    @Modifying  
    @RequestMapping("updateuser")  
    public Object updateUser(@RequestBody UserEntity userEntity)
    {
        UserEntity user = userDao.findUserInfoById(userEntity.getId());
        if (user != null)  
        {  
            user.setName(userEntity.getName());
            userDao.save(user);
        }  
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), null);  
        return resultMsg;  
    }  
      
    @Modifying  
    @RequestMapping("deleteuser")  
    public Object deleteUser(@RequestParam long id)
    {
        userDao.delete(id);
        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), null);  
        return resultMsg;  
    }  
}  