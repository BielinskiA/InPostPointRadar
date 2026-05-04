package org.example.inpostpointradar.service;

import org.example.inpostpointradar.domain.Point;
import java.util.List;

public interface PointService {
    List<Point> getAllPoints();
    long getPointsCount();
}