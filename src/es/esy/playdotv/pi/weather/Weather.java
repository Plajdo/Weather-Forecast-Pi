package es.esy.playdotv.pi.weather;

import java.io.IOException;
import java.util.Date;

import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.DailyForecast;
import net.aksingh.owmjapis.OpenWeatherMap;
import net.aksingh.owmjapis.Tools;
import net.aksingh.owmjapis.OpenWeatherMap.Language;
import net.aksingh.owmjapis.OpenWeatherMap.Units;

public class Weather{
	
	private static String place;
	private static String api_key;
	
	private static String city;
	private static double minTemp;
	private static double maxTemp;
	private static double currentTemp;
	private static double humidity;
	private static double pressure;
	private static String weather;
	private static double cloudCoverage;
	private static double windSpeed;
	private static double windGust;
	private static double windDirection;
	private static String windDirectionText;
	private static double chanceOfRain;
	private static Date sunrise;
	private static Date sunset;
	private static float dailyHumidity;
	private static float dailyPressure;
	private static float dailyPercentageOfClouds;
	private static float dailyRainChance;
	private static float dailySnowChance;
	private static float dailyMorningTemperature;
	private static float dailyDayTemperature;
	private static float dailyNightTemperature;
	private static float dailyEveningTemperature;
	private static float dailyMinimumTemperature;
	private static float dailyMaximumTemperature;
	private static float dailyWindDirection;
	private static String dailyWindDirectionText;
	private static float dailyWindSpeed;
	private static long cityPopulation;
	private static int weatherID;
	private static String dailyWeather;
	private static int dailyWeatherID;
	
	public static void update() throws IOException{
		OpenWeatherMap owm = new OpenWeatherMap(Units.METRIC, Language.SLOVAK, api_key);
		CurrentWeather cwd = owm.currentWeatherByCityName(place);
		DailyForecast df = owm.dailyForecastByCityName(place, Byte.parseByte("5"));
		
		if(cwd.isValid()){
			if(cwd.hasCityName()){
				city = cwd.getCityName();
				System.out.println(city);
			}else{
				System.err.println("Error: No city name");
			}
			
			if(cwd.getMainInstance().hasMinTemperature()){
				minTemp = cwd.getMainInstance().getMinTemperature();
				System.out.println("minTemp: " + minTemp);
			}else{
				System.err.println("Error: No min temperature data");
			}
			
			if(cwd.getMainInstance().hasMaxTemperature()){
				maxTemp = cwd.getMainInstance().getMaxTemperature();
				System.out.println("maxTemp: " + maxTemp);
			}else{
				System.err.println("Error: No max temperature data");
			}
			
			if(cwd.getMainInstance().hasTemperature()){
				currentTemp = cwd.getMainInstance().getTemperature();
				System.out.println("currentTemp: " + currentTemp);
			}else{
				System.err.println("Error: No temperature data");
			}
			
			if(cwd.getMainInstance().hasHumidity()){
				humidity = cwd.getMainInstance().getHumidity();
				System.out.println("humidity: " + humidity);
			}else{
				System.err.println("Error: No humidity data");
			}
			
			if(cwd.getMainInstance().hasPressure()){
				pressure = cwd.getMainInstance().getPressure();
				System.out.println("pressure: " + pressure);
			}else{
				System.err.println("Error: No pressure data");
			}
			
			if(cwd.hasWeatherInstance()){
				weather = cwd.getWeatherInstance(0).getWeatherDescription();
				weatherID = cwd.getWeatherInstance(0).getWeatherCode();
				System.out.println("weather: " + weather + " " + weatherID);
			}else{
				System.err.println("Error: No weather data");
			}
			
			if(cwd.hasCloudsInstance()){
				cloudCoverage = cwd.getCloudsInstance().getPercentageOfClouds();
				System.out.println("cloudCoverage: " + cloudCoverage);
			}else{
				System.err.println("Error: No cloud data");
			}
			
			if(cwd.hasWindInstance()){
				Tools t = new Tools();
				windSpeed = cwd.getWindInstance().getWindSpeed();
				windGust = cwd.getWindInstance().getWindGust();
				windDirection = cwd.getWindInstance().getWindDegree();
				windDirectionText = t.convertDegree2Direction(cwd.getWindInstance().getWindDegree());
				System.out.println("Wind: " + windSpeed + " " + windGust + " " + windDirection + " " + windDirectionText);
			}else{
				System.err.println("Error: No wind data");
			}
			
			if(cwd.hasRainInstance()){
				chanceOfRain = cwd.getRainInstance().getRain();
				System.out.println("chanceOfRain: " + chanceOfRain);
			}else{
				System.err.println("Error: No rain data");
			}
			
			if(cwd.hasSysInstance()){
				sunrise = cwd.getSysInstance().getSunriseTime();
				sunset = cwd.getSysInstance().getSunsetTime();
				System.out.println("Sunrise: " + sunrise + "\nSunset: " + sunset);
			}else{
				System.err.println("Error: No sunset/sunrise data");
			}
			
		}else{
			System.err.println("Error: Cannot get weather data");
		}
		
		if(df.isValid()){
			if(df.hasCityInstance()){
				cityPopulation = df.getCityInstance().getCityPopulation();
				System.out.println("cityPopulation: " + cityPopulation);
			}else{
				System.err.println("DF Error occured");
			}
			
			if(df.hasForecastCount()){
				Tools t = new Tools();
				dailyHumidity = df.getForecastInstance(0).getHumidity();
				dailyPercentageOfClouds = df.getForecastInstance(0).getPercentageOfClouds();
				dailyPressure = df.getForecastInstance(0).getPressure();
				dailyRainChance = df.getForecastInstance(0).getRain();
				dailySnowChance = df.getForecastInstance(0).getSnow();
				dailyMorningTemperature = df.getForecastInstance(0).getTemperatureInstance().getMorningTemperature();
				dailyDayTemperature = df.getForecastInstance(0).getTemperatureInstance().getDayTemperature();
				dailyEveningTemperature = df.getForecastInstance(0).getTemperatureInstance().getEveningTemperature();
				dailyNightTemperature = df.getForecastInstance(0).getTemperatureInstance().getNightTemperature();
				dailyMinimumTemperature = df.getForecastInstance(0).getTemperatureInstance().getMinimumTemperature();
				dailyMaximumTemperature = df.getForecastInstance(0).getTemperatureInstance().getMaximumTemperature();
				dailyWindDirection = df.getForecastInstance(0).getWindDegree();
				dailyWindDirectionText = t.convertDegree2Direction(df.getForecastInstance(0).getWindDegree());
				dailyWindSpeed = df.getForecastInstance(0).getWindSpeed();
				dailyWeather = df.getForecastInstance(0).getWeatherInstance(0).getWeatherDescription();
				dailyWeatherID = df.getForecastInstance(0).getWeatherInstance(0).getWeatherCode();
				System.out.println("dailyHumidity: " + dailyHumidity);
				System.out.println("dailyPercentageOfClouds: " + dailyPercentageOfClouds);
				System.out.println("dailyPressure: " + dailyPressure);
				System.out.println("dailyRainChance: " + dailyRainChance);
				System.out.println("dailySnowChance: " + dailySnowChance);
				System.out.println("dailyMorningTemperature: " + dailyMorningTemperature);
				System.out.println("dailyDayTemperature: " + dailyDayTemperature);
				System.out.println("dailyEveningTemperature: " + dailyEveningTemperature);
				System.out.println("dailyNightTemperature: " + dailyNightTemperature);
				System.out.println("dailyMinimumTemperature: " + dailyMinimumTemperature);
				System.out.println("dailyMaximumTemperature: " + dailyMaximumTemperature);
				System.out.println("dailyWindDirection: " + dailyWindDirection);
				System.out.println("dailyWindDirectionText: " + dailyWindDirectionText);
				System.out.println("dailyWindSpeed: " + dailyWindSpeed);
				System.out.println("dailyWeather: " + dailyWeather + " " + dailyWeatherID);
			}else{
				System.err.println("DF Error occured");
			}
			
		}
		
	}

