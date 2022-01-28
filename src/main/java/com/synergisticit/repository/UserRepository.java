package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synergisticit.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUserName(String userName);
	public User findByCustomerMobile(String customerMobile);
}