package cn.fyg.kq.domain.shared.process;

/**
 *流程单据对象
 */
public interface ProcessObj {
	
	/**
	 * 返回流程单据的主题
	 * @return
	 */
	public String getTitle();
	
	/**
	 * 返回流程id
	 * @return
	 */
	public String getProcessId();
	
	/**
	 * 设置流程id
	 * @param processId
	 */
	public void setProcessId(String processId);

}
