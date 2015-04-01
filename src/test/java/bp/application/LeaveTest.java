package bp.application;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.fyg.kq.application.LeaveService;
import cn.fyg.kq.domain.model.vacation.leave.Leave;
import cn.fyg.module.user.User;
import cn.fyg.module.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class LeaveTest {
	
	@Autowired
	LeaveService leaveService;
	@Autowired
	UserService userService;

	
	@Test
	public void testCreate(){
		User user=userService.createUser();
		user.setKey("user1");
		user.setRealname("陈1");
		user.setPassword("pwd111");
		user.setEmail("user1@gmail.com");
		user.setCellphone("13811112222");
		user=userService.saveUser(user);
		
		
		for (int i = 1; i <= 10; i++) {
			Leave leave = leaveService.create(user);
			leave=leaveService.save(leave);
			assertTrue(leave.getNo().equals("HR-QJ12-USER1-"+i));
		}
		
		
	}
	
//	@Test
//	public void testsearch() throws ParseException{
//		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
//		
//		User user=userService.createUser();
//		user.setKey("user2");
//		user.setRealname("陈2");
//		user.setPassword("pwd111");
//		user.setEmail("user2@gmail.com");
//		user.setCellphone("13911112222");
//		userService.saveUser(user);
//
//		
//		Leave leave = leaveService.create(user);
//		Dayitem beg=new Dayitem();
//		beg.setDate(dateFormat.parse("2012-1-1"));
//		beg.setAmpm(AMPM.am);
//		Dayitem end=new Dayitem();
//		end.setDate(dateFormat.parse("2012-1-31"));
//		end.setAmpm(AMPM.pm);
//		
//		leave.setActurlDay(new BigDecimal("1"));
//		
//		leave.setBegDayItem(beg);
//		leave.setEndDayitem(end);
//		
//		leave=leaveService.save(leave);
//		
//		
//		
//		Dayitem mid=new Dayitem();
//		mid.setDate(dateFormat.parse("2012-1-1"));
//		mid.setAmpm(AMPM.pm);
//		
//		leaveService.appendCompdate(mid);
//		
//		leave=leaveService.find(leave.getId());
//		
//		assertEquals(new BigDecimal("0.50"),leave.getActurlDay());
//	}

}
