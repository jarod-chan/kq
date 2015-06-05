package zktime;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.fyg.kq.infrastructure.tool.date.DateUtil;
import cn.fyg.zktime.domain.Numrun;
import cn.fyg.zktime.domain.NumrunDeil;
import cn.fyg.zktime.domain.Userrun;
import cn.fyg.zktime.domain.UserrunMapper;

import common.H;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserrunMapperTest {
	
	@Autowired
	UserrunMapper userrunMapper;
	
	@Test
	public void userrun(){
		Date monthBeg = DateUtil.monthBeg(2015,5,59);
		Date monthEnd = DateUtil.monthEnd(2015,5,59);
		int userid=38;
		
		List<Userrun> userruns = this.userrunMapper.userrun(userid, monthBeg, monthEnd);
		
		for (Userrun userrun : userruns) {
			H.p(userrun);
		}
		
	}
	
	@Test
	public void findNumrun(){
		Numrun numrun = this.userrunMapper.findNumrun(130);
		H.p(numrun);
		
		for(NumrunDeil deil:numrun.getNumrunDeils()){
			H.p(deil);
			H.p(deil.getSchclass());
		}
		
	}

}
