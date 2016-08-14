/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myantwars;

import aiantwars.EAction;
import aiantwars.EAntType;
import aiantwars.IAntAI;
import aiantwars.IAntInfo;
import aiantwars.IEgg;
import aiantwars.ILocationInfo;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 *
 * @author RolfMoikjær
 */
public class MainCarrierAI implements IAntAI {

    private final Random rnd = new Random();
    private final Queue<EAction> acs = new PriorityQueue();
    private int turns;
    private final MyMovement myM = new MyMovement();

    @Override
    public EAction chooseAction(IAntInfo thisAnt, ILocationInfo thisLocation, List<ILocationInfo> visibleLocations, List<EAction> possibleActions) {
        EAction action = null;
        if (!visibleLocations.isEmpty() && (visibleLocations.get(0).isFilled() || visibleLocations.get(0).isRock())) {
            myM.findObstacles(visibleLocations);
            if (thisAnt.getFoodLoad() >= 10) {
                acs.clear();
                acs.addAll(myM.moving(thisAnt, 0, 0));
            }

        }

        if (!acs.isEmpty()) {
            if (possibleActions.contains(acs.peek())) {
                action = acs.poll();
            } else {
                action = EAction.Pass;
            }
        } else if (thisAnt.getLocation().getX() == 0 && thisAnt.getLocation().getY() == 0 && possibleActions.contains(EAction.DropFood)) {

            action = EAction.DropFood;

        } else if (possibleActions.contains(EAction.EatFood) && thisAnt.getHitPoints() < 10) {
            action = EAction.EatFood;
        } else if (thisAnt.getFoodLoad() >= 10) {
            if (!acs.isEmpty()) {

                if (possibleActions.contains(acs.peek())) {
                    action = acs.poll();
                } else {
                    action = EAction.Pass;
                }

            } else if (!(thisAnt.getLocation().getX() == 0 && thisAnt.getLocation().getY() == 0)) {
                acs.addAll(myM.moving(thisAnt, 0, 0));
                action = EAction.Pass;
            } else {
                action = EAction.Pass;
            }
        } else if (thisAnt.getFoodLoad() == 30) {
            if (thisAnt.getLocation().getX() == 0 && thisAnt.getLocation().getY() == 1 && thisAnt.getFoodLoad() > 0) {

                action = EAction.DropFood;
            } else if (!acs.isEmpty()) {

                if (possibleActions.contains(acs.peek())) {
                    action = acs.poll();
                } else {
                    action = EAction.Pass;
                }

            } else {
                action = EAction.Pass;
            }
        } else if (possibleActions.contains(EAction.PickUpFood) && !(thisAnt.getLocation().getX() == 0 && thisAnt.getLocation().getY() == 0)) {
            action = EAction.PickUpFood;
        } else if (possibleActions.contains(EAction.DigOut)) {
            action = EAction.DigOut;
        } else if (possibleActions.contains(EAction.Attack) && visibleLocations.get(0).getAnt().getTeamInfo().getTeamID() != thisAnt.getTeamInfo().getTeamID()) {
            action = EAction.Attack;
        } else {
            action = possibleActions.get(rnd.nextInt(possibleActions.size()));
            if (action == EAction.PickUpFood || action == EAction.Attack || action == EAction.DropFood || action == EAction.DropSoil || action == EAction.EatFood) {
                action = EAction.Pass;
            }
        }
        StringBuilder actions = new StringBuilder();
        for (EAction a : possibleActions) {
            actions.append(a.toString());
            actions.append(", ");
        }
        System.out.println(actions.toString());
        System.out.println("ID: " + thisAnt.antID() + " chooseAction: " + action);
        return action;
    }

    @Override
    public void onHatch(IAntInfo thisAnt, ILocationInfo thisLocation, int worldSizeX, int worldSizeY) {
        System.out.println("ID: " + thisAnt.antID() + " onHatch for: " + thisAnt.getTeamInfo().getTeamID());
    }

    @Override
    public void onStartTurn(IAntInfo thisAnt, int turn) {
        System.out.println("ID: " + thisAnt.antID() + " onStartTurn(" + turn + ")");
        turns = turn;
    }

    @Override
    public void onLayEgg(IAntInfo thisAnt, List<EAntType> types, IEgg egg) {
        EAntType type = null;
        System.out.println("ID: " + thisAnt.antID() + " onLayEgg: " + type);
        egg.set(type, this);
    }

    @Override
    public void onAttacked(IAntInfo thisAnt, int dir, IAntInfo attacker, int damage) {
        System.out.println("ID: " + thisAnt.antID() + " onAttacked: " + damage + " damage");

    }

    @Override
    public void onDeath(IAntInfo thisAnt) {
        System.out.println("ID: " + thisAnt.antID() + " for Team: " + thisAnt.getTeamInfo().getTeamID() + " onDeath");
    }

}
