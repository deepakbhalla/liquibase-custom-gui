package com.example.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseChangeLogId implements Serializable {

	private static final long serialVersionUID = 2140365020067192096L;

	String id;
	String author;
	String filename;
}
