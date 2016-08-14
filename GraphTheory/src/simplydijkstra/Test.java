/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplydijkstra;

/**
 *
 * @author Tobias Grundtvig
 */
public class Test
{
    public static void main(String[] args)
    {
        Graph graph = new Graph();
        Node a = graph.createNode("a");
        Node b = graph.createNode("b");
        Node c = graph.createNode("c");
        Node d = graph.createNode("d");
        Node e = graph.createNode("e");
        Node f = graph.createNode("f");
        Node g = graph.createNode("g");
        Node h = graph.createNode("h");
        
        graph.createEdge(a, b, 3);
        graph.createEdge(a, c, 5);
        graph.createEdge(a, d, 4);
        graph.createEdge(b, e, 10);
        graph.createEdge(b, c, 6);
        graph.createEdge(c, e, 8);
        graph.createEdge(c, d, 12);
        graph.createEdge(d, c, 10);
        graph.createEdge(d, a, 5);
        graph.createEdge(d, e, 11);
        graph.createEdge(d, f, 8);
        graph.createEdge(e, g, 5);
        graph.createEdge(e, b, 7);
        graph.createEdge(f, e, 7);
        graph.createEdge(f, g, 6);
        graph.createEdge(g, h, 10);
        
        DijkstrasAlgorithm algo = new DijkstrasAlgorithm();
        Iterable<Node> path = algo.findShortestPath(graph, a, h);
        for(Node n : path)
        {
            System.out.println(n + " dist " + n.getCost());
        }
    }
    
}
