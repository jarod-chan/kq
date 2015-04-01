package bp.application;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.fyg.kq.application.CompdateService;
import cn.fyg.kq.domain.model.vacation.common.AMPM;
import cn.fyg.kq.domain.model.vacation.common.Dayitem;
import cn.fyg.kq.domain.model.vacation.compdate.DayResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CompdateTest {
	
	@Autowired
	CompdateService compdateService;

	@Test
	public void test() throws ParseException{
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		
		Dayitem dayitem=new Dayitem();
		dayitem.setDate(dateFormat.parse("2012-1-1"));
		dayitem.setAmpm(AMPM.am);
		compdateService.append(dayitem);
		
		
		
		dayitem=new Dayitem();
		dayitem.setDate(dateFormat.parse("2012-1-2"));
		dayitem.setAmpm(AMPM.am);
		compdateService.append(dayitem);
		
		dayitem=new Dayitem();
		dayitem.setDate(dateFormat.parse("2012-1-3"));
		dayitem.setAmpm(AMPM.pm);
		compdateService.append(dayitem);


		compdateService.syncCompdate();
		
	}
	
	@Test 
	public void testComputerdate() throws Exception{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Dayitem dayitem1=new Dayitem();
		dayitem1.setDate(dateFormat.parse("2012-1-1"));
		dayitem1.setAmpm(AMPM.am);

		Dayitem dayitem2=new Dayitem();
		dayitem2.setDate(dateFormat.parse("2012-1-2"));
		dayitem2.setAmpm(AMPM.pm);
		
		DayResult computerDay = compdateService.computerDay(dayitem1, dayitem2);
		assertEquals(new BigDecimal("2.00"),computerDay.natureDay());
		assertEquals(new BigDecimal("1.00"),computerDay.acturlDay());
	}
	
}
