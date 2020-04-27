package edu.shafiFgcu;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        BagOfWords b = new BagOfWords();
        b.loadFile();
        b.compare("poakdasdl");

    }
}
