package testthearad;

public class TestTherad {
	
	public static void main2(String[] args) throws InterruptedException {
		Object obj = new Object();
        obj.wait();
        obj.notifyAll();
	}
	
	public static void main(String[] args) throws InterruptedException {
		Object obj = new Object();
		synchronized (obj) {
			obj.wait();
			System.out.println("get lock");
		}
	}

}
