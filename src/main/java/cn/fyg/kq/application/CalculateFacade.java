package cn.fyg.kq.application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import cn.fyg.kq.domain.model.checkuser.Checkuser;
import cn.fyg.kq.domain.model.checkuser.CheckuserSpecs;
import cn.fyg.kq.domain.model.checkuser.Kqstat;
import cn.fyg.kq.domain.model.kaoqin.busi.Kaoqin;
import cn.fyg.kq.domain.model.kaoqin.busi.KaoqinItem;
import cn.fyg.kq.domain.model.kaoqin.busi.MonthItem;
import cn.fyg.kq.domain.model.kaoqin.busi.SchclassInout;
import cn.fyg.kq.domain.model.period.Period;
import cn.fyg.kq.domain.model.period.PeriodState;
import cn.fyg.kq.domain.shared.kq.Comp;
import cn.fyg.zktime.domain.Checkinout;
import cn.fyg.zktime.domain.Schclass;
import cn.fyg.zktime.domain.monthcheck.DateCheck;
import cn.fyg.zktime.domain.monthcheck.MonthCheck;
import cn.fyg.zktime.domain.monthcheck.SchclassInOut;
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
		List<Checkuser> checkuserList = allCheckUserOfComp(period.getComp());	
		List<MonthCheck> monthCheckList=allMonthCheck(checkuserList,period);
		
		for(int i=0,len=checkuserList.size();i<len;i++){
			Checkuser checkuser = checkuserList.get(i);
			MonthCheck monthCheck = monthCheckList.get(i);
			monthCheckToKaoqin(checkuser,period,monthCheck);
		}
				
		finishCal(period);
	}


	/*
	 * 查找本公司所有参与考勤的人员
	 */
	private List<Checkuser> allCheckUserOfComp(Comp comp) {
		Specification<Checkuser> inComp = CheckuserSpecs.inComp(comp);
		Specification<Checkuser> kqstat = CheckuserSpecs.isKqstat(Kqstat.yes);
		Specifications<Checkuser> specs=Specifications.where(inComp).and(kqstat);
		Sort sort=new Sort(new Order("userid"));
		
		List<Checkuser> checkuserList = this.checkuserService.findAll(specs, sort);
		return checkuserList;
	}


	/*
	 * 返回所有人员对应的考勤结果
	 */
	private List<MonthCheck> allMonthCheck(List<Checkuser> checkuserList,Period period) {
		List<Integer> userids=new ArrayList<Integer>();
		for(Checkuser checkuser:checkuserList){
			userids.add(checkuser.getUserid());
		}
		MonthItem monthitem = period.getMonthitem();
		return this.monthCheckService.getMonthCheck(userids,monthitem.getYear(), monthitem.getMonth());
	}

	/*
	 *更改计算状态为计算完成
	 */
	private void finishCal(Period period) {
		period.setState(PeriodState.finishcal);
		this.periodService.save(period);
	}



	public void monthCheckToKaoqin(Checkuser checkuser,Period period,MonthCheck monthcheck){
		List<KaoqinItem> kaoqinItems = new ArrayList<KaoqinItem>();
		int sn=0;
		for (DateCheck dateCheck : monthcheck.getDatechecks()) {
			for(SchclassInOut inOut:dateCheck.getSchclassInOuts()){
				Schclass schclass = inOut.getSchclass();
				if(!inOut.isCheckin()){
					sn++;
					KaoqinItem kaoqinItem = new KaoqinItem();
					kaoqinItem.setSn(Long.valueOf(sn));
					kaoqinItem.setDate(dateCheck.getDate());
					cn.fyg.kq.domain.model.kaoqin.busi.Schclass kqsch = new cn.fyg.kq.domain.model.kaoqin.busi.Schclass();
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
					kaoqinItem.setRealtime(realtime.trim());
					
					kaoqinItems.add(kaoqinItem);
				}
				if(!inOut.isCheckout()){
					sn++;
					KaoqinItem kaoqinItem = new KaoqinItem();
					kaoqinItem.setSn(Long.valueOf(sn));
					kaoqinItem.setDate(dateCheck.getDate());
					cn.fyg.kq.domain.model.kaoqin.busi.Schclass kqsch = new cn.fyg.kq.domain.model.kaoqin.busi.Schclass();
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
					kaoqinItem.setRealtime(realtime.trim());
					
					kaoqinItems.add(kaoqinItem);
				}
			}
		}
		
		if(kaoqinItems.size()>0){
			Kaoqin kaoqin = new Kaoqin();
			kaoqin.setNo(null);
			kaoqin.setUser(checkuser.getUser());
			kaoqin.setMonthitem(period.getMonthitem());
			kaoqin.setState(null);
			kaoqin.setItem_all(kaoqinItems.size());
			kaoqin.setItem_real(0);
			kaoqin.setComp(period.getComp().toString());
			
			kaoqin.setKaoqinItems(kaoqinItems);
		
			
			this.kaoqinService.save(kaoqin);
		}
		
	}
	
	

}
