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
}