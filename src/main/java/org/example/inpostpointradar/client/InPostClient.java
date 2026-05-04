package org.example.inpostpointradar.client;

import org.example.inpostpointradar.client.dto.InPostResponse;
import reactor.core.publisher.Mono;

public interface InPostClient {
    Mono<InPostResponse> fetchPoints(int page, int perPage);
}