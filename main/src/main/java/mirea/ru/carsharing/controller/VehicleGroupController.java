package mirea.ru.carsharing.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import mirea.ru.carsharing.model.VehicleGroup;
import mirea.ru.carsharing.service.VehicleGroupService;
import mirea.ru.carsharing.utilities.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/vehicle/groups")
@Tag(name = "Vehicle Groups", description = "API для управления группами автомобилей")
public class VehicleGroupController {

    private final VehicleGroupService vehicleGroupService;

    @Autowired
    public VehicleGroupController(VehicleGroupService vehicleGroupService) {
        this.vehicleGroupService = vehicleGroupService;
    }

    @Operation(summary = "Создать группу автомобилей", description = "Создает новую группу автомобилей")
    @PostMapping
    public ResponseEntity<ExecutionResult<VehicleGroup>> createVehicleGroup(@RequestBody VehicleGroup vehicleGroup) {
        ExecutionResult<VehicleGroup> result = vehicleGroupService.createVehicleGroup(vehicleGroup);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.badRequest().body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @Operation(summary = "Обновить информацию о группе автомобилей", description = "Обновляет информацию о существующей группе автомобилей по ее ID")
    @PutMapping("/{id}")
    public ResponseEntity<ExecutionResult<VehicleGroup>> updateVehicleGroup(
            @Parameter(description = "ID группы автомобилей") @PathVariable("id") Integer id,
            @RequestBody VehicleGroup vehicleGroup) {
        ExecutionResult<VehicleGroup> result = vehicleGroupService.updateVehicleGroup(id, vehicleGroup);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.badRequest().body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @Operation(summary = "Удалить группу автомобилей", description = "Удаляет группу автомобилей по ее ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ExecutionResult<Void>> deleteVehicleGroup(
            @Parameter(description = "ID группы автомобилей") @PathVariable("id") Integer id) {
        ExecutionResult<Void> result = vehicleGroupService.deleteVehicleGroup(id);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @Operation(summary = "Получить информацию о группе автомобилей по ID", description = "Получает информацию о группе автомобилей по ее ID")
    @GetMapping("/{id}")
    public ResponseEntity<ExecutionResult<VehicleGroup>> getVehicleGroupById(
            @Parameter(description = "ID группы автомобилей") @PathVariable("id") Integer id) {
        ExecutionResult<VehicleGroup> result = vehicleGroupService.getVehicleGroupById(id);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @Operation(summary = "Получить список всех групп автомобилей", description = "Получает список всех зарегистрированных групп автомобилей")
    @GetMapping
    public ResponseEntity<ExecutionResult<List<VehicleGroup>>> getAllVehicleGroups() {
        ExecutionResult<List<VehicleGroup>> result = vehicleGroupService.getAllVehicleGroups();
        if (result.getErrorMessage() != null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }
}
