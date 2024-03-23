package com.batchexample.entity;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private int age;

	@Column(nullable = false, unique = true)
	private String mobileNumber;

	private BigDecimal beforeValue;

	private BigDecimal afterValue;

	public Employee() {
	}

	public Employee(String name, int age, String mobileNumber, BigDecimal beforeValue, BigDecimal afterValue) {
		this.name = name;
		this.age = age;
		this.mobileNumber = mobileNumber;
		this.beforeValue = beforeValue;
		this.afterValue = afterValue;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public BigDecimal getBeforeValue() {
		return beforeValue;
	}

	public BigDecimal getAfterValue() {
		return afterValue;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setBeforeValue(BigDecimal beforeValue) {
		this.beforeValue = beforeValue;
	}

	public void setAfterValue(BigDecimal afterValue) {
		this.afterValue = afterValue;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", mobileNumber=" + mobileNumber
				+ ", beforeValue=" + beforeValue + ", afterValue=" + afterValue + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(afterValue, age, beforeValue, id, mobileNumber, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(afterValue, other.afterValue) && age == other.age
				&& Objects.equals(beforeValue, other.beforeValue) && Objects.equals(id, other.id)
				&& Objects.equals(mobileNumber, other.mobileNumber) && Objects.equals(name, other.name);
	}

}
