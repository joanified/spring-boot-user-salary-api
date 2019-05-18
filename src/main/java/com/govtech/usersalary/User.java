package com.govtech.usersalary;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;


@Data
@Entity
@Table(name="user_salary")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5835612349586503559L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="id")
	private Integer id;

	@JsonProperty("name")
	private String name;
	@JsonProperty("salary")
	private double salary;

	User() {}

	User(String name, double salary) {
		this.name = name;
		this.salary = salary;
	} 
}
