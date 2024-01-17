package com.example.systemDesign.repository;

import com.example.systemDesign.model.File;
import com.example.systemDesign.model.Permission;
import com.example.systemDesign.model.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, Long> {
    Optional<PermissionGroup> findPermissionGroupByGroupName(String groupName);
}
