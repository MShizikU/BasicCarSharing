package mirea.ru.carsharing.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
@Schema(description = "Пользователь")
public class User {

    @Id
    @Column(name = "snpassport")
    @Schema(description = "Серийный номер паспорта", example = "1234567890")
    private Long snpassport;

    @Column(name = "full_name")
    @Schema(description = "Полное имя пользователя", example = "Иванов Иван Иванович")
    private String fullName;

    @Column(name = "username")
    @Schema(description = "Имя пользователя (логин)", example = "ivanov_ii")
    private String username;

    @Column(name = "date_of_birth")
    @Schema(description = "Дата рождения", example = "1990-01-01")
    private LocalDate dateOfBirth;

    @JoinColumn(name = "id_level", referencedColumnName = "id_level")
    @Schema(description = "Идентификатор уровня доступа", example = "1")
    private Integer idLevel;
}
