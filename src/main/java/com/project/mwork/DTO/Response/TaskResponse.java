package com.project.mwork.DTO.Response;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskResponse {
	String id;
	String nameTask;
	LocalDate deadLine, finishAt, createAt;
	@JsonInclude(value = Include.NON_EMPTY)
	UserResponse user;
}
