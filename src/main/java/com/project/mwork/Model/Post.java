package com.project.mwork.Model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	
	String name;
	String url;
	LocalDate dateCreate;
	int type;
	@JsonIgnore
	@OneToMany(mappedBy = "project")
	List<Task> tasks;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	Group group;
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
	List<Document> docs;
	
	@ManyToOne()
	User owner;
	
	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", dateCreate=" + dateCreate + ", tasks=" + tasks + ", group="
				+ group + "]";
	}
	
}
