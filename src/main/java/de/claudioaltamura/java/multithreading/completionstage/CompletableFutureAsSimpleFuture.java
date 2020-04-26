package de.claudioaltamura.java.multithreading.completionstage;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletableFutureAsSimpleFuture {

  public Future<String> calculateAsync() {
    CompletableFuture<String> completableFuture = new CompletableFuture<>();

    Executors.newCachedThreadPool()
        .submit(
            () -> {
              Thread.sleep(500);
              completableFuture.complete("hello, world");
              return null;
            });

    return completableFuture;
  }

  public Future<String> calculateAsyncWithCancellation() throws InterruptedException {
    CompletableFuture<String> completableFuture = new CompletableFuture<>();

    Executors.newCachedThreadPool()
        .submit(
            () -> {
              Thread.sleep(500);
              completableFuture.cancel(false);
              return null;
            });

    return completableFuture;
  }
}
