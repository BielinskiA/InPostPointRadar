package org.example.inpostpointradar.controller;

import lombok.RequiredArgsConstructor;
import org.example.inpostpointradar.domain.Point;
import org.example.inpostpointradar.service.PointService;
import org.example.inpostpointradar.service.SyncService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/points")
@RequiredArgsConstructor
public class PointController {

    private final SyncService syncService;
    private final PointService pointService; // Używamy tylko serwisu!

    @PostMapping("/sync")
    public ResponseEntity<String> triggerSync() {
        syncService.synchronizeAllPoints();
        return ResponseEntity.ok("Synchronizacja uruchomiona w tle.");
    }

    @GetMapping
    public ResponseEntity<List<Point>> getAllPoints() {
        return ResponseEntity.ok(pointService.getAllPoints());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countPoints() {
        return ResponseEntity.ok(pointService.getPointsCount());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Point>> searchByCity(@RequestParam String city) {
        return ResponseEntity.ok(pointService.findByCity(city));
    }

    @GetMapping("/search/count")
    public ResponseEntity<Long> countPointsInCity(@RequestParam String city) {
        return ResponseEntity.ok(pointService.countPointsInCity(city));
    }

    @GetMapping("/search/zip")
    public ResponseEntity<List<Point>> searchByZipCode(@RequestParam String zipCode) {
        return ResponseEntity.ok(pointService.getPointsByPostCode(zipCode));
    }

    @GetMapping("/search/zip/count")
    public ResponseEntity<Long> countPointsByZipCode(@RequestParam String zipCode) {
        return ResponseEntity.ok(pointService.countPointsByPostCode(zipCode));
    }
}