package es.esy.playdotv.pi.weather;

public class Main {
	
	public static void main(String[] args){
		Thread t1 = new UpdateThread();
		Thread t2 = new ScreenLogic();
		
		t1.start();
		t2.start();
		System.out.println("Threads t1 and t2 started");
		
	}
	
}
