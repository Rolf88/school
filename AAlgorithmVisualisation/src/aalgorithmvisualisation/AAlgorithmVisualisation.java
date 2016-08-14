/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aalgorithmvisualisation;

import aalgorithm.Node;
import aalgorithm.Test;
import app2dapi.geometry.G2D;
import app2dapi.graphics.Canvas;
import app2dapi.graphics.Color;
import app2dapi.graphics.ColorFactory;
import app2dapi.input.charinput.CharInputEvent;
import app2dapi.input.keyboard.KeyPressedEvent;
import app2dapi.input.keyboard.KeyReleasedEvent;
import app2dapi.panandzoom2dapp.PanAndZoom2DApp;
import app2dapi.panandzoom2dapp.PanAndZoomInit;
import app2dapi.panandzoom2dapp.PanAndZoomToolKit;

/**
 *
 * @author RolfMoikjær
 */
public class AAlgorithmVisualisation implements PanAndZoom2DApp {

    private double hudHeight;
    private double hudWidth;
    private int worldWidth = 100;
    private int worldHeight = 100;
    private int viewWidth = 16;
    private int viewHeight = 16;
    private ColorFactory cf;
    private G2D g2d;
    private Tile[][] tiles;
    private TileDrawer tileDrawer;
    private Test astar;

    @Override
    public PanAndZoomInit initialize(PanAndZoomToolKit tk, double aspectRatio) {

        astar = new Test();

        tiles = new Tile[viewWidth][viewHeight];
        for (int y = 0; y < viewWidth; ++y) {

            for (int x = 0; x < viewHeight; x++) {
                tiles[x][y] = new Tile(x, y, x + y);
            }
        }
        this.hudHeight = 1000;
        this.hudWidth = hudHeight * aspectRatio;
        this.cf = tk.cf();
        this.g2d = tk.g2d();
        tileDrawer = new TileDrawer(g2d, cf);
        return new PanAndZoomInit(g2d.origo(),
                g2d.newPoint2D(hudWidth, hudHeight),
                g2d.origo(), g2d.newPoint2D(worldWidth, worldHeight),
                g2d.newPoint2D(worldWidth * 0.1, worldHeight * 0.1), worldWidth, 1, worldWidth);
    }

    @Override
    public boolean showMouseCursor() {
        return true;
    }

    @Override
    public void onMouseMoved(G2D.Point2D mouseHUDPos, G2D.Point2D mouseWorldPos) {
    }

    @Override
    public void onMousePressed(G2D.Point2D mouseHUDPos, G2D.Point2D mouseWorldPos) {

    }

    @Override
    public void onMouseReleased(G2D.Point2D mouseHUDPos, G2D.Point2D mouseWorldPos) {
    }

    @Override
    public void onKeyPressed(KeyPressedEvent e) {

    }

    @Override
    public void onKeyReleased(KeyReleasedEvent e) {
    }

    @Override
    public void onCharInput(CharInputEvent event) {
    }

    @Override
    public boolean update(double time) {
        return true;
    }

    @Override
    public Color getBackgroundColor() {
        return cf.getBlack();
    }

    @Override
    public void drawWorld(Canvas canvas) {
//        canvas.setColor(cf.getRed());
//        canvas.drawFilledRectangle(g2d.newPoint2D(50, 50), 100, 100);
//
//        canvas.setColor(cf.getBlue());
//        canvas.drawFilledRectangle(g2d.newPoint2D(50, 50), 50, 50);

        for (int y = 0; y < viewWidth; ++y) {
            for (int x = 0; x < viewHeight; ++x) {
                tileDrawer.draw(canvas, tiles[x][y]);
            }
        }

        for (Node node : astar.astarForViz()) {
            tileDrawer.drawPath(canvas, node);
        }
    }

    @Override
    public void drawHUD(Canvas canvas) {
    }

    @Override
    public void destroy() {
        //Close the app
    }

}
