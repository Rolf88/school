/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplydijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Tobias Grundtvig
 */
public class DijkstrasAlgorithm
{
    public Iterable<Node> findShortestPath(Graph graph, Node start, Node goal)
    {
        start.setCost(0);
        Queue<Node> unvisited = new PriorityQueue<>();
        for(Node node : graph.getNodes())
        {
            unvisited.add(node);
        }
        Node currentNode = start;
        while(true)
        {
            for(Edge edge : currentNode)
            {
                Node endOfEdge = edge.getEnd();
                if(unvisited.contains(endOfEdge))
                {
                    double newCost = currentNode.getCost() + edge.getWeight();
                    if(newCost < endOfEdge.getCost())
                    {
                        endOfEdge.setCost(newCost);
                        endOfEdge.setPrev(currentNode);
                    }
                }
            }
            unvisited.remove(currentNode);
            if(currentNode == goal)
            {
                ArrayList<Node> res = new ArrayList<>();
                do
                {
                   res.add(currentNode);
                   currentNode = currentNode.getPrev();
                } while(currentNode != null);
                Collections.reverse(res);
                return res;
            }
            else if(unvisited.isEmpty())
            {
                return null;
            }
            currentNode = unvisited.poll();
            if(currentNode.getCost() == Double.POSITIVE_INFINITY)
            {
                return null;
            }
        }        
    }
}
