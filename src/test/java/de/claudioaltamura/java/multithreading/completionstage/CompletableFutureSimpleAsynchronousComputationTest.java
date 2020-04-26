package de.claudioaltamura.java.multithreading.completionstage;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CompletableFutureSimpleAsynchronousComputationTest {

  private final CompletableFutureWithEncapsulatedLogic completableFutureWithEncapsulatedLogic =
      new CompletableFutureWithEncapsulatedLogic();

  @Test
  @DisplayName("thenApply returns a value, chaining synchronously, sequentially")
  void thenApply() throws InterruptedException, ExecutionException {
    CompletableFuture<String> completableFuture =
        completableFutureWithEncapsulatedLogic.calculateAsync();

    CompletableFuture<String> appliedFuture = completableFuture.thenApply(s -> s + "!");

    assertThat(appliedFuture.get()).isEqualTo("hello, world!");
  }

  @Test
  @DisplayName("thenAccept returns just void")
  void thenAccept() throws InterruptedException, ExecutionException {
    CompletableFuture<String> completableFuture =
        completableFutureWithEncapsulatedLogic.calculateAsync();

    CompletableFuture<Void> acceptedFuture =
        completableFuture.thenAccept(s -> System.out.println(s + "!"));

    assertThat(acceptedFuture.get()).isNull();
  }

  @Test
  @DisplayName("thenRun runs at the end")
  void thenRun() throws InterruptedException, ExecutionException {
    CompletableFuture<String> completableFuture =
        completableFutureWithEncapsulatedLogic.calculateAsync();

    CompletableFuture<Void> acceptedFuture =
        completableFuture.thenRun(() -> System.out.println("finished"));

    assertThat(acceptedFuture.get()).isNull();
  }
}
