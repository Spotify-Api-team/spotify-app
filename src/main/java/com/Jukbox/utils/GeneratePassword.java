package com.Jukbox.utils;

import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GeneratePassword {

    public static void main(String[] args) {
      System.out.print(createPassword());
    }

    public static int randomGen(int length) {

      return ThreadLocalRandom.current().nextInt(0, length);
  }

  public static List<String> getWords(){
    List<String> data = new ArrayList<>(); 
    try {
			Scanner scanner = new Scanner(new File("/static/five_letter.txt"));
			while (scanner.hasNextLine()) {
				data.add(scanner.nextLine());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
    }

    return(data);
  }

  public static String createPassword(){

    List<String> data= getWords();
        //972 words in text doc 
        //word1
        String word1 =data.get(randomGen(5757));
        //word2
        String word2 =data.get(randomGen(5757));
        //word3
        String word3 =data.get(randomGen(5757));

        String finWord= word1 + "-" + word2 + "-" + word3;
        return(finWord);
  
  }

}