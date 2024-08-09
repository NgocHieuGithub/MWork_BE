package com.project.mwork.DTO.Response;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProjectResponse {
	String id;
	String name;
	LocalDate dateCreate;
	int type;
	@JsonInclude(value = Include.NON_EMPTY)
	Set<TaskResponse> tasks;	
	@JsonInclude(value = Include.NON_NULL)
	Set<DocResponse> docs;
	@JsonInclude(value = Include.NON_NULL)
	UserResponse owner;
}
