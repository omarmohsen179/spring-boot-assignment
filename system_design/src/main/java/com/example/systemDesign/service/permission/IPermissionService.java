package com.example.systemDesign.service.permission;

import com.example.systemDesign.dto.PermissionLevel;
import com.example.systemDesign.model.PermissionGroup;

import java.util.List;

public interface IPermissionService {
    void assignGroupPermissions(PermissionGroup permissionGroup);
    void checkPermission(PermissionGroup permissionGroup, PermissionLevel requiredPermissionLevel);
}
