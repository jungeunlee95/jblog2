package com.cafe24.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.UserDao;
import com.cafe24.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public Boolean joinUser(UserVo userVo) {
		Boolean result =false;
		if(userDao.insertUser(userVo)==true && userDao.insertBlog(userVo)==true) {
			result =true;
		}
		return result;
	}
	
	public Boolean deleteUser(String userId) {
		Boolean result =false;
		if(userDao.deleteBlog(userId)==true && userDao.deleteUser(userId)==true) {
			result =true;
		}
		return result;
	}
	
	public Boolean existId(String userId) {
		UserVo userVo = userDao.get(userId);
		return userVo != null;
	}
	
	public UserVo getUser(UserVo userVo) {
		return userDao.get(userVo);
	}

}
