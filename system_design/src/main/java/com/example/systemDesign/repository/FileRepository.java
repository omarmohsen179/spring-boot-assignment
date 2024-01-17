package com.example.systemDesign.repository;

import com.example.systemDesign.dto.FileDto;
import com.example.systemDesign.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    @Query(value = """
SELECT new com.example.systemDesign.dto.FileDto(f.id, f.name, f.binaryData, folder.name) FROM File f
                    JOIN Folder folder ON f.folder = folder
                    JOIN Item item ON folder.item = item
                    JOIN PermissionGroup permission ON  item.permissionGroup = permission
                    JOIN Permission pr ON  permission = pr.group
            WHERE f.id = :fileId AND pr.permissionLevel = 3
            """)
    FileDto findFileByIdAndPermissionGroup(@Param("fileId") Long fileId);
}
