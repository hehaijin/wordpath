package AStarPathFinding;

/**
 * This class contains several static methods for calculating distance for two
 * strings
 * 
 * @author Haijin He
 *
 */

public class StringDistance
{

  /**
   * Calculate the Hamming distance of two strings.
   * 
   * @param s1
   *          String s1
   * @param s2
   *          String s2
   * @return The hamming distance between s1 and s2. return -1 if their length
   *         are not equal.
   */
  public static int hamming(String s1, String s2)
  {
    if (s1.length() != s2.length())
      return -1;
    if (s1.equals(s2))
      return 0;
    int count = 0;
    for (int i = 0; i < s1.length(); i++)
    {
      if (s1.charAt(i) != s2.charAt(i))
        count++;

    }
    return count;
  }

  /**
   * Check if two strings levenshtein distance is 1.
   * 
   * @param s1
   * @param s2
   * @return
   */
  public static boolean isLeven1(String s1, String s2)
  {
    if (Math.abs(s1.length() - s2.length()) > 1)
      return false;
    if (s1.length() == s2.length() && hamming(s1, s2) == 1)
      return true;
    if (s1.length() < s2.length())
    {
      for (int i = 0; i < s2.length(); i++)
      {
        if (s1.equals(s2.substring(0, i) + s2.substring(i + 1)))
          return true;
      }

    } else
    {
      for (int i = 0; i < s1.length(); i++)
      {
        if (s2.equals(s1.substring(0, i) + s1.substring(i + 1)))
          return true;
      }
    }

    return false;

  }

  /**
   * Calculates the estimated distance between string s1 and s2.
   * 
   * @param s1
   *          String s1
   * @param s2
   *          String s2
   * @return The estimated distance between s1 and s2. Here it's simply the
   *         length differnce.
   */

  public static int estimate(String s1, String s2)
  {

    return Math.abs(s1.length() - s2.length());

  }

  // ***********************************
  // main method. Used for testing.
  // ***********************************

  public static void main(String[] args)
  {
    // TODO Auto-generated method stub
    // test cases from class material.
    System.out.println(StringDistance.hamming("karolin", "kathrin"));
    System.out.println(StringDistance.hamming("karolin", "kerstin"));
    System.out.println(StringDistance.hamming("1011101", "1001001"));
    System.out.println(StringDistance.hamming("2173896", "2233796"));
    System.out.println(StringDistance.isLeven1("karolin", "karolint"));
    System.out.println(StringDistance.isLeven1("karolin", "karolintt"));

  }

}
