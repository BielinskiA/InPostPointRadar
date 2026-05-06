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
    List<Point> getPointsByCityAndStreet(String city, String street);
    long countByCityAndStreet(String city, String street);
    List<Point> getPointsByPostCodeAndStreet(String postCode, String street);
    long countByPostCodeAndStreet(String postCode, String street);
}