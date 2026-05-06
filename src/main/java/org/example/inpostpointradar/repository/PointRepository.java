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
    List<Point> findByCityIgnoreCaseAndStreetContainingIgnoreCase(String city, String street);
    long countByCityIgnoreCaseAndStreetContainingIgnoreCase(String city, String street);
    List<Point> findByPostCodeIgnoreCaseAndStreetContainingIgnoreCase(String postCode, String street);
    long countByPostCodeIgnoreCaseAndStreetContainingIgnoreCase(String postCode, String street);
}