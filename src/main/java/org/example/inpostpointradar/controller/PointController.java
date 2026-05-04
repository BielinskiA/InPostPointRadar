package org.example.inpostpointradar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.inpostpointradar.domain.Point;
import org.example.inpostpointradar.repository.PointRepository;
import org.example.inpostpointradar.service.SyncService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/points")
@RequiredArgsConstructor
@Tag(name = "InPost Points", description = "Zarządzanie danymi o punktach odbioru")
public class PointController {

    private final SyncService syncService;
    private final PointRepository pointRepository;

    @PostMapping("/sync")
    public ResponseEntity<String> triggerSync() {
        syncService.synchronizeAllPoints();
        return ResponseEntity.ok("Synchronizacja uruchomiona. Sprawdź logi aplikacji, aby śledzić postęp.");
    }

    @GetMapping
    public ResponseEntity<List<Point>> getAllPoints() {
        return ResponseEntity.ok(pointRepository.findAll());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countPoints() {
        return ResponseEntity.ok(pointRepository.count());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Point>> searchByCity(@RequestParam String city) {
        return ResponseEntity.ok(pointRepository.findByLine2ContainingIgnoreCase(city));
    }
}