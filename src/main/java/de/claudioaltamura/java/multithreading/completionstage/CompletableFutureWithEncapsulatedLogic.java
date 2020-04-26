package de.claudioaltamura.java.multithreading.completionstage;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureWithEncapsulatedLogic {

  public CompletableFuture<String> calculateAsync() {
    return CompletableFuture.supplyAsync(() -> "hello, world");
  }
}
