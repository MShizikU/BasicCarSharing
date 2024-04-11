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
@Table(name = "vehicle_name")
@Schema(description = "Наименование транспортного средства")
public class VehicleName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehicle_name")
    @Schema(description = "Идентификатор наименования", example = "1")
    private Integer idVehicleName;

    @JoinColumn(name = "id_model")
    @Schema(description = "Идентификатор модели транспортного средства", example = "1")
    private Integer idModel;

    @JoinColumn(name = "id_brand")
    @Schema(description = "Идентификатор марки транспортного средства", example = "1")
    private Integer idBrand;
}
