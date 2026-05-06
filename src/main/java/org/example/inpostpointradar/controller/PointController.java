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
    private final PointService pointService;

    @PostMapping("/sync")
    public ResponseEntity<String> triggerSync() {
        syncService.synchronizeAllPoints();
        return ResponseEntity.ok("Synchronization is working.");
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
    @GetMapping("/search/post-code")
    public ResponseEntity<List<Point>> searchByZipCode(@RequestParam String postCode) {
        return ResponseEntity.ok(pointService.getPointsByPostCode(postCode));
    }
    @GetMapping("/search/post-code/count")
    public ResponseEntity<Long> countPointsByZipCode(@RequestParam String postCode) {
        return ResponseEntity.ok(pointService.countPointsByPostCode(postCode));
    }
    @GetMapping("/search/city/street")
    public ResponseEntity<List<Point>> searchByCityAndStreet(@RequestParam String city, @RequestParam String street) {
        return ResponseEntity.ok(pointService.getPointsByCityAndStreet(city, street));
    }
    @GetMapping("/search/city/street/count")
    public ResponseEntity<Long> countByCityAndStreet(@RequestParam String city, @RequestParam String street) {
        return ResponseEntity.ok(pointService.countByCityAndStreet(city, street));
    }
    @GetMapping("/search/post-code/street")
    public ResponseEntity<List<Point>> searchByZipAndStreet(@RequestParam String postCode, @RequestParam String street) {
        return ResponseEntity.ok(pointService.getPointsByPostCodeAndStreet(postCode, street));
    }
    @GetMapping("/search/post-code/street/count")
    public ResponseEntity<Long> countByZipAndStreet(@RequestParam String postCode, @RequestParam String street) {
        return ResponseEntity.ok(pointService.countByPostCodeAndStreet(postCode, street));
    }
}