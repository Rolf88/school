/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aalgorithmvisualisation;

import aalgorithm.Node;
import app2dapi.geometry.G2D;
import app2dapi.geometry.G2D.Transformation2D;
import app2dapi.graphics.Canvas;
import app2dapi.graphics.ColorFactory;

/**
 *
 * @author RolfMoikjær
 */
public class TileDrawer {

    private final G2D g2d;
    private final ColorFactory cf;

    public TileDrawer(G2D g2d, ColorFactory cf) {
        this.g2d = g2d;
        this.cf = cf;
    }

    public void draw(Canvas canvas, Tile tile) {
        Transformation2D parent = canvas.getTransformation();
        Transformation2D t = g2d.translate(tile.getxPos(), tile.getyPos());
        Transformation2D c = g2d.combine(parent, t);
        canvas.setTransformation(c);

        //The actual drawing
        canvas.setColor(cf.getBlack());
        canvas.drawFilledRectangle(g2d.newPoint2D(0.5, 0.5), 1, 1);
        canvas.setColor(cf.getWhite());
        canvas.drawFilledRectangle(g2d.newPoint2D(0.5, 0.5), 0.95, 0.95);
        canvas.setColor(cf.getBlue());
        canvas.drawText(g2d.newPoint2D(0.5, 0.5), Integer.toString(tile.getValue()), 0.5, true, true);

        canvas.setTransformation(parent);
    }

    public void drawPath(Canvas canvas, Node node) {
        Transformation2D parent = canvas.getTransformation();
        Transformation2D t = g2d.translate(node.getXPos(), node.getYPos());
        Transformation2D c = g2d.combine(parent, t);
        canvas.setTransformation(c);

        //The actual drawing
        canvas.setColor(cf.getRed());
        canvas.drawFilledRectangle(g2d.newPoint2D(0.5, 0.5), 1, 1);

        canvas.setTransformation(parent);
    }
}
