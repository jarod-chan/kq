package dateutil;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import static cn.fyg.kq.infrastructure.tool.date.DateUtil.*;

public class DateUtilTest {
	
	@Test
	public void testDateTimeZero() {
		p(dateTimeZero(monthEnd(2015,5,59)));
	}
	
	private static void  p(Date date){
		System.out.println( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(date) );
	}

}
