package com.example.systemDesign.service.file;

import com.example.systemDesign.dto.FileDto;
import com.example.systemDesign.dto.FolderDto;
import com.example.systemDesign.dto.ItemType;
import com.example.systemDesign.dto.PermissionLevel;
import com.example.systemDesign.exception.NotFoundException;
import com.example.systemDesign.model.File;
import com.example.systemDesign.model.Folder;
import com.example.systemDesign.model.Item;
import com.example.systemDesign.model.PermissionGroup;
import com.example.systemDesign.repository.*;
import com.example.systemDesign.service.permission.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService implements IFileService {

    private final FileRepository fileRepository;

    private final FolderRepository folderRepository;
    private final PermissionGroupRepository permissionGroupRepository;

    private final PermissionRepository permissionRepository;

    private final ItemRepository itemRepository;

    private final PermissionService permissionService;

    @Override
    public File uploadFile(Long folderId, String fileName, MultipartFile file) throws IOException {

        Folder folder = getFolder(folderId);
        PermissionGroup permissionGroup = folder.getItem().getPermissionGroup();
        permissionService.checkPermission(permissionGroup, PermissionLevel.EDIT);

        File newFile = new File();
        newFile.setName(fileName);
        newFile.setFolder(folder);
        newFile.setBinaryData(file.getBytes());

        return fileRepository.save(newFile);
    }

    public Folder getFolder(Long id) {
        return folderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Folder not found with ID: " + id));
    }

    @Override
    public FileDto getFile(Long id) {

        var data = fileRepository.findFileByIdAndPermissionGroup(id);
        return data;
    }

    @Override
    public Item getItem(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item not found with ID: " + id));
    }

    @Override
    public FolderDto createFolder(Long spaceId, String name) {
        Item space = getItem(spaceId);
        permissionService.checkPermission(space.getPermissionGroup(), PermissionLevel.EDIT);
        Folder newFolder = folderRepository.save(Folder.builder().name(name).item(space).build());
        return FolderDto.builder().id(newFolder.getId()).item(space.getName()).name(newFolder.getName()).build();
    }

    public FileDto createFile(Long folderId, String name) {
        Folder folder = getFolder(folderId);
        File newFile = fileRepository.save(File.builder().name(name).folder(folder).build());
        return FileDto.builder().binaryData(newFile.getBinaryData()).id(newFile.getId()).name(newFile.getName()).folderName(newFile.getFolder().getName()).build();
    }

    @Override
    public Resource downloadFile(Long fileId) {
        FileDto file = getFile(fileId);

        byte[] fileData = file.getBinaryData();
        String fileName = file.getName();
        return new ByteArrayResource(fileData) {
            @Override
            public String getFilename() {
                return fileName;
            }
        };
    }

    @Override
    public Item createSpace(String name, PermissionGroup permissionGroup, ItemType itemType) {
        permissionService.assignGroupPermissions(permissionGroup);
        return itemRepository.save(Item.builder().name(name).permissionGroup(permissionGroup).type(itemType).build());
    }

}
