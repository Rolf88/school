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
public class MainWarriorAI implements IAntAI {

    private final Random rnd = new Random();

    private int turns;
    private int count;
    private MyMovement myM = new MyMovement();
    private Queue<EAction> acs = new PriorityQueue();
    private int moveX = 14;
    private int moveY = 0;

    @Override
    public EAction chooseAction(IAntInfo thisAnt, ILocationInfo thisLocation, List<ILocationInfo> visibleLocations, List<EAction> possibleActions) {
        EAction action = null;

        if (!visibleLocations.isEmpty() && (visibleLocations.get(0).isFilled() || visibleLocations.get(0).isRock())) {
            myM.findObstacles(visibleLocations);
            if (!acs.isEmpty()) {
                acs.clear();
                acs.addAll(myM.moving(thisAnt, moveX, moveY));
            }
        }

        if (possibleActions.contains(EAction.Attack) && visibleLocations.get(0).getAnt().getTeamInfo().getTeamID() != thisAnt.getTeamInfo().getTeamID()) {
            action = EAction.Attack;
        } else if (thisAnt.getLocation().getX() == 14 && thisAnt.getLocation().getY() == 0) {
            moveX = 14;
            moveY = 14;
            acs.clear();
            acs.addAll(myM.moving(thisAnt, moveX, moveY));
            if (possibleActions.contains(acs.peek())) {
                action = acs.poll();
            } else {
                count++;
                action = EAction.Pass;
            }

        } else if (thisAnt.getLocation().getX() == 14 && thisAnt.getLocation().getY() == 14) {
            moveX = 0;
            moveY = 14;
            acs.clear();
            acs.addAll(myM.moving(thisAnt, moveX, moveY));
            if (possibleActions.contains(acs.peek())) {
                action = acs.poll();
            } else {
                count++;
                action = EAction.Pass;
            }

        } else if (acs.isEmpty()) {
            acs.addAll(myM.moving(thisAnt, moveX, moveY));
            if (possibleActions.contains(acs.peek())) {
                action = acs.poll();
            } else {
                count++;
                action = EAction.Pass;
            }
        } else if (!acs.isEmpty()) {
            if (possibleActions.contains(acs.peek())) {
                action = acs.poll();
            } else {
                count++;
                action = EAction.Pass;
            }
        } else {
            if (!acs.isEmpty()) {
                if (possibleActions.contains(acs.peek())) {
                    action = acs.poll();
                } else {
                    count++;
                    action = EAction.Pass;
                }
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
