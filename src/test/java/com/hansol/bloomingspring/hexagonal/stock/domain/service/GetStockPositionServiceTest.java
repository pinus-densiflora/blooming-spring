package com.hansol.bloomingspring.hexagonal.stock.domain.service;

import com.hansol.bloomingspring.hexagonal.stock.domain.model.DomainModelFaker;
import com.hansol.bloomingspring.hexagonal.stock.domain.model.StockPosition;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetStockPositionServiceTest {

    private final StockPositionsRepository repository = mock(StockPositionsRepository.class);
    private final GetStockPositionService subject = new GetStockPositionService(repository);

    @Test
    void get() {
        // arrange
        String user = DomainModelFaker.fakeUser();
        String symbol = DomainModelFaker.fakeStockSymbol();
        StockPosition fakeStockPosition = DomainModelFaker.fakeStockPosition(user, symbol);
        when(repository.findOneByUserAndSymbol(user, symbol)).thenReturn(Mono.just(fakeStockPosition));

        // act
        Mono<StockPosition> result = subject.get(user, symbol);

        // assert
        StepVerifier.create(result)
                .expectNext(fakeStockPosition)
                .verifyComplete();
    }
}