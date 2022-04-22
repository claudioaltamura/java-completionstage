package de.claudioaltamura.java.multithreading.completionstage;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

class CompletableFutureTimeoutTest {

  @Test
  void timeout() {
    Countries countries = new Countries();
    CompletableFuture.supplyAsync(countries::getCountries).orTimeout(1, TimeUnit.MILLISECONDS);
  }
}
