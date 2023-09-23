package com.ripple.employee.service;
import javax.persistence.*;


import com.ripple.employee.model.Employee;

import java.util.List;

public class EmployeeDAO {
	 private EntityManagerFactory entityManagerFactory;
	    private EntityManager entityManager;

	    public EmployeeDAO() {
	        entityManagerFactory = Persistence.createEntityManagerFactory("EmployeePU");
	        entityManager = entityManagerFactory.createEntityManager();
	    }
	    
	    public void createEmployee(Employee employee) {
	        entityManager.getTransaction().begin();
	        entityManager.persist(employee);
	        entityManager.getTransaction().commit();
	    }
	    public Employee getEmployeeById(int id) {
	        return entityManager.find(Employee.class, id);
	    }
	    public List<Employee> getAllEmployees() {
	        TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employee e", Employee.class);
	        return query.getResultList();
	    }

	    public void updateEmployee(Employee employee) {
	        entityManager.getTransaction().begin();
	        entityManager.merge(employee);
	        entityManager.getTransaction().commit();
	    }
	    
	    public void deleteEmployee(int id) {
	        Employee employee = getEmployeeById(id);
	        if (employee != null) {
	            entityManager.getTransaction().begin();
	            entityManager.remove(employee);
	            entityManager.getTransaction().commit();
	        }
	    }

	    public void close() {
	        entityManager.close();
	        entityManagerFactory.close();
	    }
	}


