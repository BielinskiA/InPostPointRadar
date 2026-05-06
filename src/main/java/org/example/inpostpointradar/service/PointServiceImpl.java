package org.example.inpostpointradar.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.inpostpointradar.domain.Point;
import org.example.inpostpointradar.repository.PointRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointServiceImpl implements PointService {
    private final PointRepository pointRepository;
    @Override
    public List<Point> getAllPoints() {
        log.debug("Fetching all points from database");
        return pointRepository.findAll();
    }
    @Override
    public long getPointsCount() {
        return pointRepository.count();
    }
    @Override
    public List<Point> findByCity(String city) {
        log.debug("Searching for points in city: {}", city);
        return pointRepository.findByCityContainingIgnoreCase(city);
    }
    @Override
    public long countPointsInCity(String city) {
        return pointRepository.countByCityContainingIgnoreCase(city);
    }
    @Override
    public List<Point> getPointsByPostCode(String postCode) {
        log.debug("Searching for points with post code: {}", postCode);
        return pointRepository.findByPostCodeContainingIgnoreCase(postCode);
    }
    @Override
    public long countPointsByPostCode(String postCode) {
        return pointRepository.countByPostCodeContainingIgnoreCase(postCode);
    }
    @Override
    public List<Point> getPointsByCityAndStreet(String city, String street) {
        return pointRepository.findByCityIgnoreCaseAndStreetContainingIgnoreCase(city, street);
    }
    @Override
    public long countByCityAndStreet(String city, String street) {
        return pointRepository.countByCityIgnoreCaseAndStreetContainingIgnoreCase(city, street);
    }
    @Override
    public List<Point> getPointsByPostCodeAndStreet(String postCode, String street) {
        return pointRepository.findByPostCodeIgnoreCaseAndStreetContainingIgnoreCase(postCode, street);
    }
    @Override
    public long countByPostCodeAndStreet(String postCode, String street) {
        return pointRepository.countByPostCodeIgnoreCaseAndStreetContainingIgnoreCase(postCode, street);
    }
}