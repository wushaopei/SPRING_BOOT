package com.services;

import java.io.Writer;
import java.util.List;

import com.entities.User;
import com.mappers.UserMapper;
import com.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private MailUtils mailUtils;

	public List<User> getAll() {
		return userMapper.selectAll();
	}

	@Override
	public Integer addUser(User user) {

		//将数据存入到数据库
		Integer integer = userMapper.addUser(user);

		//发送一封激活邮件
		mailUtils.sendMail("18620307785@163.com",user.getCode(),"来自邮箱测试接口邮件","<h1>来自wto网站激活邮件，激活请点击以下链接：</h1><h3><a href='http://wj7ei8.natappfree.cc/regist_web/activateServlet?code="+user.getCode()+"'>http://wj7ei8.natappfree.cc/regist_web/activateServlet?code="+user.getCode()+"</a></h3>");

		return integer;
	}

	@Override
	public User findByCode(String code) {


		return userMapper.findByCode(code);
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}

}
