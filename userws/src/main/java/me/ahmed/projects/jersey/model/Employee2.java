package me.ahmed.projects.jersey.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE2")
public class Employee2 {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EMPLOYEE_ID")
	private Long employeeId;

	@Column(name = "EMPLOYEE_NAME")
	private String employeeName;

	public Employee2() {
	}

	public Employee2(String employeeName) {
		this.employeeName = employeeName;
	}

	public Long getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return this.employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + this.employeeId + ", employeeName="
				+ this.employeeName + "]";
	}
}
