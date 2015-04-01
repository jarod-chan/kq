package bp.application;

import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.fyg.kq.application.UsertrackService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UsertrackTest {
	
	@Autowired
	UsertrackService usertrackService;

	@Test
	public void testTrack(){
		usertrackService.trackProcessInstance("chen","process1");
		usertrackService.trackProcessInstance("chen","process2");
	}
	
	@Test
	public void untrack(){
		usertrackService.untrackProcessInstance("chen", "process1");
	}
	
	@Test
	public void getProcessInstanceIds(){
		usertrackService.trackProcessInstance("chen", "process3");
		Set<String> processInstanceIds = usertrackService.getTrackProcessInstanceIds("chen");
		assertTrue(processInstanceIds.size()==2); 
	}
	
	
}
