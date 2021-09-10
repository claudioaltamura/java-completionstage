package de.claudioaltamura.java.multithreading.completionstage;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CompletableFutureCombiningFuturesTest {

  private final CompletableFutureWithEncapsulatedLogic completableFutureWithEncapsulatedLogic =
      new CompletableFutureWithEncapsulatedLogic();

  @Test
  @DisplayName(
      "thenCompose returns another object of the same type, asynchronously, chaining two Futures sequentially")
  void thenCompose() throws InterruptedException, ExecutionException {
    // thenCompose() uses the previous stage as the argument. It will flatten and return a Future
    // with the result directly.
    // So if the idea is to chain CompletableFuture methods then it’s better to use thenCompose().
    CompletableFuture<String> composedFuture =
        completableFutureWithEncapsulatedLogic
            .calculateAsync()
            .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "!"));

    assertThat(composedFuture.get()).isEqualTo("hello, world!");
  }

  @Test
  @DisplayName(
      "thenCombine with two independent Futures, combines functions and process both results")
  void thenCombineWith2IndependentFutures() throws InterruptedException, ExecutionException {
    CompletableFuture<String> combinedFuture =
        completableFutureWithEncapsulatedLogic
            .calculateAsync()
            .thenCombine(CompletableFuture.supplyAsync(() -> "!"), (s1, s2) -> s1 + s2);

    assertThat(combinedFuture.get()).isEqualTo("hello, world!");
  }

  @Test
  @DisplayName("thenAcceptBoth process both results, but not passing any result")
  void thenAcceptBoth() throws InterruptedException, ExecutionException {
    CompletableFuture<Void> combinedThenAcceptBothFuture =
        completableFutureWithEncapsulatedLogic
            .calculateAsync()
            .thenAcceptBoth(
                CompletableFuture.supplyAsync(() -> "!"), (s1, s2) -> System.out.println(s1 + s2));

    assertThat(combinedThenAcceptBothFuture.get()).isNull();
  }
}
