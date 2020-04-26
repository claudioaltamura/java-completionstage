package de.claudioaltamura.java.multithreading.completionstage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class CompletableFutureAsSimpleFutureTest {

  private final CompletableFutureAsSimpleFuture completableFutureAsSimpleFuture =
      new CompletableFutureAsSimpleFuture();

  @Test
  @Timeout(value = 600, unit = TimeUnit.MILLISECONDS)
  void completableFutureAsSimpleFuture() throws InterruptedException, ExecutionException {
    Future<String> completableFuture = completableFutureAsSimpleFuture.calculateAsync();
    String result = completableFuture.get();

    assertThat(result).isEqualTo("hello, world");
  }

  @Test
  @Timeout(value = 600, unit = TimeUnit.MILLISECONDS)
  void completableFutureAsSimpleFutureCancellation()
      throws InterruptedException, ExecutionException {
    Future<String> completableFuture =
        completableFutureAsSimpleFuture.calculateAsyncWithCancellation();

    Throwable thrown =
        catchThrowable(
            () -> {
              completableFuture.get();
            });

    assertThat(thrown).isInstanceOf(CancellationException.class);
  }
}
