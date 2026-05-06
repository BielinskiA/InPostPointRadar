package org.example.inpostpointradar.service;

import org.example.inpostpointradar.domain.Point;
import java.util.List;

public interface PointService {
    List<Point> getAllPoints();
    long getPointsCount();
    List<Point> findByCity(String city);
    long countPointsInCity(String city);
    List<Point> getPointsByPostCode(String postCode);
    long countPointsByPostCode(String postCode);
}