package org.example.inpostpointradar.client;

import lombok.extern.slf4j.Slf4j;
import org.example.inpostpointradar.client.dto.InPostResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class InPostWebClient implements InPostClient{

    private final WebClient webClient;

    public InPostWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<InPostResponse> fetchPoints(int page, int perPage) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/points")
                        .queryParam("page", page)
                        .queryParam("per_page", perPage)
                        .build())
                .retrieve()
                .onStatus(status -> status.isError(), response -> {
                    log.error("InPost API returned error status: {}", response.statusCode());
                    return Mono.error(new RuntimeException("InPost API Error: " + response.statusCode()));
                })
                .bodyToMono(InPostResponse.class)
                .doOnNext(response -> log.info("Pobrano stronę {}/{} z InPost.", page, response.getTotalPages()))
                .onErrorResume(e -> {
                    log.error("Błąd podczas pobierania danych z InPost (Strona {}): {}", page, e.getMessage());
                    return Mono.empty();
                });
    }
}