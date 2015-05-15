package cn.fyg.kq.application;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import cn.fyg.kq.domain.model.process.ProcessFile;

public interface WorkflowService {
	
	List<ProcessFile> getProcessFile(String directoryPath)throws FileNotFoundException; 
	
	void deployFile(String directoryPath,String filename)throws FileNotFoundException;
	
	/**
	 * 临时解决方案
	 * 获得某个节点的用户
	 * @param context
	 * @param node
	 * @return
	 */
	String getUser(Map<String,Object> context,String node);
}
