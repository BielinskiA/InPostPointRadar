package org.example.inpostpointradar.repository;
import org.example.inpostpointradar.domain.Point;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PointRepositoryTest {

    @Autowired
    private PointRepository pointRepository;

    @Test
    void shouldFindPointsByCityCaseInsensitive() {
        Point p = Point.builder()
                .name("GN-1")
                .city("Gniezno")
                .build();
        pointRepository.save(p);

        List<Point> results = pointRepository.findByCityContainingIgnoreCase("gnie");

        assertFalse(results.isEmpty());
        assertEquals("Gniezno", results.get(0).getCity());
    }
}