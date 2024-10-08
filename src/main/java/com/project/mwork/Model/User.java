package com.project.mwork.Model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	String name, username, password, email, sdt, urlavt;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	Set<Group_details> groups;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	Set<Task> task;
	
	@JsonIgnore
	@OneToMany(mappedBy = "leader")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	Set<Group> GCreated;
	
	@JsonIgnore
	@OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
	List<Post> post;
}
