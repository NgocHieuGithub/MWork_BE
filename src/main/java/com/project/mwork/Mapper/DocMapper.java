package com.project.mwork.Mapper;

import org.mapstruct.Mapper;

import com.project.mwork.DTO.Request.DocumentRequest;
import com.project.mwork.DTO.Response.DocResponse;
import com.project.mwork.Model.Document;

@Mapper(componentModel = "spring")
public interface DocMapper {
	Document toDocument(DocumentRequest request);
	DocResponse toDocResponse(Document req);
}
