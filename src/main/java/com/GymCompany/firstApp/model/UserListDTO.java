package com.GymCompany.firstApp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

//ULID	NUMBER	No		1	
//USERID	VARCHAR2(50 BYTE)	No		2	
//USERPW	VARCHAR2(100 BYTE)	No		3	
//USERNAME	VARCHAR2(100 BYTE)	No		4	
//JOIN_DATE	DATE	Yes	sysdate	5	
//LASTLOGINTIME	TIMESTAMP(6)	Yes		6	
@Entity
@Table(name = "USERLIST")
public class UserListDTO {   // (db 유저리스트 객체)
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userListSeqGen")
    @SequenceGenerator(name = "userListSeqGen", sequenceName = "user_list_seq", allocationSize = 1)
    @Column(name = "ULID")
    private int ulid;
    
    @Column(name = "USERID", unique = true)
    @NotNull
    @Size(max = 50)
    private String userId;
    
    @Column(name = "USERPW")
    @NotNull
    @Size(max = 100)
    private String userPw;
    
    @Column(name = "USERNAME")
    @NotNull
    @Size(max = 100)
    private String userName;
    
    @Column(name = "join_date")
    private LocalDate joinDate;
    
    @Column(name = "Last_Login_Time")
    private LocalDateTime lastLoginTime;

    public UserListDTO() {}
    
	public UserListDTO(int ulid, String userId, String userPw, String userName, LocalDate joinDate,
			LocalDateTime lastLoginTime) {
		super();
		this.ulid = ulid;
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.joinDate = joinDate;
		this.lastLoginTime = lastLoginTime;
	}
	

	public int getUlid() {
		return ulid;
	}
	public void setUlid(int ulid) {
		this.ulid = ulid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public LocalDate getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}
	public LocalDateTime getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(LocalDateTime lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}


	@Override
	public String toString() {
		return "UserListDTO [ulid=" + ulid + ", userId=" + userId + ", userPw=" + userPw + ", userName=" + userName
				+ ", joinDate=" + joinDate + ", lastLoginTime=" + lastLoginTime + "]";
	}
    
	

}