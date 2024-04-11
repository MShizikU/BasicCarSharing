package mirea.ru.carsharing.controller;

import mirea.ru.carsharing.DTO.LoginDTO;
import mirea.ru.carsharing.DTO.LoginResultDTO;
import mirea.ru.carsharing.DTO.RegistrationDTO;
import mirea.ru.carsharing.service.UserLevelService;
import mirea.ru.carsharing.service.UserService;
import mirea.ru.carsharing.utilities.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("")
public class AuthController {
    private final UserService userService;
    private final UserLevelService userLevelService;

    @Autowired
    public AuthController(UserService userService, UserLevelService userLevelService) {
        this.userService = userService;
        this.userLevelService = userLevelService;
    }

    @Operation(hidden = true)
    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        ModelAndView modelAndView = new ModelAndView("login_page");
        return modelAndView;
    }

    @Operation(hidden = true)
    @GetMapping("/register")
    public ModelAndView showRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView("register_page");
        return modelAndView;
    }

    @Operation(summary = "Исполнение входа пользователя", description = "Выполняет аутентификацию пользователя по данным логина и пароля")
    @PostMapping("/login")
    public ResponseEntity<ExecutionResult<LoginResultDTO>> performLogin(@RequestBody LoginDTO loginDTO){
        ExecutionResult<LoginResultDTO> result = userService.performLogin(loginDTO);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Регистрация нового пользователя", description = "Регистрирует нового пользователя в системе")
    @PostMapping("/register")
    public ResponseEntity<ExecutionResult<String>> performRegistration(@RequestBody RegistrationDTO regDTO){
        ExecutionResult<String> result = userService.performRegistration(regDTO);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }
}
