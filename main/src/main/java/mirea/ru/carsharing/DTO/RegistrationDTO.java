package mirea.ru.carsharing.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос регистрации пользователя")
public class RegistrationDTO {

    @Schema(description = "Серия номер паспорта пользователя", example = "1718598305")
    private Long snpassport;

    @Schema(description = "Полное имя пользователя", example = "Sidorov Stanislav Dmitrievich")
    private String fullName;

    @Schema(description = "Имя пользователя", example = "Shiz")
    private String username;

    @Schema(description = "Дата рождения пользователя", example = "2004-06-03")
    private LocalDate dateOfBirth;

    @Schema(description = "Пароль пользователя", example = "123321")
    private String password;
}
