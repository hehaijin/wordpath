package AStarPathFinding;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class have a static method that reads strings from a file.
 * 
 * @author Haijin He
 *
 */
public class ReadFile
{

  /**
   * Read the given file and returns to a String buffer.
   * 
   * @param path
   *          The String referring to the file path of the dictionary.
   * @return String array that holds all strings read from the dictionary file.
   */

  public static String[] readTextFile(String path)
  {
    Scanner sc = null;
    try
    {
      sc = new Scanner(new File(path));
    } catch (Exception e)
    {
      // TODO Auto-generated catch block
      System.out.println("Please give a right path for the dictionary file!");

    }
    List<String> list = new ArrayList<>();
    while (sc.hasNext())
      list.add(sc.next());
    sc.close();
    String[] str = new String[list.size()];
    int index = 0;
    for (String s : list)
    {
      str[index] = s;
      index++;

    }
    System.out.println("Reading from file done!");

    return str;

  }

  /**
   * Main method. Used for testing.
   */

  public static void main(String[] args)
  {
    // TODO Auto-generated method stub

    ReadFile.readTextFile("res/OpenEnglishWordList.txt");

  }

}
