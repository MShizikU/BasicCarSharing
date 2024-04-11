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
@Table(name = "vehicles")
@Schema(description = "Транспортное средство")
public class Vehicle {

    @Id
    @Column(name = "vin")
    @Schema(description = "Идентификатор VIN", example = "1HGCM82633A001234")
    private String vin;

    @Column(name = "color")
    @Schema(description = "Цвет транспортного средства", example = "Черный")
    private String color;

    @Column(name = "state")
    @Schema(description = "Состояние транспортного средства", example = "В наличии")
    private String state;

    @Column(name = "place")
    @Schema(description = "Местоположение транспортного средства", example = "Москва, ул. Ленина, д.15")
    private String place;

    @JoinColumn(name = "id_vehicle_work_model")
    @Schema(description = "Идентификатор модели транспортного средства")
    private Integer idVehicleWorkModel;
}
