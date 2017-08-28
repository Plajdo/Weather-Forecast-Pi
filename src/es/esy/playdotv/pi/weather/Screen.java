package es.esy.playdotv.pi.weather;

import java.io.IOException;

public class Screen{
	
	public void write(String text) throws IOException{
		ProcessBuilder pb = new ProcessBuilder("sudo", "python", "text.py", text);
		pb.start();
		
	}
	
	public void flush() throws IOException{
		ProcessBuilder pb = new ProcessBuilder("sudo", "python", "text.py", "\"\"");
		pb.start();
		
	}
	
}
