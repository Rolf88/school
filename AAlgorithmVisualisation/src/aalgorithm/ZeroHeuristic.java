/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aalgorithm;

/**
 *
 * @author Tobias Grundtvig
 */
public class ZeroHeuristic implements IHeuristic
{

    @Override
    public double getMinimumDist(Node a, Node b)
    {
        return 0;
    }
    
}
