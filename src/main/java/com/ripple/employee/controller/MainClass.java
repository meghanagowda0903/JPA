package com.ripple.employee.controller;

import com.ripple.employee.model.*;

import com.ripple.employee.service.*;
import com.ripple.employee.validation.*;


import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class MainClass {
	 public static void main(String[] args) {
	        EmployeeDAO employeeDAO = new EmployeeDAO();
	        Scanner scanner = new Scanner(System.in);

	        try {
	            while (true) {
	                System.out.println("Employee CRUD Operations:");
	                System.out.println("1. Create Employee");
	                System.out.println("2. Read Employee");
	                System.out.println("3. Update Employee");
	                System.out.println("4. Delete Employee");
	                System.out.println("5. Exit");
	                System.out.print("Enter your choice: ");
	                int choice = Integer.parseInt(scanner.nextLine());
	                
	                switch (choice) {
                    case 1:
                        createEmployee(scanner, employeeDAO);
                        break;
                    case 2:
                        readEmployee(scanner, employeeDAO);
                        break;
                    case 3:
                        updateEmployee(scanner, employeeDAO);
                        break;
                    case 4:
                        deleteEmployee(scanner, employeeDAO);
                        break;
                    case 5:
                    	 System.out.println("Are you sure you want to exit? (Enter 'yes' to confirm, 'no' to continue)");
    	                 String confirmExit = scanner.nextLine();
    	                 if (confirmExit.equalsIgnoreCase("yes")) {
    	                     scanner.close();
    	                     System.out.println("Closed...."); 
    	                     return;
    	                 } 
    	                 else if(confirmExit.equalsIgnoreCase("no")) {
    	                	 continue;
    	                 }
                       
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
	          }
	        } finally {
	            employeeDAO.close();
	            scanner.close();
	        }
	    }
	 private static void createEmployee(Scanner scanner, EmployeeDAO employeeDAO) {
	        Employee newEmployee = captureEmployeeData(scanner);
	        employeeDAO.createEmployee(newEmployee);
	        System.out.println("Employee created successfully.");
	    }

	 private static void readEmployee(Scanner scanner, EmployeeDAO employeeDAO) {
	        System.out.print("Enter Employee ID: ");
	        int id = Integer.parseInt(scanner.nextLine());
	        Employee employee = employeeDAO.getEmployeeById(id);
	        if (employee != null) {
	            System.out.println("Employee Details:");
	            System.out.println(employee);
	        } else {
	            System.out.println("Employee not found.");
	        }
	    }
	 private static void updateEmployee(Scanner scanner, EmployeeDAO employeeDAO) {
	        System.out.print("Enter Employee ID: ");
	        int id = Integer.parseInt(scanner.nextLine());
	        Employee employeeToUpdate = employeeDAO.getEmployeeById(id);
	        if (employeeToUpdate != null) {
	        	System.out.println("Current Employee Details:");
	            System.out.println("First Name: " + employeeToUpdate.getFirstName());
	            System.out.println("Last Name: " + employeeToUpdate.getLastName());
	            System.out.println("Email: " + employeeToUpdate.getEmail());
	            System.out.println("Age: " + employeeToUpdate.getAge());
	            System.out.println("Salary: " + employeeToUpdate.getSalary());
	            System.out.println("Hire Date: " + employeeToUpdate.getHireDate());
	            Employee updatedEmployee = new Employee();
	            updatedEmployee.setId(id);
	            
	            System.out.print("Enter new First Name or press Enter to keep existing: ");
	            String newFirstName = scanner.nextLine().trim();
	            if (!newFirstName.isEmpty()) {
	                updatedEmployee.setFirstName(newFirstName);
	            } else {
	                updatedEmployee.setFirstName(employeeToUpdate.getFirstName());
	            }
	            
	            System.out.print("Enter new Last Name or press Enter to keep existing: ");
	            String newLastName = scanner.nextLine().trim();
	            if (!newLastName.isEmpty()) {
	                updatedEmployee.setLastName(newLastName);
	            } else {
	                updatedEmployee.setLastName(employeeToUpdate.getLastName());
	            }
	            
	            System.out.print("Enter new Email or press Enter to keep existing: ");
	            String newEmail = scanner.nextLine().trim();
	            if (!newEmail.isEmpty()) {
	                updatedEmployee.setEmail(newEmail);
	            } else {
	                updatedEmployee.setEmail(employeeToUpdate.getEmail());
	            }
	            
	            System.out.print("Enter new Age or press Enter to keep existing: ");
	            String newAge = scanner.nextLine().trim();
	            if (!newAge.isEmpty()) {
	                updatedEmployee.setAge(Integer.parseInt(newAge));
	            } else {
	                updatedEmployee.setAge(employeeToUpdate.getAge());
	            }
	            
	            System.out.print("Enter new Salary or press Enter to keep existing: ");
	            String newSalary = scanner.nextLine().trim();
	            if (!newSalary.isEmpty()) {
	                updatedEmployee.setSalary(Integer.parseInt(newSalary));
	            } else {
	                updatedEmployee.setSalary(employeeToUpdate.getSalary());
	            }
	            
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            System.out.print("Enter new Hire Date (yyyy-MM-dd) or press Enter to keep existing): ");
	            String hireDateInput = scanner.nextLine().trim();
	            if (!hireDateInput.isEmpty()) {
	                try {
	                    Date hireDate = dateFormat.parse(hireDateInput);
	                    updatedEmployee.setHireDate(hireDate);
	                } catch (ParseException e) {
	                    System.out.println("Invalid date format. Hire Date will be kept as it is.");
	                }
	            } else {
	                updatedEmployee.setHireDate(employeeToUpdate.getHireDate());
	            }
	            
	            
	            employeeDAO.updateEmployee(updatedEmployee);
	            System.out.println("Employee updated successfully.");
	        } else {
	            System.out.println("Employee not found.");
	        }
	    }
	 private static void deleteEmployee(Scanner scanner, EmployeeDAO employeeDAO) {
	        System.out.print("Enter Employee ID to delete: ");
	        int id = Integer.parseInt(scanner.nextLine());
	        employeeDAO.deleteEmployee(id);
	        System.out.println("Employee deleted successfully.");
	    }
	 private static Employee captureEmployeeData(Scanner scanner) {
	        Employee newEmployee = new Employee();
	        while(true) {
	        System.out.print("Enter First Name: ");
	        String firstName=scanner.nextLine();
	        if(!Validation.isValidName(firstName)) {
	        	System.out.println("Please enter the name in alphabets");
	        	
	        }
	        else {
	        	newEmployee.setFirstName(firstName);
	        	break;
	        }
	       
	        	
	        }
	        while(true) {
	        System.out.print("Enter Last Name: ");
	        String lastName=scanner.nextLine();
	        if(!Validation.isValidName(lastName)) {
	        	System.out.println("Please enter the name in alphabets");
	        }
	        else {
	        	newEmployee.setLastName(lastName);
	        	break;
	        }
	        }
	        	
	        
	        
	        while(true) {
	        System.out.print("Enter Email: ");
	        String email=scanner.nextLine();
	        if(Validation.isValidEmail(email)) {
	        	System.out.println("Please enter the valid email");
	        }
	        else {
	        	newEmployee.setEmail(email);
	        	break;
	        }
	        	
	        }
	        
	        while(true) {
	        System.out.print("Enter Age: ");
	        int age=scanner.nextInt();
	        if(!Validation.isValidAge(age)) {
	        	System.out.println("Please enter the valid number");
	        }
	        else {
	        	newEmployee.setAge(age);
	        	break;
	        }
	        	
	        }
	        
	        while(true) {
	        System.out.print("Enter Salary: ");
	        int salary=scanner.nextInt();
	        if(Validation.isValidAge(salary)) {
	        	System.out.println("Please enter the valid number");
	        }
	        else {
	        	newEmployee.setSalary(salary);
	        	break;
	        }
	        	
	        }
	       
	        
	        System.out.print("Enter Hire Date (yyyy-MM-dd): ");
	        String inputDate=scanner.nextLine();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        Date hiredate=null;
	 
	        while(hiredate==null) {
	        inputDate=scanner.nextLine();
	        
	        try {
	             hiredate = dateFormat.parse(inputDate);
	            newEmployee.setHireDate(hiredate);
	        } catch (ParseException e) {
	            System.out.println("Invalid date format. Employee creation failed.");
	        }
	       
	      
	        }
	        return newEmployee;
	       
	       
	    }
}
	

