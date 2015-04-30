package zktime;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTime.Property;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import common.H;

import cn.fyg.zktime.domain.Checkinout;
import cn.fyg.zktime.domain.CheckinoutMapper;
import cn.fyg.zktime.domain.MonthCheck;
import cn.fyg.zktime.domain.Schclass;
import cn.fyg.zktime.domain.UserrunMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MonthcheckTest {
	
	@Autowired
	UserrunMapper userrunMapper;
	
	@Autowired
	CheckinoutMapper checkinoutMapper;
	
	@Test
	public void test(){
		new MonthCheck().initDate(38,2015,4,userrunMapper,checkinoutMapper);
		
	}
	
	@Test
	public void test2(){
		DateTime dateTime = new DateTime(2015, 4, 1, 0, 0, 0, 0);
		Integer runidofdate = this.userrunMapper.runidofdate(38, dateTime.toDate());
		System.out.println(runidofdate);
		Property day = dateTime.dayOfWeek();
		
		List<Schclass> schclassofrun = this.userrunMapper.schclassofrun(runidofdate, day.get());
		for (Schclass schclass : schclassofrun) {
			H.p(schclass);
		}
	}
	
	@Test
	public void test3(){
		List<Checkinout> monthCheckinout = this.checkinoutMapper.monthCheckinout(38, 2015, 4);
		for (Checkinout checkinout : monthCheckinout) {
			H.p(checkinout);
		}
	}

}
