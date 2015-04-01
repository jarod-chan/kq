package cn.fyg.kq.domain.model.vacation.compdate;

public class CompdateFactory {
	
	/**
	 * 生成新对象的状态为一个数据无效状态
	 * @return
	 */
	public static Compdate create() {
		Compdate compdate=new Compdate();
		compdate.setDatastate(Datastate.none);
		compdate.setWaitAction(WaitAction.wait_remove);
		return compdate;
	}

}
