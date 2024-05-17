package com.GymCompany.firstApp.model;


import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class Authority {  //권한 종류 담아놓는 db 용 객체)
		
		
	 	@Id
	    @Column(name = "authority_name", length = 50)
	    private String authorityName;

		public String getAuthorityName() {
			return authorityName;
		}

		public void setAuthorityName(String authorityName) {
			this.authorityName = authorityName;
		}	
	 	
	 	public Authority() {
			
		}

		public Authority(String authorityName) {
			super();
			this.authorityName = authorityName;
		}
	 	
	 	
}
