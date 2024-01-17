package com.example.systemDesign.controller;

import com.example.systemDesign.dto.FileDto;
import com.example.systemDesign.dto.FolderDto;
import com.example.systemDesign.dto.ItemType;
import com.example.systemDesign.dto.PermissionLevel;
import com.example.systemDesign.model.*;
import com.example.systemDesign.service.file.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/spaces")
@RequiredArgsConstructor
public class SpaceController {
    private final IFileService fileService;

    // 1- API to create space:
    @PostMapping
    public ResponseEntity<Item> createSpace() {

        PermissionGroup permissionGroup = new PermissionGroup();
        permissionGroup.setGroupName("admin");
        Permission viewPermission = Permission.builder().userEmail("user1@example.com").permissionLevel(PermissionLevel.VIEW).build();
        Permission editPermission = Permission.builder().userEmail("user2@example.com").permissionLevel(PermissionLevel.EDIT).build();
        Permission addPermission = Permission.builder().userEmail("user4@example.com").permissionLevel(PermissionLevel.ADD).build();
        Permission deletePermission = Permission.builder().userEmail("user5@example.com").permissionLevel(PermissionLevel.DELETE).build();
        permissionGroup.setPermissions(Arrays.asList(viewPermission, editPermission, addPermission, deletePermission));
        return ResponseEntity.status(HttpStatus.CREATED).body(fileService.createSpace("stc-assessments", permissionGroup, ItemType.SPACE));
    }

    // 2- API to create space:
    @PostMapping("/folder/{spaceId}/{name}")
    public ResponseEntity<FolderDto> createFolder(@PathVariable Long spaceId, @PathVariable String name) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fileService.createFolder(spaceId, name));
    }
    // 3- API to create space:
    @PostMapping("/file/{folderId}/{name}")
    public ResponseEntity<FileDto> createFile(@PathVariable Long folderId, @PathVariable String name) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fileService.createFile(folderId, name));
    }

    @PostMapping("/folder/test")
    public ResponseEntity<FolderDto> createFolderTest() {
        return createFolder(1L,"backend");
    }
    @PostMapping("/file/test")
    public ResponseEntity<FileDto> createFileTest() {
        return createFile(1L,"assessments");
    }

    @PostMapping("/fileMetaData/{fileId}")
    public ResponseEntity<FileDto> getFileMetaData(@PathVariable Long fileId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fileService.getFile(fileId));
    }

    @PostMapping("/test/all")
    public ResponseEntity<FileDto> getFileMetaDataTest() {
        createSpace();
        createFolder(1L,"backend");
        createFile(1L,"assessments");
        return ResponseEntity.status(HttpStatus.CREATED).body(fileService.getFile(1L));
    }
    @GetMapping("/files/{fileId}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment().filename("assessment.pdf").build());
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileService.downloadFile(fileId));
    }
}