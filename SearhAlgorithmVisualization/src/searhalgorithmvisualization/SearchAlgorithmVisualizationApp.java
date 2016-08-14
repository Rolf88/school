package searhalgorithmvisualization;


import app2dapi.Platform;
import app2dapi.geometry.G2D;
import app2dapi.graphics.Canvas;
import app2dapi.graphics.Color;
import app2dapi.graphics.ColorFactory;
import app2dapi.input.charinput.CharInputEvent;
import app2dapi.input.keyboard.KeyPressedEvent;
import app2dapi.input.keyboard.KeyReleasedEvent;
import app2dapi.panandzoom2dapp.PanAndZoom2DApp;
import app2dapi.panandzoom2dapp.PanAndZoomAdapter;
import app2dapi.panandzoom2dapp.PanAndZoomInit;
import app2dapi.panandzoom2dapp.PanAndZoomToolKit;
import app2dpcimpl.PCPlatformImpl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tobias Grundtvig
 */
public class SearchAlgorithmVisualizationApp implements PanAndZoom2DApp
{
    private double hudHeight;
    private double hudWidth;
    private int worldWidth = 20;
    private int worldHeight = 10;
    private ColorFactory cf;
    private G2D g2d;
    private Tile[][] tiles;
    private TileDrawer tileDrawer;
    
    @Override
    public PanAndZoomInit initialize(PanAndZoomToolKit tk, double aspectRatio)
    {
        
        tiles = new Tile[worldWidth][worldHeight];
        for(int y = 0; y < worldHeight; ++y)
        {
            for(int x = 0; x < worldWidth; ++x)
            {
                tiles[x][y] = new Tile(x, y, x+y);
            }
        }
        this.hudHeight = 1000;
        this.hudWidth = hudHeight * aspectRatio;
        this.cf = tk.cf();
        this.g2d = tk.g2d();
        tileDrawer = new TileDrawer(g2d, cf);
        return new PanAndZoomInit(  g2d.origo(),
                                    g2d.newPoint2D(hudWidth, hudHeight),
                                    g2d.origo(),
                                    g2d.newPoint2D(worldWidth, worldHeight),
                                    g2d.newPoint2D(worldWidth*0.5, worldHeight*0.5),
                                    worldWidth, 1, worldWidth);
    }

    @Override
    public boolean showMouseCursor()
    {
        return true;
    }

    @Override
    public void onMouseMoved(G2D.Point2D mouseHUDPos, G2D.Point2D mouseWorldPos)
    {
        
    }

    @Override
    public void onMousePressed(G2D.Point2D mouseHUDPos, G2D.Point2D mouseWorldPos)
    {
        
    }

    @Override
    public void onMouseReleased(G2D.Point2D mouseHUDPos, G2D.Point2D mouseWorldPos)
    {
        
    }

    @Override
    public void onKeyPressed(KeyPressedEvent e)
    {
        
    }

    @Override
    public void onKeyReleased(KeyReleasedEvent e)
    {
        
    }

    @Override
    public void onCharInput(CharInputEvent event)
    {
        
    }

    @Override
    public boolean update(double time)
    {
        return true;
    }

    @Override
    public Color getBackgroundColor()
    {
        return cf.getGreen();
    }

    @Override
    public void drawWorld(Canvas canvas)
    {
        for(int y = 0; y < worldHeight; ++y)
        {
            for(int x = 0; x < worldWidth; ++x)
            {
                tileDrawer.draw(canvas, tiles[x][y]);
            }
        }
    }

    @Override
    public void drawHUD(Canvas canvas)
    {
        
    }

    @Override
    public void destroy()
    {
        
    }
    
    public static void main(String[] args)
    {
        PanAndZoom2DApp app = new SearchAlgorithmVisualizationApp();
        Platform pc = new PCPlatformImpl();
        pc.runApplication(new PanAndZoomAdapter(app));
    }
    
}
