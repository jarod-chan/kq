package cn.fyg.kq.application.ReptService;

public interface Range {
	
	Range falseRange=new Range(){
		@Override
		public boolean inRange(int val) {
			return false;
		}};
		
	Range trueRange=new Range(){
		@Override
		public boolean inRange(int val) {
			return true;
		}
	};
	
	boolean inRange(int val);

}
