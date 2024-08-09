package com.project.mwork.Repository.HttpClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.mwork.DTO.Request.FTaskRequest;
import com.project.mwork.DTO.Response.ProjectResponse;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient(value = "Google-API-Integration", url = "${app.services.googlesheetapi}")
public interface GoogleSheetClient {
	@GetMapping(value = "check", produces = MediaType.APPLICATION_JSON_VALUE)
	String GetClient();
	
	@PostMapping(value = "createSheet", produces = MediaType.APPLICATION_JSON_VALUE)
	String CreateGoogleSheetClient(@RequestBody ProjectResponse req);
	
	@PostMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Boolean> UpdateGoogleSheetClient(@RequestBody FTaskRequest req);
}
