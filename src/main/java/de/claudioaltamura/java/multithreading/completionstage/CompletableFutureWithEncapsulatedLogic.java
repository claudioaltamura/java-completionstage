package de.claudioaltamura.java.multithreading.completionstage;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureWithEncapsulatedLogic {

  public CompletableFuture<String> calculateAsync() {
    return CompletableFuture.supplyAsync(() -> "hello, world");
  }

  public CompletableFuture<String> calculateAsync(String str) {
    return CompletableFuture.supplyAsync(() -> str);
  }

  public CompletableFuture<String> calculateAsyncWithException(String str) {
    return CompletableFuture.supplyAsync(
        () -> {
          if (str == null) throw new IllegalArgumentException();
          return "hello " + str;
        });
  }
}
