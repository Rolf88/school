/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aalgorithmvisualisation;

import app2dapi.Platform;
import app2dapi.panandzoom2dapp.PanAndZoom2DApp;
import app2dapi.panandzoom2dapp.PanAndZoomAdapter;
import app2dpcimpl.PCPlatformImpl;

/**
 *
 * @author RolfMoikjær
 */
public class TestAAlgorithm {

    public static void main(String[] args) {
        PanAndZoom2DApp alg = new AAlgorithmVisualisation();
        Platform pc = new PCPlatformImpl();
        pc.runApplication(new PanAndZoomAdapter(alg));

    }
}
