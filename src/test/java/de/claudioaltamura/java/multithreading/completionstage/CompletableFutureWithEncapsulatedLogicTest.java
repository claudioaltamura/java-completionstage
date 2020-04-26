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
}
