package de.claudioaltamura.java.multithreading.completionstage;

import java.util.List;

class Countries {

  List<String> getCountries() {
    try {
      Thread.sleep(3 * 1000);
    } catch (InterruptedException ie) {
      //
    }
    return List.of("Italy", "Germany", "France", "Spain", "Portugal");
  }
}
