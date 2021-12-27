package com.marlabs.training.cv.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "corona")
public class Corona {
	
	@Id
	private String statename;
	
	private int active;
	
	private int confirmed;
	
    private int deceased;
    
    private int recovered;
    
    private LocalDateTime dateCreated = LocalDateTime.now();
	

}
