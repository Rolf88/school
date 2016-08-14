/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphtheory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Tobias Grundtvig
 */
public class DijkstrasAlgorithm implements IShortestPathAlogrithm
{

    @Override
    public Iterable<INode<DijkstraNode>> solve(IShortestPathProblem problem)
    {
        Queue<DijkstraNode> unvisited = new PriorityQueue<>();
        DijkstraNode start = new DijkstraNode(problem.getStartNode(), 0);
        start.node.setPayload(start);
        for(INode node : problem.getGraph().getNodes())
        {
            if(node != start.node)
            {
                DijkstraNode dnode = new DijkstraNode(node, Double.MAX_VALUE);
                node.setPayload(dnode);
                unvisited.add(dnode);
            }
        }
        DijkstraNode currentNode = start;
        while(true)
        {
            for(IEdge<DijkstraNode> edge : currentNode.getNode())
            {
                DijkstraNode endNode = edge.getEnd().getPayload();
                if(unvisited.contains(endNode))
                {
                    double newCost = currentNode.getCost() + edge.getWeight();
                    if(newCost < endNode.getCost())
                    {
                        endNode.setCost(newCost, currentNode.getNode());
                    }
                }
            }
            unvisited.remove(currentNode);
            if(currentNode.getNode() == problem.getEndNode())
            {
                System.out.println("Shortest path = " + currentNode.cost);
                ArrayList<INode<DijkstraNode>> res = new ArrayList<>();
                INode<DijkstraNode> cur = currentNode.getNode();
                res.add(cur);
                while(cur.getPayload().prev != null)
                {
                    cur = cur.getPayload().getPrev();
                    res.add(cur);
                }
                Collections.reverse(res);
                return res;
            }
            if(unvisited.isEmpty())
            {
                System.out.println("No goal found! No more unvisited nodes!");
                return null;
            }
            currentNode = unvisited.poll();
            if(currentNode.getCost() == Double.MAX_VALUE)
            {
                System.out.println("No goal found! Smallest node is infinity");
                return null;
            }
        }
    }

    public static class DijkstraNode implements Comparable<DijkstraNode>
    {
        private final INode node;
        private INode prev;
        private double cost;
        

        public DijkstraNode(INode node, double cost)
        {
            this.node = node;
            this.cost = cost;
            this.prev = null;
        }

        public double getCost()
        {
            return cost;
        }
        
        public INode getPrev()
        {
            return prev;
        }

        public void setCost(double cost, INode prev)
        {
            this.cost = cost;
            this.prev = prev;
        }

        public INode<DijkstraNode> getNode()
        {
            return node;
        }

        @Override
        public int compareTo(DijkstraNode o)
        {
            if(this.cost < o.cost)
            {
                return -1;
            }
            if(this.cost > o.cost)
            {
                return 1;
            }
            //this.cost == o.cost
            return 0;
        }

    }
}
