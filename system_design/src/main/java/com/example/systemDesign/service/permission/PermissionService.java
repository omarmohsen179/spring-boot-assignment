package com.example.systemDesign.service.permission;

import com.example.systemDesign.dto.PermissionLevel;
import com.example.systemDesign.exception.UnAuthorizedException;
import com.example.systemDesign.model.PermissionGroup;
import com.example.systemDesign.repository.PermissionGroupRepository;
import com.example.systemDesign.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PermissionService implements IPermissionService {

    private final PermissionGroupRepository permissionGroupRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public void assignGroupPermissions(PermissionGroup permissionGroup) {
        checkPermission(permissionGroup, PermissionLevel.EDIT);
        permissionGroup = permissionGroupRepository.save(permissionGroup);
        var group = permissionGroup;
        permissionGroup.setPermissions(permissionGroup.getPermissions().stream().map(e -> {
            e.setGroup(PermissionGroup.builder().id(group.getId()).build());
            return e;
        }).toList());
        permissionRepository.saveAll(permissionGroup.getPermissions());
    }

    @Override
    public void checkPermission(PermissionGroup permissionGroup, PermissionLevel requiredPermissionLevel) {
        if (permissionGroup.getPermissions().stream().filter(e -> e.getPermissionLevel() == requiredPermissionLevel).toList().isEmpty()) {
            throw new UnAuthorizedException("UnAuthorized");
        }
    }
}
