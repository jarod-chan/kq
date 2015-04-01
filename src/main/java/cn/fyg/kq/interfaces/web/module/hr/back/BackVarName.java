package cn.fyg.kq.interfaces.web.module.hr.back;

public interface BackVarName {
	
	/**
	 *是否同意，经理是否确认销假日期
	 */
	String IS_PASS="isPass";
	
	/**
	 * 请假单Id
	 */
	String LEAVE_ID="leaveId";
	
	/**
	 * 是否正常销假
	 * 当销假日期在请假日期之内
	 */
	String IS_NORMAL_BACK="isNormalBack";
	
	/**
	 * 实际请假天数
	 */
	String ACTURL_DAY="acturlDay";
}
