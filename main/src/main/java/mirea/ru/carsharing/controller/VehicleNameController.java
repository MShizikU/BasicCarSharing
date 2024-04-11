package mirea.ru.carsharing.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import mirea.ru.carsharing.model.VehicleName;
import mirea.ru.carsharing.service.VehicleNameService;
import mirea.ru.carsharing.utilities.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vehicle/names")
@Tag(name = "Vehicle Names", description = "API для управления наименованиями транспортных средств")
public class VehicleNameController {
    
    private final VehicleNameService vehicleNameService;

    @Autowired
    public VehicleNameController(VehicleNameService vehicleNameService) {
        this.vehicleNameService = vehicleNameService;
    }

    @Operation(summary = "Создание нового наименования транспортного средства", description = "Создает новое наименование транспортного средства")
    @PostMapping
    public ResponseEntity<ExecutionResult<VehicleName>> create(
            @RequestBody VehicleName vehicleName) {
        ExecutionResult<VehicleName> result = vehicleNameService.create(vehicleName);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Обновление существующего наименования транспортного средства", description = "Обновляет существующее наименование транспортного средства по ID")
    @PutMapping("/{id}")
    public ResponseEntity<ExecutionResult<VehicleName>> updateVehicleName(
            @Parameter(description = "ID наименования транспортного средства для обновления") @PathVariable Integer id,
            @RequestBody VehicleName vehicleName) {
        vehicleName.setIdVehicleName(id);
        ExecutionResult<VehicleName> result = vehicleNameService.updateVehicleName(id, vehicleName);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Удаление наименования транспортного средства по ID", description = "Удаляет наименование транспортного средства по его ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ExecutionResult<Void>> delete(
            @Parameter(description = "ID наименования транспортного средства для удаления") @PathVariable Integer id) {
        ExecutionResult<Void> result = vehicleNameService.delete(id);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Получение наименования транспортного средства по ID", description = "Извлекает наименование транспортного средства по его ID")
    @GetMapping("/{id}")
    public ResponseEntity<ExecutionResult<VehicleName>> getById(
            @Parameter(description = "ID наименования транспортного средства для извлечения") @PathVariable Integer id) {
        ExecutionResult<VehicleName> result = vehicleNameService.getById(id);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Получение всех наименований транспортных средств", description = "Извлекает список всех наименований транспортных средств")
    @GetMapping
    public ResponseEntity<ExecutionResult<List<VehicleName>>> getAll() {
        ExecutionResult<List<VehicleName>> result = vehicleNameService.getAll();
        return ResponseEntity.ok(result);
    }
}
