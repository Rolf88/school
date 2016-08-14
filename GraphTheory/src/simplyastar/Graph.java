/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplyastar;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Tobias Grundtvig
 */
public class Graph
{
   private final ArrayList<Node> nodes;
   private final ArrayList<Edge> edges;

    public Graph()
    {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
   
    public Iterable<Node> getNodes()
    {
        return Collections.unmodifiableList(nodes);
    }
    
    public Node createNode(String name, double xPos, double yPos)
    {
        Node res = new Node(name, xPos, yPos);
        nodes.add(res);
        return res;
    }
    
    public void createEdge(Node begin, Node end, double weight)
    {
       Edge edge = new Edge(begin, end, weight);
       edges.add(edge);
    }
}
