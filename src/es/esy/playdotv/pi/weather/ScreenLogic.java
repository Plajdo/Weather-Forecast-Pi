package es.esy.playdotv.pi.weather;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import es.esy.playdotv.gpiolib.GPIO;
import es.esy.playdotv.gpiolib.Mode;;

public class ScreenLogic extends Thread{
	
	UpdateThread ut = new UpdateThread();
	
	private int getTime(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		return Integer.parseInt(sdf.format(cal.getTime()));
	}
	
	private String getDay(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("u");
		switch(Integer.parseInt(sdf.format(cal.getTime()))){
		case 1:
			return "pondelok";
		case 2:
			return "utorok";
		case 3:
			return "streda";
		case 4:
			return "stvrtok";
		case 5:
			return "piatok";
		case 6:
			return "sobota";
		case 7:
			return "nedela";
		default:
			return "error";
		}
	}
	
	private String getDayShort(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("u");
		switch(Integer.parseInt(sdf.format(cal.getTime()))){
		case 1:
			return "PON";
		case 2:
			return "UTO";
		case 3:
			return "STR";
		case 4:
			return "STV";
		case 5:
			return "PIA";
		case 6:
			return "SOB";
		case 7:
			return "NED";
		default:
			return "ERR";
		}
	}
	
	private String getDate(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("d.M.YYYY");
		return sdf.format(cal.getTime());
	}
	
	private String make16(String s){
		StringBuffer ob = new StringBuffer(16);
		ob.append(s);
		for(int i = s.length(); i < 16; i++){
			ob.append(" ");
		}
		return ob.toString();
	}
	
	public void run(){
		Screen scr = new Screen();
		DecimalFormat dfTemp = new DecimalFormat(" 00.0;-00.0");
		DecimalFormat dfWind = new DecimalFormat("#00.00");
		DecimalFormat dfPressure = new DecimalFormat("#000");
		DecimalFormat dfHumidity = new DecimalFormat("#0");
		//Thread led = new LED();

		while(true){
			try{
				/*
				 * Screen 1
				 */
				if(getTime() <= 9){
					scr.write("Dobre rano      " + "Dnes je " + getDay());
				}else if(getTime() >= 10 && getTime() <= 17){
					scr.write("Prajem pekny den" + "Dnes je " + getDay());
				}else if(getTime() >= 18){
					scr.write("Dobry vecer     " + "Dnes je " + getDay());
				}
				Thread.sleep(30000);
				
				/*
				 * Screen 2
				 */
				String s = getDayShort() + ". " + getDate();
				scr.write(make16(s) + "T: " + dfTemp.format(Weather.getCurrentTemp()) + " C");
				Thread.sleep(30000);
				
				/*
				 * Screen 3
				 */
				String s1 = "Tlak: " + dfPressure.format(Weather.getPressure()) + " hPa";
				String s2 = "Vlhkost: " + dfHumidity.format(Weather.getHumidity()) + "%";
				scr.write(make16(s1) + make16(s2));
				Thread.sleep(30000);
				
				/*
				 * Screen 4
				 */
				scr.write("Rychlost vetra: " + dfWind.format(Weather.getWindSpeed()*3.6) + " km/h " + Weather.getWindDirectionText());
				Thread.sleep(30000);
				
				/*
				 * Screen 5
				 */
				String s3 = "Pocasie teraz:";
				String s4 = Weather.getWeather();
				scr.write(make16(s3) + s4);
				Thread.sleep(30000);
				
			}catch(IOException e){
				e.printStackTrace();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
		}
		
	}
	
}

class LED extends Thread{
	@Override
	public void run(){
		GPIO g = new GPIO(4, Mode.OUT);
		g.gpioON();
		try{
			Thread.sleep(500);
		}catch(InterruptedException e){
			//Do not do anything
		}
		g.gpioOFF();

	}
	
}