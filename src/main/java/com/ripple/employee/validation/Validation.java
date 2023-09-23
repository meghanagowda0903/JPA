package com.ripple.employee.validation;


public class Validation {
	 public static boolean isValidName(String name) {
	      
	        return !name.isEmpty() && name.matches("^[a-zA-Z\\s]+$");
	    }

	    public static boolean isValidEmail(String email) {
	        
	        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
	    }
	    public static boolean isValidAge(int age) {
	        
	        return age >= 18 && age <= 100;
	    }

	    
	   

}
