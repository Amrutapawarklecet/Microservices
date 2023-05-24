package com.app.blog.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user_ratings")
public class Rating {
	
	@Id
	private String ratingId;
	
	private String userId;
	
	private String hotelId;
	
	private Integer rating;
	
	private String fedback;
	
	@Transient
//	private Hotels hotel;
	private Hotels hotel;
}
