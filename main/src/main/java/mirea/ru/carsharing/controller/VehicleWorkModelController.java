package mirea.ru.carsharing.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import mirea.ru.carsharing.model.VehicleWorkModel;
import mirea.ru.carsharing.service.VehicleWorkModelService;
import mirea.ru.carsharing.utilities.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vehicle/work_models")
@Tag(name = "Vehicle Work Models", description = "API для управления моделями работы транспортных средств")
public class VehicleWorkModelController {

    private final VehicleWorkModelService vehicleWorkModelService;

    @Autowired
    public VehicleWorkModelController(VehicleWorkModelService vehicleWorkModelService) {
        this.vehicleWorkModelService = vehicleWorkModelService;
    }

    @Operation(summary = "Создание новой модели работы транспортного средства", description = "Создает новую модель работы транспортного средства")
    @PostMapping
    public ResponseEntity<ExecutionResult<VehicleWorkModel>> createVehicleWorkModel(
            @RequestBody VehicleWorkModel vehicleWorkModel) {
        ExecutionResult<VehicleWorkModel> executionResult = vehicleWorkModelService.createVehicleWorkModel(vehicleWorkModel);
        if (executionResult.getErrorMessage() != null) {
            return ResponseEntity.badRequest().body(executionResult);
        }
        return ResponseEntity.ok(executionResult);
    }

    @Operation(summary = "Обновление существующей модели работы транспортного средства", description = "Обновляет существующую модель работы транспортного средства по ID")
    @PutMapping("/{id}")
    public ResponseEntity<ExecutionResult<VehicleWorkModel>> updateVehicleWorkModel(
            @Parameter(description = "ID модели работы транспортного средства для обновления") @PathVariable Integer id,
            @RequestBody VehicleWorkModel updatedModel) {
        ExecutionResult<VehicleWorkModel> result = vehicleWorkModelService.updateVehicleWorkModel(id, updatedModel);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Получение модели работы транспортного средства по ID", description = "Извлекает модель работы транспортного средства по его ID")
    @GetMapping("/{id}")
    public ResponseEntity<ExecutionResult<VehicleWorkModel>> getVehicleWorkModelById(
            @Parameter(description = "ID модели работы транспортного средства для извлечения") @PathVariable Integer id) {
        ExecutionResult<VehicleWorkModel> executionResult = vehicleWorkModelService.getVehicleWorkModelById(id);
        if (executionResult.getErrorMessage() != null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(executionResult);
    }

    @Operation(summary = "Получение всех моделей работы транспортных средств", description = "Извлекает список всех моделей работы транспортных средств")
    @GetMapping
    public ResponseEntity<ExecutionResult<List<VehicleWorkModel>>> getAllVehicleWorkModels() {
        ExecutionResult<List<VehicleWorkModel>> executionResult = vehicleWorkModelService.getAllVehicleWorkModels();
        return ResponseEntity.ok(executionResult);
    }

    @Operation(summary = "Удаление модели работы транспортного средства по ID", description = "Удаляет модель работы транспортного средства по его ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ExecutionResult<Void>> deleteVehicleWorkModel(
            @Parameter(description = "ID модели работы транспортного средства для удаления") @PathVariable Integer id) {
        ExecutionResult<Void> executionResult = vehicleWorkModelService.deleteVehicleWorkModel(id);
        if (executionResult.getErrorMessage() != null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(executionResult);
    }
}
