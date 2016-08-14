/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphtheory;

import java.util.ArrayList;

/**
 *
 * @author Tobias Grundtvig
 * @param <E>
 */
public class SimpleGraph<E> implements IGraph
{
    private final ArrayList<INode<E>> nodes;

    public SimpleGraph()
    {
        nodes = new ArrayList<>();
    }
    
    public void addNode(INode<E> node)
    {
        nodes.add(node);
    }
    
    @Override
    public Iterable<INode<E>> getNodes()
    {
        return nodes;
    }
    
}