	public static String getPlace() {
		return place;
	}

	public static String getApi_key() {
		return api_key;
	}

	public static String getCity() {
		return city;
	}

	public static double getMinTemp() {
		return minTemp;
	}

	public static double getMaxTemp() {
		return maxTemp;
	}

	public static double getCurrentTemp() {
		return currentTemp;
	}

	public static double getHumidity() {
		return humidity;
	}

	public static double getPressure() {
		return pressure;
	}

	public static String getWeather() {
		return weather;
	}

	public static double getCloudCoverage() {
		return cloudCoverage;
	}

	public static double getWindSpeed() {
		return windSpeed;
	}

	public static double getWindGust() {
		return windGust;
	}

	public static double getWindDirection() {
		return windDirection;
	}

	public static String getWindDirectionText() {
		return windDirectionText;
	}

	public static double getChanceOfRain() {
		return chanceOfRain;
	}

	public static Date getSunrise() {
		return sunrise;
	}

	public static Date getSunset() {
		return sunset;
	}

	public static float getDailyHumidity() {
		return dailyHumidity;
	}

	public static float getDailyPressure() {
		return dailyPressure;
	}

	public static float getDailyPercentageOfClouds() {
		return dailyPercentageOfClouds;
	}

	public static float getDailyRainChance() {
		return dailyRainChance;
	}

	public static float getDailySnowChance() {
		return dailySnowChance;
	}

	public static float getDailyMorningTemperature() {
		return dailyMorningTemperature;
	}

	public static float getDailyDayTemperature() {
		return dailyDayTemperature;
	}

	public static float getDailyNightTemperature() {
		return dailyNightTemperature;
	}

	public static float getDailyEveningTemperature() {
		return dailyEveningTemperature;
	}

	public static float getDailyMinimumTemperature() {
		return dailyMinimumTemperature;
	}

	public static float getDailyMaximumTemperature() {
		return dailyMaximumTemperature;
	}

	public static float getDailyWindDirection() {
		return dailyWindDirection;
	}

	public static String getDailyWindDirectionText() {
		return dailyWindDirectionText;
	}

	public static float getDailyWindSpeed() {
		return dailyWindSpeed;
	}

	public static long getCityPopulation() {
		return cityPopulation;
	}
	
	public static long getWeatherID(){
		return weatherID;
	}

	public static String getDailyWeather() {
		return dailyWeather;
	}

	public static int getDailyWeatherID() {
		return dailyWeatherID;
	}

	public static void setPlace(String place) {
		Weather.place = place;
	}

	public static void setApiKey(String api_key) {
		Weather.api_key = api_key;
	}
	
}
