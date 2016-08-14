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
public interface IShortestPathProblem
{
    public IGraph getGraph();
    public INode getStartNode();
    public INode getEndNode();
}
