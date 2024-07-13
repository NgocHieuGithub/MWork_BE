package com.project.mwork.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.mwork.DTO.Response.GroupResponse;
import com.project.mwork.Model.Group;
import java.util.List;


@Repository
public interface GroupRepository extends JpaRepository<Group, String>{
    Group findByCodeGroup(String codeGroup);
    
    @Query(value = "select NEW com.project.mwork.DTO.Response.GroupResponse(g.id, g.name, g.codeGroup, null ) from Group g Join g.users gu "
    		+ "where gu.user.id = :id_user")
    List<GroupResponse> GetListGroupByUser(@Param("id_user") String id_user);
}
