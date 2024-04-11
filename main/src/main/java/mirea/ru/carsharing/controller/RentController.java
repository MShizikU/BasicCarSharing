package mirea.ru.carsharing.controller;

import mirea.ru.carsharing.model.Rent;
import mirea.ru.carsharing.service.DefaultEmailService;
import mirea.ru.carsharing.service.RentService;
import mirea.ru.carsharing.utilities.ExecutionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/rents")
@Tag(name = "Rents", description = "API для управления арендой автомобилей")
public class RentController {

    private final DefaultEmailService emailService;
    private final RentService rentService;

    public RentController(DefaultEmailService emailService, RentService rentService) {
        this.emailService = emailService;
        this.rentService = rentService;
    }

    @Operation(summary = "Начать аренду", description = "Начинает аренду автомобиля")
    @PostMapping("/start")
    public ResponseEntity<ExecutionResult<Rent>> startRent(@RequestBody Rent rent) {
        ExecutionResult<Rent> result = rentService.startRent(rent);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Закрыть аренду", description = "Закрывает аренду автомобиля по идентификатору")
    @PostMapping("/close/{id}")
    public ResponseEntity<ExecutionResult<Rent>> closeRent(
            @Parameter(description = "Идентификатор аренды") @PathVariable Integer id,
            @RequestBody Rent rent) {
        ExecutionResult<Rent> result = rentService.closeRent(id, rent);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.badRequest().body(result);
        }
        emailService.sendEmail("sidorovstasdw@gmail.com", "TestTOT11", "Testing from Java");
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Создать аренду", description = "Создает новую аренду автомобиля")
    @PostMapping
    public ResponseEntity<ExecutionResult<Rent>> createRent(@RequestBody Rent rent) {
        ExecutionResult<Rent> result = rentService.createRent(rent);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Обновить аренду", description = "Обновляет информацию об аренде по идентификатору")
    @PutMapping("/{rentId}")
    public ResponseEntity<ExecutionResult<Rent>> updateRent(
            @Parameter(description = "Идентификатор аренды") @PathVariable Integer rentId,
            @RequestBody Rent rent) {
        ExecutionResult<Rent> result = rentService.updateRent(rentId, rent);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Удалить аренду", description = "Удаляет аренду автомобиля по идентификатору")
    @DeleteMapping("/{rentId}")
    public ResponseEntity<Void> deleteRent(
            @Parameter(description = "Идентификатор аренды") @PathVariable Integer rentId) {
        ExecutionResult<Rent> result = rentService.deleteRent(rentId);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получить все аренды", description = "Получает список всех аренд автомобилей")
    @GetMapping
    public ResponseEntity<ExecutionResult<List<Rent>>> getAllRents() {
        return ResponseEntity.ok(rentService.getAllRents());
    }

    @Operation(summary = "Получить аренду по идентификатору", description = "Получает информацию об аренде по идентификатору")
    @GetMapping("/{rentId}")
    public ResponseEntity<ExecutionResult<Rent>> getRentById(
            @Parameter(description = "Идентификатор аренды") @PathVariable Integer rentId) {
        ExecutionResult<Rent> result = rentService.getRentById(rentId);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Получить аренды по паспортным данным", description = "Получает список аренд по паспортным данным")
    @GetMapping(params = "snpassport")
    public ResponseEntity<ExecutionResult<List<Rent>>> getRentBySnpassport(
            @Parameter(description = "Паспортные данные арендатора") @RequestParam Long snpassport) {
        ExecutionResult<List<Rent>> result = rentService.getRentBySnpassport(snpassport);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Получить аренды по VIN", description = "Получает список аренд по VIN автомобиля")
    @GetMapping(params = "vin")
    public ResponseEntity<ExecutionResult<List<Rent>>> getRentByVin(
            @Parameter(description = "VIN автомобиля") @RequestParam String vin) {
        ExecutionResult<List<Rent>> result = rentService.getRentByVin(vin);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
