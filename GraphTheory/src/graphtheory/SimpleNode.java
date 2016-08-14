/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphtheory;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Tobias Grundtvig
 * @param <E>
 */
public class SimpleNode<E> implements INode<E>
{
    private String name;
    private E payload;
    private ArrayList<IEdge<E>> edges;

    public SimpleNode(String name)
    {
        this.name = name;
        edges = new ArrayList<>();
    }
    
    

    @Override
    public E getPayload()
    {
        return payload;
    }

    @Override
    public void setPayload(E payload)
    {
        this.payload = payload;
    }

    @Override
    public Iterator<IEdge<E>> iterator()
    {
        return edges.iterator();
    }
    
    @Override
    public String toString()
    {
        return name;
    }
    
    public void addEdge(IEdge<E> edge)
    {
        edges.add(edge);
    }
    
}
