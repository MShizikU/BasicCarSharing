package mirea.ru.carsharing.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "rents")
@Schema(description = "Аренда автомобиля")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rent")
    @Schema(description = "Идентификатор аренды", example = "1")
    private Integer idRent;

    @JoinColumn(name = "snpassport")
    @Schema(description = "Серийный номер паспорта", example = "1234567890")
    private Long snpassport;

    @Column(name = "duration")
    @Schema(description = "Продолжительность аренды в часах", example = "3")
    private Integer duration;

    @Column(name = "starting_point")
    @Schema(description = "Точка начала аренды", example = "Москва, ул. Пушкина, д.10")
    private String startingPoint;

    @Column(name = "start_time")
    @Schema(description = "Время начала аренды")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    @Schema(description = "Время окончания аренды")
    private LocalDateTime endTime;

    @JoinColumn(name = "vin")
    @Schema(description = "Идентификатор автомобиля (VIN)", example = "1HGCM82633A001234")
    private String vin;

    @Column(name = "ending_point")
    @Schema(description = "Точка окончания аренды", example = "Москва, ул. Ленина, д.15")
    private String endingPoint;
}
