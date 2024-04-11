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
@Table(name = "vehicle_group")
@Schema(description = "Группа транспортных средств")
public class VehicleGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group")
    @Schema(description = "Идентификатор группы", example = "1")
    private Integer idGroup;

    @Column(name = "group_name")
    @Schema(description = "Название группы", example = "Седаны")
    private String groupName;

    @Column(name = "group_description")
    @Schema(description = "Описание группы", example = "Автомобили класса седан")
    private String groupDescription;

    @Column(name = "count_vehicles")
    @Schema(description = "Количество транспортных средств в группе", example = "50")
    private Integer countVehicles;
}
