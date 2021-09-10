package de.claudioaltamura.java.multithreading.completionstage;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CompletableFutureWithAsyncMethodsTest {

  @Test
  @DisplayName(
      "methods with Async postfix running a corresponding step of execution in another thread.")
  void asyncMethods() throws InterruptedException, ExecutionException {
    CompletableFutureWithEncapsulatedLogic completableFutureWithEncapsulatedLogic =
        new CompletableFutureWithEncapsulatedLogic();

    // thenApplyAsync with executor and without (using the common fork/join pool)
    CompletableFuture<String> completableFuture =
        completableFutureWithEncapsulatedLogic
            .calculateAsync("hello")
            .thenApplyAsync(s -> s + ", world");

    assertThat(completableFuture.get()).isEqualTo("hello, world");
  }
}
