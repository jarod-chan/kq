package cn.fyg.kq.application.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.RepositoryService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Joiner;

import cn.fyg.kq.application.ReptlineService;
import cn.fyg.kq.application.WorkflowService;
import cn.fyg.kq.domain.model.process.ProcessFile;
import cn.fyg.kq.domain.model.reptline.Reptline;
import cn.fyg.kq.interfaces.web.shared.tool.FlowConstant;

@Service("workflowService")
public class WorkflowServiceImpl implements WorkflowService {
	
	@Autowired
	RepositoryService repositoryService;

	@Override
	public List<ProcessFile> getProcessFile(String directoryPath) throws FileNotFoundException {
		File directory = new File(directoryPath);
		if(!directory.exists())
			throw new FileNotFoundException(String.format("directoryPath:[%s] not exists", directoryPath));
		String[] files = directory.list();
		List<ProcessFile> fileList = new ArrayList<ProcessFile>();
		for (int i = 0; i < files.length; i++) {
			ProcessFile processFile = new ProcessFile();
			processFile.setName(files[i].toString());
			fileList.add(processFile);
		}
		return fileList;
		
	}

	@Override
	@Transactional
	public void deployFile(String directoryPath, String filename)
			throws FileNotFoundException {
		String fullFilePath = directoryPath+ File.separator + filename;
		ZipInputStream inputStream = new ZipInputStream(new FileInputStream(fullFilePath));
		repositoryService.createDeployment()
		    .name(filename)
		    .addZipInputStream(inputStream)
		    .deploy();

	}
	
	@Autowired
	ReptlineService reptlineService;
	
	Map<String,Integer> processMap=new HashMap<String,Integer>();
	{
		processMap.put("gm_check", 1);
		processMap.put("vp_check", 2);
		processMap.put("dm_check", 3);
		processMap.put("start", 4);
	}

	@Override
	public String getUser(Map<String, Object> context, String node) {
		String fid=(String) context.get(FlowConstant.APPLY_USER);
		Reptline findByUser_fidAndLevel = this.reptlineService.findByUser_fidAndLevel(fid, 4);
		String code = findByUser_fidAndLevel.getCode();
		
		int level=this.processMap.get(node);
		
		String[] split = StringUtils.split(code,".");
		String[] splitTo=Arrays.copyOfRange(split, 0, level);
		
		Joiner joiner = Joiner.on(".").skipNulls();
		
		String newcode=joiner.join(splitTo);

		Reptline findByCode = this.reptlineService.findByCode(newcode);
		
		return findByCode.getUser().getFid();
	}

}
