package com.project.mwork.Model;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Entity;
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
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	
	String name;
	Date dateCreate;
	
	@OneToMany(mappedBy = "project")
	Set<Task> tasks;
	
	@ManyToOne
	Group group;

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", dateCreate=" + dateCreate + ", tasks=" + tasks + ", group="
				+ group + "]";
	}
	
}
