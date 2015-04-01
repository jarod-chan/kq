package bp.application;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.fyg.kq.application.OpinionService;
import cn.fyg.kq.domain.model.opinion.Opinion;
import cn.fyg.kq.domain.model.opinion.Result;
import cn.fyg.kq.domain.model.vacation.leave.Leave;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class OpinionServiceTest {
	
	@Autowired
	OpinionService opinionService;
	
	@Test
	public void testAppend(){
		Opinion opinion=new Opinion();
		opinion.setBusinessCode(Leave.BUSINESS_CODE);
		opinion.setBusinessId(1L);
		opinion.setDescription("ddfefd");
		opinion.setResult(Result.agree);
		opinion.setTaskKey("taskkey");
		opinion.setTaskName("taskname");
		opinion.setUserKey("userkey");
		opinion.setUserName("username");
		opinionService.append(opinion);
	}
	
	@Test
	public void testallOpinion(){
		List<Opinion> opinionList = opinionService.allOpinion(Leave.BUSINESS_CODE, 1L);
		assertEquals(1,opinionList.size());
	}
	
	@Test
	public void testClear(){
		opinionService.clear(Leave.BUSINESS_CODE, 1L);
		List<Opinion> opinionList = opinionService.allOpinion(Leave.BUSINESS_CODE, 1L);
		assertTrue(opinionList.isEmpty());
	}

}
