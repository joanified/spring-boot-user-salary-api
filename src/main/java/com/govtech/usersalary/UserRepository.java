package com.govtech.usersalary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepository extends JpaRepository<User,Integer>{

	  List<User> findBySalaryBetween(Double least, Double max);
}
