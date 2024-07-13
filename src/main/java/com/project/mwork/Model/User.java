package com.project.mwork.Model;


import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	
	String name, username, password, email, sdt;
	
	@OneToMany(mappedBy = "user")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	//@JoinColumn(name = "user_id")
	Set<Group_details> groups = new HashSet<>();
	
	@OneToMany(mappedBy = "user")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	Set<Task> task = new HashSet<>();
	
	@OneToMany(mappedBy = "leader")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	Set<Group> GCreated = new HashSet<>();
}
