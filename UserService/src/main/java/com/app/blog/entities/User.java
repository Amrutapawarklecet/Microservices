package com.app.blog.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private String userId;
	
	@Column(name="user_name", nullable=false, length=100)
	private String name;
	
	private String email;
	
	private String password;
	
	private String about;
	
	@Transient
	private List<Rating> rating=new ArrayList<>();
	

}
