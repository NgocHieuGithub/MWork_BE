package com.project.mwork.DTO.Request;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CProjectRequest {
	String name;
	LocalDate dateCreate;
	String id_group;
	int type;
	@JsonInclude(value = Include.NON_NULL)
	List<CTaskRequest> taskRequests;
	@JsonInclude(value = Include.NON_NULL)
	List<DocumentRequest> docs;
}
