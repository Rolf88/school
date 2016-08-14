/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphtheory;

/**
 *
 * @author Tobias Grundtvig
 * @param <E>
 */
public interface IEdge<E>
{
    public double getWeight();
    public INode<E> getBegin();
    public INode<E> getEnd();
}
