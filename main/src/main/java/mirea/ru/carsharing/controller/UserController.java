package mirea.ru.carsharing.controller;

import mirea.ru.carsharing.DTO.LoginDTO;
import mirea.ru.carsharing.DTO.LoginResultDTO;
import mirea.ru.carsharing.DTO.RegistrationDTO;
import mirea.ru.carsharing.model.User;
import mirea.ru.carsharing.service.UserLevelService;
import mirea.ru.carsharing.service.UserService;
import mirea.ru.carsharing.utilities.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "API для управления пользователями")
public class UserController {

    private final UserService userService;
    private final UserLevelService userLevelService;

    @Autowired
    public UserController(UserService userService, UserLevelService userLevelService) {
        this.userService = userService;
        this.userLevelService = userLevelService;
    }

    @Operation(summary = "Создать пользователя", description = "Создает нового пользователя")
    @PostMapping
    public ResponseEntity<ExecutionResult<User>> createUser(@RequestBody User user) {
        ExecutionResult<User> result = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Operation(summary = "Обновить пользователя", description = "Обновляет информацию о пользователе по его паспортным данным")
    @PutMapping("/{snpassport}")
    public ResponseEntity<ExecutionResult<User>> updateUser(
            @Parameter(description = "Паспортные данные пользователя") @PathVariable Long snpassport,
            @RequestBody User updatedUser) {
        ExecutionResult<User> result = userService.updateUser(snpassport, updatedUser);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Удалить пользователя", description = "Удаляет пользователя по его паспортным данным")
    @DeleteMapping("/{snpassport}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "Паспортные данные пользователя") @PathVariable Long snpassport) {
        userService.deleteUser(snpassport);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получить пользователя по паспортным данным", description = "Получает информацию о пользователе по его паспортным данным")
    @GetMapping("/{snpassport}")
    public ResponseEntity<ExecutionResult<User>> getUserBySnpassport(
            @Parameter(description = "Паспортные данные пользователя") @PathVariable Long snpassport) {
        ExecutionResult<User> result = userService.getUserBySnpassport(snpassport);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Получить пользователей по уровню доступа", description = "Получает список пользователей по идентификатору уровня доступа")
    @GetMapping("/userLevel/{idLevel}")
    public ResponseEntity<ExecutionResult<List<User>>> getUsersByUserLevel(
            @Parameter(description = "Идентификатор уровня доступа") @PathVariable Integer idLevel) {
        ExecutionResult<List<User>> users = userService.getUsersByUserLevel(idLevel);
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Получить пользователя по имени пользователя", description = "Получает информацию о пользователе по его имени пользователя")
    @GetMapping("/username/{username}")
    public ResponseEntity<ExecutionResult<User>> getUserByUsername(
            @Parameter(description = "Имя пользователя") @PathVariable String username) {
        ExecutionResult<User> result = userService.getUserByUsername(username);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }
}
