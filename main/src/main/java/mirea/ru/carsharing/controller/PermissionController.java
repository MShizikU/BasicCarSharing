package mirea.ru.carsharing.controller;

import mirea.ru.carsharing.model.Permission;
import mirea.ru.carsharing.service.PermissionService;
import mirea.ru.carsharing.utilities.ExecutionResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/permissions")
@Tag(name = "Permissions", description = "API для управления разрешениями доступа")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Operation(summary = "Создать новое разрешение", description = "Создает новое разрешение доступа")
    @PostMapping
    public ResponseEntity<ExecutionResult<Permission>> createPermission(
            @RequestBody Permission permission) {
        ExecutionResult<Permission> result = permissionService.createPermission(permission);
        HttpStatus status = result.getErrorMessage() == null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(result, status);
    }

    @Operation(summary = "Удалить разрешение", description = "Удаляет разрешение доступа по идентификаторам уровня и группы")
    @DeleteMapping
    public ResponseEntity<ExecutionResult<Permission>> deletePermission(
            @RequestBody Permission permission) {
        ExecutionResult<Permission> result = permissionService.deletePermission(
                permission.getIdLevel(), permission.getIdGroup());

        HttpStatus status = result.getErrorMessage() == null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(result, status);
    }

    @Operation(summary = "Получить разрешение", description = "Получает разрешение доступа по идентификаторам уровня и группы")
    @GetMapping
    public ResponseEntity<ExecutionResult<Permission>> getPermission(
            @Parameter(description = "Идентификатор уровня пользователя") @RequestParam("user_level_id") Integer userLevel,
            @Parameter(description = "Идентификатор группы транспортных средств") @RequestParam("vehicle_group_id") Integer vehicleGroup) {
        ExecutionResult<Permission> result = permissionService.getPermission(userLevel, vehicleGroup);
        HttpStatus status = result.getErrorMessage() == null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(result, status);
    }

    @Operation(summary = "Получить все разрешения", description = "Получает список всех разрешений доступа")
    @GetMapping("/all")
    public ResponseEntity<ExecutionResult<List<Permission>>> getAllPermissions() {
        ExecutionResult<List<Permission>> result = permissionService.getAllPermissions();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
