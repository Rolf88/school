/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplydijkstra;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Tobias Grundtvig
 */
public class Node implements Iterable<Edge>, Comparable<Node>
{

    private final String name;
    private Node prev;
    private double cost;
    private final ArrayList<Edge> edges;

    public Node(String name)
    {
        this.name = name;
        prev = null;
        cost = Double.POSITIVE_INFINITY;
        edges = new ArrayList<>();
    }

    public void addEdge(Edge edge)
    {
        edges.add(edge);
    }

    public Node getPrev()
    {
        return prev;
    }

    public void setPrev(Node prev)
    {
        this.prev = prev;
    }

    public double getCost()
    {
        return cost;
    }

    public void setCost(double cost)
    {
        this.cost = cost;
    }

    @Override
    public String toString()
    {
        return name;
    }

    @Override
    public Iterator<Edge> iterator()
    {
        return edges.iterator();
    }

    @Override
    public int compareTo(Node o)
    {
        if(this.cost < o.cost)
        {
            return -1;
        }
        if(this.cost > o.cost)
        {
            return 1;
        }
        return 0;
    }

}
