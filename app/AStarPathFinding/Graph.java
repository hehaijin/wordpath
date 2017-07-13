package AStarPathFinding;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * This is the class for building up the graph, look for the path between two
 * strings and print the path. It's main method is the entry of the program.
 * 
 * @author Haijin He
 */

public class Graph
{

  HashMap<String, Node> graph = new HashMap<>(130000);// holds all nodes.
  HashSet<String> words = new HashSet<String>(130000);// holds all words.

  /**
   * Constructor of Graph class. First read the dictionary into a file. For each
   * word in the dictionary, generate all its neighbors, scan the dictionary if
   * any word matches the neighbors, and add edge.
   * 
   * @param path
   *          an string for the path of the dictionary file.
   */

  public Graph(String path)
  {

    String[] s = ReadFile.readTextFile(path);

    System.out.println("Now generating the graph.");

    for (int i = 0; i < s.length; i++)
    {
      words.add(s[i]);
      graph.put(s[i], new Node(s[i]));

    }
    System.out.println("Nodes added");
    for (int i = 0; i < s.length; i++)
    {
      String[] neighbors = StringNeighbor.generate(s[i]);
      for (String s2 : neighbors)
      {
        if (words.contains(s2))
          addEdge(graph.get(s2), graph.get(s[i]));
      }

    }
    System.out.println("Edges added");

    System.out.println("Graph generated!");

  }

  /**
   * Adding edge for two nodes.
   * 
   * @param a
   *          one of the two nodes for adding edge.
   * @param b
   *          one of the two node for adding edge
   */

  public void addEdge(Node a, Node b)
  {
    if (a == b)
      return;
    if (!a.edges.contains(b))
      a.edges.add(b);
    if (!b.edges.contains(a))
      b.edges.add(a);

  }

  /**
   * Method used for displaying a small graph. used for testing.
   */

  public void showGraph()
  {
    if (graph.size() > 1000)
    {
      System.out.println("Graph is too large! Can not display !");
      return;
    }
    Set<String> strings = graph.keySet();
    for (String s : strings)
    {
      System.out.print(s + " : ");
      for (Node n : graph.get(s).edges)
        System.out.print(n.str + " ");

      System.out.println();
    }

  }

  /**
   * Method for showing all adjacent edge for a node containing string s. used
   * for testing.
   * 
   * @param s
   *          The given String.
   */

  public void showNeighbors(String s)
  {
    Node source = graph.get(s);
    for (Node n : source.edges)
      System.out.print(n.str + " ");
    System.out.println();

  }

  /**
   * Finds the path between source node and destination node. First check if
   * string s and d really have a node. If not, print an error message and
   * return. The precedure uses A* algorithm. A priority queue is used, based on
   * the addition of cost_so_far and cost_estimate. cost_so_far is current
   * distance from source cost_estimate is the estimate of remaining distance
   * from destination. Here it is simply the length difference. Each step, takes
   * a node from the priority queue, and recalculate all of it's neighbors.
   * 
   * @param s
   *          the source node with string s
   * @param d
   *          the destination node with string d
   */

  public void findPath(String s, String d)
  {

    Node source = graph.get(s);
    String notfound = "";
    if (source == null)
    {
      notfound = notfound + s;
    }

    Node destination = graph.get(d);
    if (destination == null)
    {
      notfound = notfound + " " + d;
    }
    if (notfound.length() > 0)
    {
      System.out.println("Can not find " + notfound + " in the dictionary");
      return;
    }
    source.color = 0;
    initGraph(s, d);
    PriorityQueue<Node> frontier = new PriorityQueue<>(100, new Comparator<Node>()
    {

      @Override
      public int compare(Node o1, Node o2)
      {
        // TODO Auto-generated method stub
        return o1.cost_so_far + o1.cost_estimate - o2.cost_so_far - o2.cost_estimate;
      }

    });

    frontier.offer(source);
    while (frontier.size() > 0)
    {
      Node u = frontier.poll();
      Iterator<Node> it = u.edges.iterator();
      while (it.hasNext())
      {
        Node v = it.next();
        if (v.color == 1)
        {
          v.color = 0;
          if (v.cost_so_far > u.cost_so_far + 1)
          {
            v.cost_so_far = u.cost_so_far + 1;

            v.cost_estimate = v.cost_so_far + StringDistance.estimate(v.str, d);
          }
          v.pre = u;
          frontier.offer(v);

        }
        if (v == destination)
          return;
      }
      u.color = -1;

    }

  }

  /**
   * initialize the graph for source node and destination node.
   * 
   * @param s
   *          source node with string s
   * @param d
   *          destination node with string d
   */

  public void initGraph(String s, String d)
  {
    Iterator<String> it = graph.keySet().iterator();
    while (it.hasNext())
    {
      String str = it.next();
      graph.get(str).setCost_so_far(10000);// assign a big number.
      graph.get(str).cost_estimate = 10000; // assign a big number.
      graph.get(str).pre = null;
      graph.get(str).color = 1;
    }
    graph.get(s).cost_so_far = 0;
    graph.get(s).color = 0;

  }

  /**
   * Prints the path from source node to destination node. Starts from
   * destination node, push every previous node into a stack, till it reach
   * source node. Then pops all nodes out from the stack.
   * 
   * @param s
   *          source node with string s
   * @param d
   *          destination node with string d
   */

  public void printPath(String s, String d)
  {
    Node p = graph.get(d);
    if (p == null)
      return;
    if (p.pre == null)
    {
      System.out.println("NO POSSIBLE PATH between: " + s + " and " + d);
      return;
    }
    ArrayDeque<String> stack = new ArrayDeque<>();

    while (p.pre != null)
    {
      stack.push(p.str);
      p = p.pre;

    }
    stack.push(p.str);
    while (stack.size() > 0)
    {
      System.out.print(stack.pop() + "  ");
    }

    System.out.println();

  }
  
  public String getPath(String s, String d)
  {
    Node p = graph.get(d);
    if (p == null)
      return "no path between "+s+" "+d ;
    if (p.pre == null)
    {
      System.out.println("NO POSSIBLE PATH between: " + s + " and " + d);
      return "NO POSSIBLE PATH between: " + s + " and " + d;
    }
    
    
    
    ArrayDeque<String> stack = new ArrayDeque<>();
    ArrayList<String> allwords=new ArrayList<>();

    while (p.pre != null)
    {
      stack.push(p.str);
      p = p.pre;

    }
    stack.push(p.str);
    while (stack.size() > 0)
    {
      String word=stack.pop();
      allwords.add(word);
      
    }
    StringBuilder sb=new StringBuilder();
    for(int i=0;i<allwords.size();i++)
    {
    	sb.append(allwords.get(i));
    	if(i!=allwords.size()-1)
    	sb.append(" -> ");
    }
    
    return sb.toString();
    
  }
  

  /**
   * Entry point for the program.
   */

  public static void main(String[] args)
  {
    // TODO Auto-generated method stub
    if (args.length < 3)
    {
      System.out.println("Input size must be at least 3");
      return;
    }

    Graph g = new Graph(args[0]);
    for (int i = 1; i < args.length - 1; i = i + 2)
    {
      g.findPath(args[i], args[i + 1]);
      g.printPath(args[i], args[i + 1]);
    }

    if (args.length % 2 == 0)
      System.out.println("Input words must be in pair!");

  }

}
