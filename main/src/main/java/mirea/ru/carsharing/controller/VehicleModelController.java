package mirea.ru.carsharing.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import mirea.ru.carsharing.model.VehicleModel;
import mirea.ru.carsharing.service.VehicleModelService;
import mirea.ru.carsharing.utilities.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vehicle/models")
@Tag(name = "Vehicle Models", description = "API для управления моделями транспортных средств")
public class VehicleModelController {

    private final VehicleModelService vehicleModelService;

    @Autowired
    public VehicleModelController(VehicleModelService vehicleModelService) {
        this.vehicleModelService = vehicleModelService;
    }

    @Operation(summary = "Создание новой модели транспортного средства", description = "Создает новую модель транспортного средства")
    @PostMapping
    public ResponseEntity<ExecutionResult<VehicleModel>> createVehicleModel(
            @RequestBody VehicleModel vehicleModel) {
        ExecutionResult<VehicleModel> result = vehicleModelService.createVehicleModel(vehicleModel);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.badRequest().body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @Operation(summary = "Обновление существующей модели транспортного средства", description = "Обновляет существующую модель транспортного средства по ID")
    @PutMapping("/{id}")
    public ResponseEntity<ExecutionResult<VehicleModel>> updateVehicleModel(
            @Parameter(description = "ID модели транспортного средства для обновления") @PathVariable Integer id,
            @RequestBody VehicleModel vehicleModel) {
        ExecutionResult<VehicleModel> result = vehicleModelService.updateVehicleModel(id, vehicleModel);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.badRequest().body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @Operation(summary = "Получение модели транспортного средства по ID", description = "Извлекает модель транспортного средства по ее ID")
    @GetMapping("/{id}")
    public ResponseEntity<ExecutionResult<VehicleModel>> getVehicleModelById(
            @Parameter(description = "ID модели транспортного средства для извлечения") @PathVariable Integer id) {
        ExecutionResult<VehicleModel> result = vehicleModelService.getVehicleModelById(id);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @Operation(summary = "Получение всех моделей транспортных средств", description = "Извлекает список всех моделей транспортных средств")
    @GetMapping
    public ResponseEntity<ExecutionResult<List<VehicleModel>>> getAllVehicleModels() {
        ExecutionResult<List<VehicleModel>> result = vehicleModelService.getAllVehicleModels();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Удаление модели транспортного средства по ID", description = "Удаляет модель транспортного средства по ее ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ExecutionResult<Void>> deleteVehicleModel(
            @Parameter(description = "ID модели транспортного средства для удаления") @PathVariable Integer id) {
        ExecutionResult<Void> result = vehicleModelService.deleteVehicleModel(id);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }
}
