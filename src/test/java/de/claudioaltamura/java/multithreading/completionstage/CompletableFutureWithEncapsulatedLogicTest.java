package de.claudioaltamura.java.multithreading.completionstage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.junit.jupiter.api.Test;

class CompletableFutureWithEncapsulatedLogicTest {

  @Test
  void completableFutureAsSimpleFuture() throws InterruptedException, ExecutionException {
    CompletableFutureWithEncapsulatedLogic completableFutureWithEncapsulatedLogic =
        new CompletableFutureWithEncapsulatedLogic();

    Future<String> completableFuture = completableFutureWithEncapsulatedLogic.calculateAsync();

    assertThat(completableFuture.get()).isEqualTo("hello, world");
  }

  @Test
  void completableFutureThenAccept() throws ExecutionException, InterruptedException {
    CompletableFutureWithEncapsulatedLogic completableFutureWithEncapsulatedLogic =
        new CompletableFutureWithEncapsulatedLogic();

    Future<String> completableFuture =
        completableFutureWithEncapsulatedLogic
            .calculateAsync()
            .thenApply(s -> "computation returned: " + s);
    // .thenCompose(s -> CompletableFuture.supplyAsync(() -> "computation returned: " + s));

    assertThat(completableFuture.get()).isEqualTo("computation returned: hello, world");
  }
}
