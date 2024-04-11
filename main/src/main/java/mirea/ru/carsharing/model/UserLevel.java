package mirea.ru.carsharing.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_level")
@Schema(description = "Уровень пользователя")
public class UserLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_level")
    @Schema(description = "Идентификатор уровня", example = "1")
    private Integer idLevel;

    @Column(name = "level_name")
    @Schema(description = "Название уровня", example = "Basic")
    private String levelName;

    @Column(name = "level_description")
    @Schema(description = "Описание уровня", example = "Базовый уровень доступа")
    private String levelDescription;

    @Column(name = "count_users")
    @Schema(description = "Количество пользователей с этим уровнем", example = "100")
    private Integer countUsers;
}
