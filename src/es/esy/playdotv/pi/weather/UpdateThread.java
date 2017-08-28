package es.esy.playdotv.pi.weather;

import java.io.IOException;

public class UpdateThread extends Thread{
	
	@Override
	public void run(){
		Weather.setApiKey("26c8e7f737e2835dc2bbaf8c8ed3e546");
		Weather.setPlace("Lipany,SK");
		try{
			while(true){
				Weather.update();
				System.out.println("----Weather data updated----");
				Thread.sleep(1200000);
			}
		}catch(IOException e){
			System.err.println("Cannot update weather info");
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}

}
