package de.claudioaltamura.java.multithreading.completionstage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CompletableFutureHandlingErrorsTest {

  private final CompletableFutureWithEncapsulatedLogic completableFutureWithEncapsulatedLogic =
      new CompletableFutureWithEncapsulatedLogic();

  @Test
  @DisplayName("handle allows you to handle unchecked exceptions")
  void handleExceptionGoodCase() throws InterruptedException, ExecutionException {
    Future<String> completableFuture =
        completableFutureWithEncapsulatedLogic
            .calculateAsyncWithException("you")
            .handle((s, t) -> s != null ? s : "Hello");

    assertThat(completableFuture.get()).isEqualTo("hello");
  }

  @Test
  void handleExceptionBadCase() throws InterruptedException, ExecutionException {
    Future<String> completableFuture =
        completableFutureWithEncapsulatedLogic
            .calculateAsyncWithException(null)
            .handle((s, t) -> s != null ? s : "hello");

    assertThat(completableFuture.get()).isEqualTo("hello");
  }

  @Test
  @DisplayName(
      "manually complete the Future with a value, but also to have the ability to complete it with an exception.")
  void exceptionally() throws InterruptedException, ExecutionException {
    Future<String> completableFuture =
        completableFutureWithEncapsulatedLogic
            .calculateAsyncWithException(null)
            .exceptionally(
                e -> {
                  System.out.println(e.getMessage());
                  throw new RuntimeException("calculation failed");
                });

    Throwable thrown =
        catchThrowable(
            () -> {
              completableFuture.get();
            });

    assertThat(thrown)
        .hasRootCauseExactlyInstanceOf(RuntimeException.class)
        .hasMessageContaining("calculation failed");
  }

  @Test
  @DisplayName("whenComplete is a callback that do not interfere")
  void whenCompleteGoodCase() throws InterruptedException, ExecutionException {
    Future<String> completableFuture =
        completableFutureWithEncapsulatedLogic
            .calculateAsyncWithException("you")
            .whenComplete(
                (s, e) -> {
                  if (e != null) {
                    System.out.println("exception occurred");
                  } else {
                    System.out.println("no exception occurred");
                  }
                })
            .exceptionally(
                e -> {
                  System.out.println(e.getMessage());
                  throw new RuntimeException("calculation failed");
                });

    assertThat(completableFuture.get()).isEqualTo("hello you");
  }

  @Test
  void whenCompleteBadCase() throws InterruptedException, ExecutionException {
    Future<String> completableFuture =
        completableFutureWithEncapsulatedLogic
            .calculateAsyncWithException(null)
            .whenComplete(
                (s, e) -> {
                  if (e != null) {
                    System.out.println("exception occurred");
                  } else {
                    System.out.println("no exception occurred");
                  }
                })
            .exceptionally(
                e -> {
                  System.out.println(e.getMessage());
                  throw new RuntimeException("calculation failed");
                });

    Throwable thrown =
        catchThrowable(
            () -> {
              completableFuture.get();
            });

    assertThat(thrown)
        .hasRootCauseExactlyInstanceOf(RuntimeException.class)
        .hasMessageContaining("calculation failed");
  }
}
