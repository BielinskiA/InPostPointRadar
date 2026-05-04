package org.example.inpostpointradar.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.inpostpointradar.client.InPostClient;
import org.example.inpostpointradar.client.dto.PointDto;
import org.example.inpostpointradar.domain.Point;
import org.example.inpostpointradar.repository.PointRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SyncServiceImpl implements SyncService {

    private final InPostClient inPostClient;
    private final PointRepository pointRepository;

    @Override
    @Async
    public void synchronizeAllPoints() {
        log.info("Rozpoczynanie pełnej synchronizacji punktów InPost...");

        inPostClient.fetchPoints(1, 100)
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(response -> {
                    int totalPages = response.getTotalPages();
                    log.info("Znaleziono {} stron do pobrania.", totalPages);

                    Flux.range(1, totalPages)
                            .flatMap(page -> inPostClient.fetchPoints(page, 100), 5)
                            .publishOn(Schedulers.boundedElastic())
                            .doOnNext(res -> {
                                List<Point> entities = res.getItems().stream()
                                        .map(this::mapToEntity)
                                        .collect(Collectors.toList());
                                pointRepository.saveAll(entities);
                                log.info("Zapisano partię danych z InPost API.");
                            })
                            .subscribe();
                }, error -> log.error("Błąd inicjalizacji: {}", error.getMessage()));
    }

    private Point mapToEntity(PointDto dto) {
        return Point.builder()
                .name(dto.getName())
                .type(dto.getType() != null && !dto.getType().isEmpty()
                        ? dto.getType().iterator().next() : "unknown")
                .status(dto.getStatus())
                .line1(dto.getAddressDetails() != null ? dto.getAddressDetails().getLine1() : null)
                .line2(dto.getAddressDetails() != null ? dto.getAddressDetails().getLine2() : null)
                .latitude(dto.getLocation() != null ? dto.getLocation().getLatitude() : null)
                .longitude(dto.getLocation() != null ? dto.getLocation().getLongitude() : null)
                .functions(dto.getFunctions())
                .build();
    }
}