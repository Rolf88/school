/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searhalgorithmvisualization;

/**
 *
 * @author Tobias Grundtvig
 */
public class Tile
{
    private final int xPos;
    private final int yPos;
    private final int value;

    public Tile(int xPos, int yPos, int value)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.value = value;
    }

    public int getXPos()
    {
        return xPos;
    }

    public int getYPos()
    {
        return yPos;
    }

    public int getValue()
    {
        return value;
    }
    
    
}
