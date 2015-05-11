package cn.fyg.kq.application;

import java.io.FileNotFoundException;
import java.util.List;

import cn.fyg.kq.domain.model.process.ProcessFile;

public interface WorkflowService {
	
	List<ProcessFile> getProcessFile(String directoryPath)throws FileNotFoundException; 
	
	void deployFile(String directoryPath,String filename)throws FileNotFoundException;

}
