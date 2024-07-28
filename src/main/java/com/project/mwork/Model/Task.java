package com.project.mwork.Model;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	
	String nameTask;
	Date deadLine, finishAt, createAt;
	@JsonIgnore
	@ManyToOne
	Project project;
	
	@ManyToOne
	User user;
	@JsonIgnore
	@OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE)
	Set<Document> docs; 
}
