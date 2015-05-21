package cn.fyg.kq.domain.model.nogenerator.look;

import cn.fyg.kq.domain.model.nogenerator.generator.Pattern;



public interface LockService {
	
	/**
	 * 获得编号锁
	 * @param pattern
	 * @return
	 */
	Lock getLock(Pattern<? extends Object> pattern);
	

}
