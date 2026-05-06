package org.example.inpostpointradar.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.inpostpointradar.client.InPostClient;
import org.example.inpostpointradar.client.dto.PointDto;
import org.example.inpostpointradar.domain.Point;
import org.example.inpostpointradar.domain.PointType;
import org.example.inpostpointradar.repository.PointRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Service
@RequiredArgsConstructor
public class SyncServiceImpl implements SyncService {

    private final InPostClient inPostClient;
    private final PointRepository pointRepository;

    @Override
    @Async
    public void synchronizeAllPoints() {
        log.info("Starting InPost points synchronization...");

        inPostClient.fetchPoints(1, 100)
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(response -> {
                    int totalPages = response.totalPages();
                    log.info("Found {} pages to fetch.", totalPages);

                    Flux.range(1, totalPages)
                            .flatMap(page -> inPostClient.fetchPoints(page, 100), 5)
                            .publishOn(Schedulers.boundedElastic())
                            .doOnNext(res -> {
                                if (res.items() != null) {
                                    res.items().forEach(this::saveOrUpdate);
                                }
                            })
                            .subscribe(
                                    null,
                                    err -> log.error("Error during page processing: {}", err.getMessage()),
                                    () -> log.info("Full synchronization finished!")
                            );
                }, error -> log.error("Failed to initialize sync: {}", error.getMessage()));
    }

    public void saveOrUpdate(PointDto dto) {
        try {
            Point point = pointRepository.findByName(dto.name())
                    .orElseGet(Point::new);

            point.setName(dto.name());
            point.setStatus(dto.status());
            String typeValue = (dto.type() != null && !dto.type().isEmpty())
                    ? dto.type().iterator().next()
                    : "UNKNOWN";

            point.setType(PointType.fromString(typeValue));

            if (dto.addressDetails() != null) {
                var addr = dto.addressDetails();
                point.setCity(addr.city());
                point.setPostCode(addr.postCode());

                String street = addr.street() != null ? addr.street() : "";
                String building = addr.buildingNumber() != null ? addr.buildingNumber() : "";
                point.setStreet((street + " " + building).trim());
            }

            if (dto.location() != null) {
                point.setLatitude(dto.location().latitude());
                point.setLongitude(dto.location().longitude());
            }

            point.setFunctions(dto.functions());
            pointRepository.save(point);
        } catch (Exception e) {
            log.warn("Skipping point {}: {}", dto.name(), e.getMessage());
        }
    }
}