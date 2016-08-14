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
public interface IGraph
{
    public <E> Iterable<INode<E>> getNodes();
}
