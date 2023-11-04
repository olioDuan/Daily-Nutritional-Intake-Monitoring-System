package main;

import java.time.LocalDate;
import java.util.*;

public class Day {
	public Day(String dateString) {
		//WIP
		try{
			this.date=LocalDate.parse(dateString);
			this.foodPortions=new ArrayList<>();
		}
		catch(Exception e){
			
		}
		
	}
	
	private LocalDate date;
	private ArrayList<FoodPortion> foodPortions;
}
