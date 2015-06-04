package cn.fyg.kq.interfaces.web.modify;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.kq.interfaces.web.modify.dto.DateCheckDao;
import cn.fyg.zktime.domain.Schclass;
import cn.fyg.zktime.domain.monthcheck.DateCheck;
import cn.fyg.zktime.domain.monthcheck.MonthCheck;
import cn.fyg.zktime.service.MonthCheckService;


@Controller
@RequestMapping("/md/kqquery")
public class KqQueryCtl {
	
	private static final String PATH = "modify/kqquery/";
	private interface Page {
		String QUERY = PATH + "query";
		String RESULT = PATH + "result";
	}
	
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toFisrst(Map<String,Object> map){
		return Page.QUERY;
	}
	
	@Autowired
	MonthCheckService monthCheckService;
	
	@RequestMapping(value="result",method=RequestMethod.GET)
	public String result(int year,int month,int userid,Map<String,Object> map){
		MonthCheck monthCheck = this.monthCheckService.getMonthCheck(year, month, userid);
		List<DateCheckDao> datecheckdaoList = new ArrayList<DateCheckDao>();
		for (DateCheck dateCheck : monthCheck.getDatechecks()) {
			List<Schclass> schclasses = dateCheck.getSchclasses();
			if(schclasses.isEmpty()){
				DateCheckDao dateCheckDao = makeDateCheckDao(dateCheck,1,new Schclass());
				datecheckdaoList.add(dateCheckDao);
			}else if(schclasses.size()==1){
				DateCheckDao dateCheckDao = makeDateCheckDao(dateCheck,1,schclasses.get(0));
				datecheckdaoList.add(dateCheckDao);
			}else{
				DateCheckDao dateCheckDao = makeDateCheckDao(dateCheck,schclasses.size(),schclasses.get(0));
				datecheckdaoList.add(dateCheckDao);
				for(int i=1,len=schclasses.size();i<len;i++){
					DateCheckDao dateCheckDaoOther = makeDateCheckDao(dateCheck,0,schclasses.get(i));
					datecheckdaoList.add(dateCheckDaoOther);
				}
			}
		}
		map.put("datecheckdaoList", datecheckdaoList);
		return Page.RESULT;
	}

	private DateCheckDao makeDateCheckDao(DateCheck dateCheck,int len,Schclass schclass) {
		DateCheckDao dateCheckDao = new DateCheckDao();
		dateCheckDao.setLen(len);
		dateCheckDao.setDate(dateCheck.getDate());
		dateCheckDao.setSchclasses(schclass);
		dateCheckDao.setCheckinout(dateCheck.getCheckinout());
		return dateCheckDao;
	}
}
