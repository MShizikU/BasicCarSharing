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
@Table(name = "vehicle_brand")
@Schema(description = "Марка транспортного средства")
public class VehicleBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_brand")
    @Schema(description = "Идентификатор марки", example = "1")
    private Integer idBrand;

    @Column(name = "brand_name")
    @Schema(description = "Название марки", example = "Toyota")
    private String brandName;

    @Column(name = "division")
    @Schema(description = "Подразделение марки", example = "Toyota Motor Corporation")
    private String division;
}
