package AStarPathFinding;

import java.util.ArrayList;

/**
 * This class is for nodes in the graph.
 * 
 * @author Haijin He
 */

public class Node
{

  String str; // string in the node
  ArrayList<Node> edges = new ArrayList<>(); // this arraylist holds all the
                                             // nodes that is adjacent to this
                                             // node.
  int cost_so_far; // cost from the source so far.
  int cost_estimate; // estimation for remaining cost to reach the destination.
  Node pre; // a pointer to previous Node in the path.
  int color; // color indicating the node is visited or not.

  /**
   * Getter method for cost_so_far.
   * 
   * @return cost_so_far.
   */
  public int getCost_so_far()
  {
    return cost_so_far;
  }

  /**
   * Setter method for cost_so_far
   * 
   * @param cost_so_far
   */
  public void setCost_so_far(int cost_so_far)
  {
    this.cost_so_far = cost_so_far;
  }

  /**
   * Constructor for Node. Takes a string input, and Assign it to member
   * variable str.
   * 
   * @param str
   */

  public Node(String str)
  {

    this.str = str;
  }

  /**
   * Getter method for str.
   * 
   * @return
   */
  public String getStr()
  {
    return str;
  }

  /**
   * Setter method for str.
   * 
   * @param str
   */
  public void setStr(String str)
  {
    this.str = str;
  }

  // ***********************************
  // main method. Used for testing.
  // ***********************************

  public static void main(String[] args)
  {
    // TODO Auto-generated method stub
    Node n = new Node("test");
    System.out.println(n.getStr());

  }

}
