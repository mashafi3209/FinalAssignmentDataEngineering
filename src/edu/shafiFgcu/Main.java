package edu.shafiFgcu;

import java.io.IOException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws IOException {
    // write your code here

    Scanner input = new Scanner(System.in);
    System.out.println("Type your word ");
    String inputWord = input.nextLine();
    BagOfWords b = new BagOfWords();
    b.loadFile();
    b.compare(inputWord);

  }
}
