package kq.user;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.fyg.kq.application.UserService;
import cn.fyg.kq.domain.model.user.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserTest {

	@Autowired
	UserService userService;
	
	@Test
	public void find(){
		
		User user = this.userService.find("not exists");
		Assert.assertNull(user);
	}
}
