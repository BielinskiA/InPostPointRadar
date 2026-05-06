package org.example.inpostpointradar.repository;

import org.example.inpostpointradar.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
    Optional<Point> findByName(String name);
    List<Point> findByCityContainingIgnoreCase(String city);
    long countByCityContainingIgnoreCase(String city);
    List<Point> findByPostCodeContainingIgnoreCase(String postCode);
    long countByPostCodeContainingIgnoreCase(String postCode);
}