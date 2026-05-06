package org.example.inpostpointradar.service;

import org.example.inpostpointradar.client.dto.AddressDto;
import org.example.inpostpointradar.client.dto.PointDto;
import org.example.inpostpointradar.domain.Point;
import org.example.inpostpointradar.domain.PointType;
import org.example.inpostpointradar.repository.PointRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SyncServiceImplTest {

    @Mock
    private PointRepository pointRepository;
    @InjectMocks
    private SyncServiceImpl syncService;

    @Test
    void shouldCorrectlyMapAndSaveNewPoint() {
        AddressDto address = new AddressDto("Gniezno", "Wielkopolskie", "62-200", "Jagiellońska", "10");

        PointDto dto = new PointDto(
                "GN-123",
                Set.of("parcel_locker"),
                "Operating",
                Set.of("parcel"),
                address,
                null
        );

        when(pointRepository.findByName("GN-123")).thenReturn(Optional.empty());

        syncService.saveOrUpdate(dto);

        ArgumentCaptor<Point> captor = ArgumentCaptor.forClass(Point.class);
        verify(pointRepository).save(captor.capture());

        Point savedPoint = captor.getValue();
        assertEquals("GN-123", savedPoint.getName());
        assertEquals("Gniezno", savedPoint.getCity());
        assertEquals("Jagiellońska 10", savedPoint.getStreet());
        assertEquals(PointType.PARCEL_LOCKER, savedPoint.getType());
    }
}