package de.claudioaltamura.java.multithreading.completionstage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CompletableFutureParallelTest {

  @Test
  @DisplayName("allOf parallely combines completable futures without returning a result")
  void parallelCombineWithNoResult() throws InterruptedException, ExecutionException {
    CompletableFutureWithEncapsulatedLogic completableFutureWithEncapsulatedLogic =
        new CompletableFutureWithEncapsulatedLogic();

    CompletableFuture<String> completableFuture1 =
        completableFutureWithEncapsulatedLogic.calculateAsync("Hello");
    CompletableFuture<String> completableFuture2 =
        completableFutureWithEncapsulatedLogic.calculateAsync("you");
    CompletableFuture<String> completableFuture3 =
        completableFutureWithEncapsulatedLogic.calculateAsync("!");

    CompletableFuture<Void> combinedFeature =
        CompletableFuture.allOf(completableFuture1, completableFuture2, completableFuture3);

    combinedFeature.get();

    assertAll(
        () -> assertThat(completableFuture1.isDone()).isTrue(),
        () -> assertThat(completableFuture2.isDone()).isTrue(),
        () -> assertThat(completableFuture3.isDone()).isTrue());
  }

  @Test
  @DisplayName("of parallely combines completable futures without returning a result")
  void parallelCombineWithResult() throws InterruptedException, ExecutionException {
    CompletableFutureWithEncapsulatedLogic completableFutureWithEncapsulatedLogic =
        new CompletableFutureWithEncapsulatedLogic();

    CompletableFuture<String> completableFuture1 =
        completableFutureWithEncapsulatedLogic.calculateAsync("Hello");
    CompletableFuture<String> completableFuture2 =
        completableFutureWithEncapsulatedLogic.calculateAsync("you");
    CompletableFuture<String> completableFuture3 =
        completableFutureWithEncapsulatedLogic.calculateAsync("!");

    String actual =
        Stream.of(completableFuture1, completableFuture2, completableFuture3)
            .map(CompletableFuture::join)
            .collect(Collectors.joining(" "));

    assertThat(actual).isEqualTo("hello you !");
  }
}
