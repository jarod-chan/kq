package trycatch;

public class Trycatch {
	
	public static void main(String[] args) {
		Trycatch trycatch = new Trycatch();
		try{
			trycatch.div();
		}catch(RuntimeException e){
			System.out.println("catch exception");
			System.out.println(e);
			throw e;
		}
		System.out.println("cant run this code");
	}
	
	public float div(){
		try{
			float x=1/0;
			return x;
		}finally{
			System.out.println("finally");
		}
	}

}
