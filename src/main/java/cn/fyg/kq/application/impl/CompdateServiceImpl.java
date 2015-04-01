package cn.fyg.kq.application.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.kq.application.CompdateService;
import cn.fyg.kq.domain.model.vacation.back.Back;
import cn.fyg.kq.domain.model.vacation.back.BackRepository;
import cn.fyg.kq.domain.model.vacation.common.Dayitem;
import cn.fyg.kq.domain.model.vacation.compdate.Compdate;
import cn.fyg.kq.domain.model.vacation.compdate.CompdateFactory;
import cn.fyg.kq.domain.model.vacation.compdate.CompdateRepository;
import cn.fyg.kq.domain.model.vacation.compdate.Datastate;
import cn.fyg.kq.domain.model.vacation.compdate.DayResult;
import cn.fyg.kq.domain.model.vacation.compdate.WaitAction;
import cn.fyg.kq.domain.model.vacation.leave.Leave;
import cn.fyg.kq.domain.model.vacation.leave.LeaveRepository;

@Service("compdateService")
public class CompdateServiceImpl implements CompdateService {
	
	@Autowired
	CompdateRepository compdateRepository;
	@Autowired
	LeaveRepository leaveRepository;
	@Autowired
	BackRepository backRepository;

	@Override
	@Transactional
	public Compdate append(Dayitem dayitem) {
		Compdate compdate=creatIfNotExist(dayitem);
		compdate.setVacation(dayitem);
		compdate.setWaitAction(WaitAction.wait_add);
		return compdateRepository.save(compdate);
	}
	
	private Compdate creatIfNotExist(Dayitem dayitem){
		Compdate compdate = compdateRepository.findByVacation(dayitem);
		return compdate==null?CompdateFactory.create():compdate;
	}

	@Override
	@Transactional
	public void remove(Dayitem dayitem) {
		Compdate compdate=creatIfNotExist(dayitem);
		compdate.setVacation(dayitem);
		compdate.setWaitAction(WaitAction.wait_remove);
		compdateRepository.save(compdate);
	}

	@Override
	@Transactional
	public void syncCompdate() {
		List<Compdate> compdates = compdateRepository.findByWaitActionNot(WaitAction.normal);
		List<Compdate> deleteCompdates=new ArrayList<Compdate>();
		for (Compdate compdate : compdates) {
			if(compdate.getDatastate()==Datastate.none && compdate.getWaitAction()==WaitAction.wait_remove){
				deleteCompdates.add(compdate);
				continue;
			}
			if(compdate.getDatastate()==Datastate.none && compdate.getWaitAction()==WaitAction.wait_add){
				appendCompdateLeave(compdate.getVacation());
				appendCompdateBack(compdate.getVacation());
				setCompdateNormal(compdate);
				continue;
			}
			if(compdate.getDatastate()==Datastate.exist && compdate.getWaitAction()==WaitAction.wait_remove){
				removeCompdateLeave(compdate.getVacation());
				removeCompdateBack(compdate.getVacation());
				deleteCompdates.add(compdate);
				continue;
			}
			if(compdate.getDatastate()==Datastate.exist && compdate.getWaitAction()==WaitAction.wait_add){
				setCompdateNormal(compdate);
				continue;
			}
		}
		compdateRepository.delete(deleteCompdates);
	}
	

	/**
	 * 设置compdate为正常
	 * @param compdate
	 */
	public void setCompdateNormal(Compdate compdate) {
		compdate.setDatastate(Datastate.exist);
		compdate.setWaitAction(WaitAction.normal);
	}
	

	private void appendCompdateLeave(Dayitem dayitem) {
		List<Leave> leaves = leaveRepository.findByDayitemContain(dayitem.getDate(), dayitem.getAmpm());
		for (Leave leave : leaves) {
			leave.setActurlDay(leave.getActurlDay().subtract(Dayitem.ITEM_VALUE));
		}
	}

	private void removeCompdateLeave(Dayitem dayitem) {
		List<Leave> leaves = leaveRepository.findByDayitemContain(dayitem.getDate(), dayitem.getAmpm());
		for (Leave leave : leaves) {
			leave.setActurlDay(leave.getActurlDay().add(Dayitem.ITEM_VALUE));
		}	
	}
	
	private void appendCompdateBack(Dayitem dayitem){
		List<Back> backs = backRepository.findByDayitemContain(dayitem.getDate(), dayitem.getAmpm());
		for(Back back:backs){
			back.setActurlDay(back.getActurlDay().subtract(Dayitem.ITEM_VALUE));
		}
	}

	private void removeCompdateBack(Dayitem dayitem){
		List<Back> backs = backRepository.findByDayitemContain(dayitem.getDate(), dayitem.getAmpm());
		for(Back back:backs){
			back.setActurlDay(back.getActurlDay().add(Dayitem.ITEM_VALUE));
		}
	}

	@Override
	@Transactional
	public void syncCompdateYear(Long year) {};
	

	@Override
	public DayResult computerDay(Dayitem begDayitem, Dayitem endDayitem) {
		
		Long datediff=(endDayitem.getDate().getTime()-begDayitem.getDate().getTime())/(1000*60*60*24)*2+endDayitem.getAmpm().value()-begDayitem.getAmpm().value()+1;
		Long itemNums=compdateRepository.countByBegDayitemAndEndDayitem(begDayitem.getDate(), begDayitem.getAmpm(), endDayitem.getDate(), endDayitem.getAmpm());
			
		BigDecimal natureDay = new BigDecimal(datediff).divide(new BigDecimal("2"), 2, BigDecimal.ROUND_HALF_UP);
		BigDecimal acturlDay = natureDay.subtract(new BigDecimal(itemNums).divide(new BigDecimal("2"), 2, BigDecimal.ROUND_HALF_UP));
			
		return new DayResult().natureDay(natureDay).acturlDay(acturlDay);
	}

}
