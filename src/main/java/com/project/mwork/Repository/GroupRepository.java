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
    
    @Query(value = "select gd.group from Group_details gd where gd.user.id = :id_user")
    List<Group> getGroups(@Param("id_user") String id_user);
}
