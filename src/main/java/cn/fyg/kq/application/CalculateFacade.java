package cn.fyg.kq.application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import cn.fyg.kq.domain.model.checkuser.Checkuser;
import cn.fyg.kq.domain.model.checkuser.CheckuserSpecs;
import cn.fyg.kq.domain.model.checkuser.Kqstat;
import cn.fyg.kq.domain.model.kaoqin.Kaoqin;
import cn.fyg.kq.domain.model.kaoqin.KaoqinItem;
import cn.fyg.kq.domain.model.kaoqin.MonthItem;
import cn.fyg.kq.domain.model.kaoqin.SchclassInout;
import cn.fyg.kq.domain.model.period.Period;
import cn.fyg.kq.domain.model.period.PeriodState;
import cn.fyg.zktime.domain.Checkinout;
import cn.fyg.zktime.domain.DateCheck;
import cn.fyg.zktime.domain.MonthCheck;
import cn.fyg.zktime.domain.Schclass;
import cn.fyg.zktime.service.MonthCheckService;
@Service
public class CalculateFacade {
	
	@Autowired
	CheckuserService checkuserService;
	@Autowired
	MonthCheckService monthCheckService;
	@Autowired
	KaoqinService kaoqinService;
	
	@Autowired
	PeriodService periodService;
	
	@Async
	public void calculate(Period period){
		
		Specification<Checkuser> inComp = CheckuserSpecs.inComp(period.getComp());
		Specification<Checkuser> kqstat = CheckuserSpecs.isKqstat(Kqstat.yes);
		Specifications<Checkuser> specs=Specifications.where(inComp).and(kqstat);
		Sort sort=null;
		
		List<Checkuser> checkuserList = this.checkuserService.findAll(specs, sort);
		
		for (Checkuser checkuser : checkuserList) {
			int year = period.getMonthitem().getYear();
			int month = period.getMonthitem().getMonth();
			int userid = checkuser.getUserid();
			MonthCheck monthCheck = this.monthCheckService.getMonthCheck(year, month, userid);
			
			produceKaoqin(monthCheck,checkuser,period.getMonthitem());
		}
		
		period.setState(PeriodState.finishcal);
		this.periodService.save(period);
	}
	
	
	public void produceKaoqin(MonthCheck monthcheck,Checkuser checkuser,MonthItem monthItem){
		List<KaoqinItem> kaoqinItems = new ArrayList<KaoqinItem>();
		int sn=0;
		for (DateCheck dateCheck : monthcheck.getDatechecks()) {
			for(Schclass schclass:dateCheck.getSchclasses()){
				if(!schclass.isCheckin()){
					sn++;
					KaoqinItem kaoqinItem = new KaoqinItem();
					kaoqinItem.setSn(Long.valueOf(sn));
					kaoqinItem.setDate(dateCheck.getDate());
					cn.fyg.kq.domain.model.kaoqin.Schclass kqsch = new cn.fyg.kq.domain.model.kaoqin.Schclass();
					Date checkintime1 = schclass.getCheckintime1();
					Date checkintime2 = schclass.getCheckintime2();
					DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
					kqsch.setName(schclass.getSchname());
					kqsch.setBegendtime(dateFormat.format(checkintime1)+"-"+dateFormat.format(checkintime2));
					kqsch.setInout(SchclassInout.in);
					kaoqinItem.setSchclass(kqsch);
					
					String realtime="";
					for(Checkinout checkinout:dateCheck.getCheckinout()){
						realtime+=dateFormat.format(checkinout.getChecktime())+" ";
					}
					kaoqinItem.setRealtime(realtime);
					
					kaoqinItems.add(kaoqinItem);
				}
				if(!schclass.isCheckout()){
					sn++;
					KaoqinItem kaoqinItem = new KaoqinItem();
					kaoqinItem.setSn(Long.valueOf(sn));
					kaoqinItem.setDate(dateCheck.getDate());
					cn.fyg.kq.domain.model.kaoqin.Schclass kqsch = new cn.fyg.kq.domain.model.kaoqin.Schclass();
					Date checkouttime1 = schclass.getCheckouttime1();
					Date checkouttime2 = schclass.getCheckouttime2();
					DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
					kqsch.setName(schclass.getSchname());
					kqsch.setBegendtime(dateFormat.format(checkouttime1)+"-"+dateFormat.format(checkouttime2));
					kqsch.setInout(SchclassInout.out);
					kaoqinItem.setSchclass(kqsch);
					
					String realtime="";
					for(Checkinout checkinout:dateCheck.getCheckinout()){
						realtime+=dateFormat.format(checkinout.getChecktime())+" ";
					}
					kaoqinItem.setRealtime(realtime);
					
					kaoqinItems.add(kaoqinItem);
				}
			}
		}
		
		if(kaoqinItems.size()>0){
			Kaoqin kaoqin = new Kaoqin();
			kaoqin.setNo(null);
			kaoqin.setUser(checkuser.getUser());
			kaoqin.setMonthitem(monthItem);
			kaoqin.setState(null);
			kaoqin.setItem_all(kaoqinItems.size());
			kaoqin.setItem_real(0);
			kaoqin.setComp("fangchan");
			
			kaoqin.setKaoqinItems(kaoqinItems);
		
			
			this.kaoqinService.save(kaoqin);
		}
		
	}
	
	

}
