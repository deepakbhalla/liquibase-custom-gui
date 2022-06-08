package com.example.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DATABASECHANGELOG")
@IdClass(DatabaseChangeLogId.class)
public class DatabaseChangeLog {

	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "AUTHOR")
	private String author;
	
	@Column(name = "FILENAME")
	private String filename;
	
	@Column(name = "DATEEXECUTED")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateexecuted;
	
	@Column(name = "ORDEREXECUTED")
	private String orderexecuted;
	
	@Column(name = "EXECTYPE")
	private String exectype;
	
	@Column(name = "MD5SUM")
	private String md5sum;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "COMMENTS")
	private String comments;
	
	@Column(name = "TAG")
	private String tag;
	
	@Column(name = "LIQUIBASE")
	private String liquibase;
	
	@Column(name = "CONTEXTS")
	private String contexts;
	
	@Column(name = "LABELS")
	private String labels;
	
	@Column(name = "DEPLOYMENT_ID")
	private String deploymentId;
}
