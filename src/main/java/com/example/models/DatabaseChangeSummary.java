package com.example.models;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DatabaseChangeSummary implements Serializable {
	 
	private static final long serialVersionUID = -3888459352229102487L;

	String id;
	String author;
	String execType;
}
