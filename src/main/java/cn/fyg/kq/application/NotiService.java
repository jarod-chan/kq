package cn.fyg.kq.application;

import cn.fyg.kq.application.common.ServiceQuery;
import cn.fyg.kq.domain.model.noti.Noti;

public interface NotiService extends ServiceQuery<Noti> {

	Noti find(Long notiId);

	String url(String str,String url);
	
	Noti send(String userid,String message);

	boolean read(Long notiId);
}
