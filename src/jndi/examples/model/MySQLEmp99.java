package jndi.examples.model;

public class MySQLEmp99 {
	private String id, salary, name, designation;

	public MySQLEmp99() {
		super();
	}

	public MySQLEmp99(String id, String salary, String name, String designation) {
		super();
		this.id = id;
		this.salary = salary;
		this.name = name;
		this.designation = designation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String toString() {
		return "{ ID: " + id + "\tSalary: " + salary + "\tName: " + name + "\tDesignation: " + designation + " }";
	}
}
