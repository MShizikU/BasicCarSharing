package mirea.ru.carsharing.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(description = "Результат входа пользователя")
public class LoginResultDTO {

    @Schema(description = "Токен пользователя", example = "jonfvkwjf08l0cvudn...")
    public String jwt;
}
