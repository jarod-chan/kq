package kq.reptline;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.fyg.kq.application.ReptlineService;
import cn.fyg.kq.domain.model.reptline.Reptline;

import common.H;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ReptlineTest {
	
	@Autowired
	ReptlineService reptlineService;
	
	@Test
	public void testCreate(){
		
		Reptline findByUser_fidAndLevel = reptlineService.findByUser_fidAndLevel("1Au5DAEfEADgAFhFrBABIxO33n8=", 4);
		
		H.p(findByUser_fidAndLevel);
	}
	
	@Test
	public void maxUserLevel(){
		Reptline reptline = this.reptlineService.maxUserLevel("102");
		H.p(reptline);
	}
}
