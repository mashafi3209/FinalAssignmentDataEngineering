package edu.shafiFgcu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class BagOfWords {

  private File dataFile = new File("./src/edu/shafiFgcu/messages.txt");

  private Map<String, Integer> bag = new HashMap<>();
  private NavigableMap<String, Integer> allWordByKey = new TreeMap<>();


  protected void loadFile() throws IOException {

    try {
      BufferedReader reader = new BufferedReader(new FileReader(dataFile));
      while ((line = reader.readLine()) != null) {
        createTreeMap(line);

      }
      reader.close();
    } catch (Exception io) {
      io.printStackTrace();
    }
    buildBag();

  }

  private void createTreeMap(String curLine) {
    /**
     *Build allWordsByKey Map based on line input from file
     * @param curline = the current line to process from file
     */
    String[] alltokens = curLine.trim().split("\\s+");
    List<String> pairs = new ArrayList<String>();
    for (int i = 0; i < alltokens.length - 1; ++i) {
      pairs.add(alltokens[i] + ", " + alltokens[i + 1]);
    }
    for (String token : pairs) {
      allWordByKey.merge(token, 1, Integer::sum);
    }
  }

  private void buildBag() {
    /** sort from highest to lowest for value using stream*/
    bag = allWordByKey.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
            (e1, e2) -> e1, LinkedHashMap::new));

  }



  protected void compare(String input) {
    double totalOccourance = 0;
    boolean found = false;
    for (Map.Entry<String, Integer> entry : allWordByKey.entrySet()) {
      String pairsOfWords = entry.getKey();

      try {

        String initial = pairsOfWords.substring(0, input.length());

        if (initial.equals(input)) {
          int valueOfOccours = entry.getValue();
          totalOccourance += valueOfOccours;

        } else {

        }

      } catch (StringIndexOutOfBoundsException e) {

      }

    }
    for (Map.Entry<String, Integer> entry : allWordByKey.entrySet()) {
      String pairsOfWords = entry.getKey();

      try {

        String initial = pairsOfWords.substring(0, input.length());

        if (initial.equals(input)) {
          int valueOfOccours = entry.getValue();
          double support = entry.getValue() / totalOccourance;

          if (support > .65) {
            System.out
                .println("Your next word might be " + entry.getKey().substring(input.length() + 1));
            found = true;

          }
        } else {

        }

      } catch (StringIndexOutOfBoundsException e) {

      }

    }
    if (found) {
      System.out.println("Your next word might be 'the' ");
      System.out.println("Your next word might be 'this' ");
    } else {
      System.out.println("Your next word might be 'the'.");
      System.out.println("Your next word might be 'this' .");
      System.out.println("Your next word might be 'of'.");
    }

  }
}
