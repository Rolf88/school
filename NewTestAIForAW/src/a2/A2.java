/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2;

import aiantwars.IAntAI;
import tournament.player.PlayerFactory;

/**
 *
 * @author RolfMoikjær
 */
public class A2 implements PlayerFactory<IAntAI> {

    @Override
    public IAntAI getNewInstance() {
        return new A2QueenAI();
    }

    @Override
    public String getID() {
        return "A2";
    }

    @Override
    public String getName() {
        return "Ice Cold Ninjas";
    }

}