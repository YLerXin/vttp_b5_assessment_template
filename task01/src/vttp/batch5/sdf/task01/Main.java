package vttp.batch5.sdf.task01;

// Use this class as the entry point of your program
//yop 5 days with highest num of cyclist 
//need a string seasons to registered
import java.io.*;
import java.util.*;
import java.util.stream.*;

import vttp.batch5.sdf.task01.models.BikeEntry;
import vttp.batch5.sdf.task01.models.BikeEntry1;

public class Main {

	public static void main(String[] args) {
		String filePath = ("C:\\Users\\yongl\\VTTP\\vttp_b5_assessment_template\\task01\\day.csv");
		try{
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			String line;
			List<BikeEntry1> bentry = new ArrayList<>();
			String[] header = ((br.readLine()+ ",total").split(",")); //read the header line
			while((line = br.readLine()) != null){
				String templine = line;
				String[] tempvalues = templine.split(",");
				int total = Integer.parseInt(tempvalues[8]) + Integer.parseInt(tempvalues[9]);
				line = line + ","+String.valueOf(total);
				String[] values = line.split(",");
				bentry.add(BikeEntry1.toBikeEntry(values));
			}

			List<BikeEntry1> tempbentry = bentry.stream()
			.sorted(Comparator.comparingDouble(BikeEntry1::getTotal).reversed())
			.limit(5)
			.collect(Collectors.toList());

			int posint = 1;
			String position = "";
			String season;
			String day;
			String month;
			int total;
			String weather = "";
			String holiday = "";
			
			for (BikeEntry1 entry:tempbentry){
			season = Utilities.toSeason(entry.getSeason());
			day = Utilities.toWeekday(entry.getWeekday());
			month = Utilities.toMonth(entry.getMonth());
			if (posint == 1){
				position = "highest";
			}else if (posint == 2){
				position = "second highest";
			}
			else if (posint == 3){
				position = "third highest";
			}	
			else if (posint == 4){
				position = "forth highest";
			}
			else if (posint == 5){
				position = "fifth highest";
			}
			total = entry.getTotal();
			if(entry.isHoliday() == true){
				holiday = "a holiday";
			}else if(entry.isHoliday() == false){
				holiday = "not a holiday";
			}
			if(entry.getWeather() == 1){weather = "Clear, Few clouds, Partly cloudy, Partly cloudy";}
			else if(entry.getWeather() == 2){weather = "Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist";}
			else if(entry.getWeather() == 3){weather = "Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds";}
			else if(entry.getWeather() == 4){weather = "Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog";}


			System.out.printf("The %s (position) recorded number of cyclists was in %s,",position,season);
			System.out.printf("(season),on a %s (day) in the month of %s (month).",day,month);
			System.out.printf("There were a total of %d (total) cyclist. The weather was ",total);
			System.out.printf("%s.",weather);
			System.out.printf(" %s was %s.",day,holiday);
			System.out.println();
			System.out.println();

			posint ++;

				//System.out.println(entry.getRegistered()+ " " +entry.getCasual()+ " " +entry.getTotal());
			}

		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
