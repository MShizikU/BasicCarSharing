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
@Table(name = "vehicle_model")
@Schema(description = "Модель транспортного средства")
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_model")
    @Schema(description = "Идентификатор модели", example = "1")
    private Integer idModel;

    @Column(name = "model_name")
    @Schema(description = "Название модели", example = "Toyota Camry")
    private String modelName;

    @Column(name = "c_year")
    @Schema(description = "Год выпуска модели", example = "2022")
    private String cyear;
}
