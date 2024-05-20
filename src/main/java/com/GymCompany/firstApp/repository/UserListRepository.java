package com.GymCompany.firstApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.GymCompany.firstApp.model.UserListDTO;


@Repository
public interface UserListRepository extends JpaRepository<UserListDTO, Integer> { 
	
	
	  
	 @Query("SELECT u.userPw FROM UserListDTO u WHERE u.userId = :userId")
	 public String findPasswordByUserId(@Param("userId") String userId);

	 UserListDTO findByUserId(String userId); // not used yet
}
