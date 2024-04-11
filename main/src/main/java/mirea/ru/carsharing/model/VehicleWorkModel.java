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
@Table(name = "vehicle_work_model")
@Schema(description = "Модель транспортного средства для работы")
public class VehicleWorkModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehicle_work_model")
    @Schema(description = "Идентификатор модели работы транспортного средства", example = "1")
    private Integer idVehicleWorkModel;

    @Column(name = "price_per_hour")
    @Schema(description = "Цена за час использования", example = "100.0")
    private Float pricePerHour;

    @JoinColumn(name = "id_vehicle_name")
    @Schema(description = "Идентификатор наименования транспортного средства", example = "1")
    private Integer idVehicleName;

    @Column(name = "model_photo_name")
    @Schema(description = "Название фотографии модели")
    private String modelPhotoName;

    @Column(name = "id_group")
    @JoinColumn(name = "id_group")
    @Schema(description = "Идентификатор группы транспортного средства", example = "1")
    private Integer idVehicleGroup;
}
