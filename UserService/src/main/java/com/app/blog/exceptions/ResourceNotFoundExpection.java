package com.app.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundExpection extends RuntimeException{
	
	String resourceName;
	String fieldName;
	String fieldValue;
	
	public ResourceNotFoundExpection(String resourceName, String fieldName, String fieldValue) {
		super(String.format("%s is not found with %s:%s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
}
