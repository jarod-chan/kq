package sqlserver;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import common.H;

import cn.fyg.zktime.domain.Userinfo;
import cn.fyg.zktime.domain.UserinfoMapper;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Sqlserver {
	
	@Autowired
	UserinfoMapper userinfoMapper;
	
	@Test
	public void testCreate(){
		
		assertNotNull(this.userinfoMapper);
		List<Userinfo> all = this.userinfoMapper.all();
		for (Userinfo userinfo : all) {
			System.out.println(userinfo);
		}
		
	}
	
	@Test
	public void testfind(){
		List<Userinfo> all = this.userinfoMapper.findByName("张");
		for (Userinfo userinfo : all) {
			H.ptl(userinfo);
		}
	}



}
