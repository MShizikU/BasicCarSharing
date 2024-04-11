package mirea.ru.carsharing.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Запрос входа пользователя")
public class LoginDTO {

    @Schema(description = "Имя пользователя", example = "Shiz")
    private String username;

    @Schema(description = "Пароль пользователя", example = "123321")
    private String password;
}