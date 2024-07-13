package com.project.mwork.Model;


import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "group_work")
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	
	String name;
	
	@Column(name  =  "code_group")
	String codeGroup;
	
	@OneToMany(mappedBy = "group")
	Set<Group_details> users;
	
	@OneToMany(mappedBy = "group")
	Set<Project> projects;
	
	@ManyToOne
	@JsonInclude(value = Include.NON_NULL)
	User leader;
}
