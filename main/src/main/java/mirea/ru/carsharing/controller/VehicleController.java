package mirea.ru.carsharing.controller;

import mirea.ru.carsharing.model.Vehicle;
import mirea.ru.carsharing.service.VehicleService;
import mirea.ru.carsharing.utilities.ExecutionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/vehicle")
@Tag(name = "Vehicles", description = "API для управления автомобилями")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Operation(summary = "Создать автомобиль", description = "Создает новый автомобиль")
    @PostMapping
    public ResponseEntity<ExecutionResult<Vehicle>> createVehicle(@RequestBody Vehicle vehicle) {
        ExecutionResult<Vehicle> result = vehicleService.createVehicle(vehicle);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Обновить информацию об автомобиле", description = "Обновляет информацию о существующем автомобиле по его VIN")
    @PutMapping("/{vin}")
    public ResponseEntity<ExecutionResult<Vehicle>> updateVehicle(
            @Parameter(description = "VIN автомобиля") @PathVariable String vin,
            @RequestBody Vehicle updatedVehicle) {
        ExecutionResult<Vehicle> result = vehicleService.updateVehicle(vin, updatedVehicle);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Удалить автомобиль", description = "Удаляет автомобиль по его VIN")
    @DeleteMapping("/{vin}")
    public ResponseEntity<ExecutionResult<Void>> deleteVehicle(
            @Parameter(description = "VIN автомобиля") @PathVariable String vin) {
        ExecutionResult<Void> result = vehicleService.deleteVehicle(vin);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Получить информацию об автомобиле по VIN", description = "Получает информацию о автомобиле по его VIN")
    @GetMapping("/{vin}")
    public ResponseEntity<ExecutionResult<Vehicle>> getVehicleById(
            @Parameter(description = "VIN автомобиля") @PathVariable String vin) {
        ExecutionResult<Vehicle> result = vehicleService.getVehicleById(vin);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Получить список всех автомобилей", description = "Получает список всех зарегистрированных автомобилей")
    @GetMapping
    public ResponseEntity<ExecutionResult<List<Vehicle>>> getAllVehicles() {
        ExecutionResult<List<Vehicle>> result = vehicleService.getAllVehicles();
        return ResponseEntity.ok(result);
    }
}
