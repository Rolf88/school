/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphtheory;

/**
 *
 * @author Tobias Grundtvig
 */
public class SimpleEdge<E> implements IEdge<E>
{
    private final double weight;
    private final INode<E> begin;
    private final INode<E> end;

    public SimpleEdge(SimpleNode<E> begin, SimpleNode<E> end, double weight)
    {
        this.weight = weight;
        this.begin = begin;
        this.end = end;
        begin.addEdge(this);
    }
    
    @Override
    public double getWeight()
    {
        return weight;
    }

    @Override
    public INode<E> getBegin()
    {
        return begin;
    }

    @Override
    public INode<E> getEnd()
    {
        return end;
    }
    
}
