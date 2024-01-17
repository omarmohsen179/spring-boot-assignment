package com.example.systemDesign.service.file;

import com.example.systemDesign.dto.FileDto;
import com.example.systemDesign.dto.FolderDto;
import com.example.systemDesign.dto.ItemType;
import com.example.systemDesign.model.File;
import com.example.systemDesign.model.Folder;
import com.example.systemDesign.model.Item;
import com.example.systemDesign.model.PermissionGroup;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface IFileService {

    File uploadFile(Long folderId, String fileName, MultipartFile file) throws IOException;
    Folder getFolder(Long id);
    FileDto createFile(Long folderId, String name);
    Resource downloadFile(Long fileId);
    FileDto getFile(Long id);

    Item getItem(Long id);

    FolderDto createFolder(Long spaceId, String name);

    Item createSpace(String name, PermissionGroup permissionGroup, ItemType itemType);
}
