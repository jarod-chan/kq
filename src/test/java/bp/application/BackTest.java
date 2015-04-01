package bp.application;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.fyg.kq.application.BackService;
import cn.fyg.kq.domain.model.vacation.back.Back;
import cn.fyg.module.user.User;
import cn.fyg.module.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class BackTest {
	
	@Autowired
	UserService userService;
	
	@Autowired
	BackService backService;
	
	@Test
	public void test(){
		User user=userService.createUser();
		user.setKey("user1");
		user.setRealname("陈1");
		user.setPassword("pwd111");
		user.setEmail("user1@gmail.com");
		user.setCellphone("13811112222");
		userService.saveUser(user);
		
		for (int i=1;i<=10;i++) {
			Back back=backService.create(user);
			back=backService.save(back);
			assertEquals("HR-XJ12-USER1-"+i,back.getNo());
		}
	}

}
