package mirea.ru.carsharing.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@AllArgsConstructor
@Data
@Schema(description = "Ответ на запрос регистрации пользователя")
public class RegistrationRedirectDTO {

    @Schema(description = "Имя пользователя", example = "shiz")
    String username;

    @Schema(description = "Пароль пользователя", example = "123321")
    String password;

    @Schema(description = "Роли пользователя", example = "ROLE_USER")
    String roles;
}
