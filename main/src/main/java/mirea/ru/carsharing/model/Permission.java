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
@Table(name = "permissions")
@Schema(description = "Разрешение на доступ")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор разрешения")
    private Long id_permission;

    @JoinColumn(name = "id_level", referencedColumnName = "id_level")
    @Schema(description = "Идентификатор уровня доступа")
    private Integer idLevel;

    @JoinColumn(name = "id_group", referencedColumnName = "id_group")
    @Schema(description = "Идентификатор группы")
    private Integer idGroup;

}
