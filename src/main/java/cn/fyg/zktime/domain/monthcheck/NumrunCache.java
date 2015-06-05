package cn.fyg.zktime.domain.monthcheck;

import java.util.HashMap;
import java.util.Map;

import cn.fyg.zktime.domain.Numrun;
import cn.fyg.zktime.domain.UserrunMapper;

/**
 *numrun的缓冲可以提高效率
 */
public class NumrunCache {
	
	private UserrunMapper userrunMapper;
	
	private Map<Integer,Numrun> cache=new HashMap<Integer,Numrun>();
	
	
	public NumrunCache(UserrunMapper userrunMapper) {
		super();
		this.userrunMapper = userrunMapper;
	}

	//当某个班次没有时段时，数据库可能返回null,所以调用该函数需要判断是否null
	public Numrun get(int numrunid){
		if(!this.cache.containsKey(numrunid)){
			Numrun numrun = this.userrunMapper.findNumrun(numrunid);
			this.cache.put(numrunid, numrun);
		}
		return this.cache.get(numrunid);
	}

}
