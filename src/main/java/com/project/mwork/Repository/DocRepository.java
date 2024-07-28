package com.project.mwork.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.mwork.Model.Document;

public interface DocRepository extends JpaRepository<Document, String>{
	@Query("select d.name, d.url from Document d where d.project.group.id = :id_group")
	List<Document> getDocuments(@Param(value = "id_group") String id_group);
}
