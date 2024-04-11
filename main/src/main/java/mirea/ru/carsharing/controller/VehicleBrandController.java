package mirea.ru.carsharing.controller;

import mirea.ru.carsharing.model.VehicleBrand;
import mirea.ru.carsharing.service.VehicleBrandService;
import mirea.ru.carsharing.utilities.ExecutionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/vehicle/brands")
@Tag(name = "Vehicle Brands", description = "API для управления марками автомобилей")
public class VehicleBrandController {

    private final VehicleBrandService vehicleBrandService;

    public VehicleBrandController(VehicleBrandService vehicleBrandService) {
        this.vehicleBrandService = vehicleBrandService;
    }

    @Operation(summary = "Создать марку автомобиля", description = "Создает новую марку автомобиля")
    @PostMapping
    public ResponseEntity<ExecutionResult<VehicleBrand>> createVehicleBrand(@RequestBody VehicleBrand vehicleBrand) {
        ExecutionResult<VehicleBrand> result = vehicleBrandService.createVehicleBrand(vehicleBrand);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.badRequest().body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @Operation(summary = "Обновить марку автомобиля", description = "Обновляет информацию о существующей марке автомобиля по ее идентификатору")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVehicleBrand(
            @Parameter(description = "Идентификатор марки автомобиля") @PathVariable Integer id,
            @RequestBody VehicleBrand updatedBrand) {
        ExecutionResult<VehicleBrand> executionResult = vehicleBrandService.updateVehicleBrand(id, updatedBrand);
        if (executionResult.getErrorMessage() != null) {
            return ResponseEntity.badRequest().body(executionResult);
        }
        return ResponseEntity.ok(executionResult.getResult());
    }

    @Operation(summary = "Получить марку автомобиля по идентификатору", description = "Получает информацию о марке автомобиля по ее идентификатору")
    @GetMapping("/{id}")
    public ResponseEntity<ExecutionResult<VehicleBrand>> getVehicleBrandById(
            @Parameter(description = "Идентификатор марки автомобиля") @PathVariable Integer id) {
        ExecutionResult<VehicleBrand> result = vehicleBrandService.getVehicleBrandById(id);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @Operation(summary = "Получить все марки автомобилей", description = "Получает список всех марок автомобилей")
    @GetMapping
    public ResponseEntity<ExecutionResult<List<VehicleBrand>>> getAllVehicleBrands() {
        ExecutionResult<List<VehicleBrand>> result = vehicleBrandService.getAllVehicleBrands();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Удалить марку автомобиля", description = "Удаляет марку автомобиля по ее идентификатору")
    @DeleteMapping("/{id}")
    public ResponseEntity<ExecutionResult<Void>> deleteVehicleBrand(
            @Parameter(description = "Идентификатор марки автомобиля") @PathVariable Integer id) {
        ExecutionResult<Void> result = vehicleBrandService.deleteVehicleBrand(id);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }
}
