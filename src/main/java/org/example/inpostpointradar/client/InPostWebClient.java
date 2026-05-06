package org.example.inpostpointradar.client;

import lombok.extern.slf4j.Slf4j;
import org.example.inpostpointradar.client.dto.InPostResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class InPostWebClient implements InPostClient {

    private final WebClient webClient;

    public InPostWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<InPostResponse> fetchPoints(int page, int perPage) {
        log.debug("Fetching points from InPost API - page: {}, perPage: {}", page, perPage);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/points")
                        .queryParam("page", page)
                        .queryParam("per_page", perPage)
                        .build())
                .retrieve()
                .bodyToMono(InPostResponse.class)
                .doOnNext(response -> log.info("Successfully fetched page {} of {} from InPost", page, response.totalPages()))
                .doOnError(e -> log.error("Critical error during InPost API call: {}", e.getMessage()))
                .onErrorResume(e -> Mono.error(new RuntimeException("Failed to fetch InPost data", e)));
    }
}