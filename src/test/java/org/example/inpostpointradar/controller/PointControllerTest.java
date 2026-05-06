package org.example.inpostpointradar.controller;

import org.example.inpostpointradar.service.PointService;
import org.example.inpostpointradar.service.SyncService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.mockito.Mockito.when;

@WebFluxTest(PointController.class)
class PointControllerTest {

    @Autowired
    private WebTestClient webTestClient;
    @MockitoBean
    private PointService pointService;
    @MockitoBean
    private SyncService syncService;

    @Test
    void shouldReturnPointsCount() {
        when(pointService.getPointsCount()).thenReturn(100L);

        webTestClient.get()
                .uri("/api/v1/points/count")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Long.class)
                .isEqualTo(100L);
    }
}