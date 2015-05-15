package kq.reptline;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import common.H;

import cn.fyg.kq.application.ReptlineService;
import cn.fyg.kq.domain.model.reptline.Reptline;
import cn.fyg.zktime.domain.Userinfo;
import cn.fyg.zktime.domain.UserinfoMapper;

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
}
