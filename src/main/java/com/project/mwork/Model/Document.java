package com.project.mwork.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Document {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	
	String name, url;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	Post project;
	@JsonIgnore
	@ManyToOne
	Task task;
}
