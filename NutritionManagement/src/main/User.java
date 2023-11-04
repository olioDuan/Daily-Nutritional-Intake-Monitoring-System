package main;

import java.util.*;

public class User {
	private User() {
		days=new ArrayList<>();
		foodList=new ArrayList<>(); //TODO: update to import txt on init
		//WIP
	}
	
	private static User instance = new User();
	
	public static User getInstance() {
		return instance;
	}
	
	private ArrayList<Day> days;
	private ArrayList<Food> foodList;
	
}
