/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphtheory;

import graphtheory.DijkstrasAlgorithm.DijkstraNode;

/**
 *
 * @author Tobias Grundtvig
 */
public class GraphTheory
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        SimpleGraph graph = new SimpleGraph();
        SimpleNode a = new SimpleNode("A");
        graph.addNode(a);
        SimpleNode b = new SimpleNode("B");
        graph.addNode(b);
        SimpleNode c = new SimpleNode("C");
        graph.addNode(c);
        SimpleNode d = new SimpleNode("D");
        graph.addNode(d);
        SimpleNode e = new SimpleNode("E");
        graph.addNode(e);
        SimpleNode f = new SimpleNode("F");
        graph.addNode(f);
        SimpleNode g = new SimpleNode("G");
        graph.addNode(g);
        SimpleNode h = new SimpleNode("H");
        graph.addNode(h);
        
        SimpleEdge edge;
        
        edge = new SimpleEdge(a, b, 3);
        edge = new SimpleEdge(a, c, 5);
        edge = new SimpleEdge(a, d, 4);
        edge = new SimpleEdge(b, e, 10);
        edge = new SimpleEdge(b, c, 6);
        edge = new SimpleEdge(c, e, 8);
        edge = new SimpleEdge(c, d, 12);
        edge = new SimpleEdge(d, c, 10);
        edge = new SimpleEdge(d, a, 5);
        edge = new SimpleEdge(d, e, 11);
        edge = new SimpleEdge(d, f, 8);
        edge = new SimpleEdge(e, g, 5);
        edge = new SimpleEdge(e, b, 7);
        edge = new SimpleEdge(f, e, 7);
        edge = new SimpleEdge(f, g, 25);
        edge = new SimpleEdge(g, h, 10);
        
        IShortestPathProblem myProblem = new IShortestPathProblem()
        {
            @Override
            public IGraph getGraph()
            {
                return graph;
            }

            @Override
            public INode getStartNode()
            {
                return a;
            }

            @Override
            public INode getEndNode()
            {
                return h;
            }
        };
        
        
        DijkstrasAlgorithm algo = new DijkstrasAlgorithm();
        Iterable<INode<DijkstraNode>> res = algo.solve(myProblem);
        for(INode<DijkstraNode> node : res)
        {
            System.out.println(node);
        }
    }
    
}
