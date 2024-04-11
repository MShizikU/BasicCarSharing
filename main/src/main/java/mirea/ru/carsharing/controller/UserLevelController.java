package mirea.ru.carsharing.controller;

import mirea.ru.carsharing.model.UserLevel;
import mirea.ru.carsharing.service.UserLevelService;
import mirea.ru.carsharing.utilities.ExecutionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/user/levels")
@Tag(name = "User Levels", description = "API для управления уровнями пользователей")
public class UserLevelController {
    
    private final UserLevelService userLevelService;

    public UserLevelController(UserLevelService userLevelService) {
        this.userLevelService = userLevelService;
    }

    @Operation(summary = "Создать уровень пользователя", description = "Создает новый уровень доступа для пользователя")
    @PostMapping
    public ResponseEntity<ExecutionResult<UserLevel>> createUserLevel(@RequestBody UserLevel userLevel) {
        ExecutionResult<UserLevel> executionResult = userLevelService.createUserLevel(userLevel);
        if (executionResult.getErrorMessage() != null) {
            return ResponseEntity.badRequest().body(executionResult);
        }

        return ResponseEntity.ok(executionResult);
    }

    @Operation(summary = "Обновить уровень пользователя", description = "Обновляет информацию о существующем уровне пользователя по его идентификатору")
    @PutMapping("/{id}")
    public ResponseEntity<ExecutionResult<UserLevel>> updateUserLevel(
            @Parameter(description = "Идентификатор уровня пользователя") @PathVariable Integer id,
            @RequestBody UserLevel userLevel) {
        ExecutionResult<UserLevel> executionResult = userLevelService.updateUserLevel(id, userLevel);
        if (executionResult.getErrorMessage() != null) {
            return ResponseEntity.badRequest().body(executionResult);
        }

        return ResponseEntity.ok(executionResult);
    }

    @Operation(summary = "Удалить уровень пользователя", description = "Удаляет уровень пользователя по его идентификатору")
    @DeleteMapping("/{id}")
    public ResponseEntity<ExecutionResult<UserLevel>> deleteUserLevel(
            @Parameter(description = "Идентификатор уровня пользователя") @PathVariable Integer id) {
        ExecutionResult<UserLevel> executionResult = userLevelService.deleteUserLevel(id);
        if (executionResult.getErrorMessage() != null) {
            return ResponseEntity.badRequest().body(executionResult);
        }

        return ResponseEntity.ok(executionResult);
    }

    @Operation(summary = "Получить уровень пользователя по идентификатору", description = "Получает информацию о уровне пользователя по его идентификатору")
    @GetMapping("/{id}")
    public ResponseEntity<ExecutionResult<UserLevel>> getUserLevelById(
            @Parameter(description = "Идентификатор уровня пользователя") @PathVariable Integer id) {
        ExecutionResult<UserLevel> executionResult = userLevelService.getUserLevelById(id);
        if (executionResult.getErrorMessage() != null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(executionResult);
    }

    @Operation(summary = "Получить все уровни пользователей", description = "Получает список всех уровней пользователей")
    @GetMapping
    public ResponseEntity<ExecutionResult<List<UserLevel>>> getAllUserLevels() {
        ExecutionResult<List<UserLevel>> executionResult = userLevelService.getAllUserLevels();
        return ResponseEntity.ok(executionResult);
    }
}
