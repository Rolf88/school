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
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 *
 * @author RolfMoikjær
 */
public class RolfKasperAI implements IAntAI {

    private final Random rnd = new Random();
    private Queue<EAction> acs = new PriorityQueue();
    private int turns;
    private int eggs;
    private MyMovement myM = new MyMovement();

    @Override
    public EAction chooseAction(IAntInfo thisAnt, ILocationInfo thisLocation, List<ILocationInfo> visibleLocations, List<EAction> possibleActions) {
        EAction action = null;
        if (!acs.isEmpty()) {
            if (possibleActions.contains(acs.peek())) {
                action = acs.poll();
            } else {
                action = EAction.Pass;
            }
        } else if (eggs == 1) {
            acs.add(EAction.TurnRight);
            acs.add(EAction.MoveForward);
            eggs = -1;

            if (!acs.isEmpty()) {
                if (possibleActions.contains(acs.peek())) {
                    action = acs.poll();
                } else {
                    action = EAction.Pass;
                }
            }
        } else if (possibleActions.contains(EAction.EatFood) && thisAnt.getHitPoints() < 25) {
            action = EAction.EatFood;
        } else if (possibleActions.contains(EAction.LayEgg)) {
            action = EAction.LayEgg;
        } else if (possibleActions.contains(EAction.PickUpFood) && thisAnt.getFoodLoad() < 5) {
            action = EAction.PickUpFood;
        } else if (thisAnt.getFoodLoad() >= 5) {
            if (!visibleLocations.isEmpty() && (visibleLocations.get(0).isFilled() || visibleLocations.get(0).isRock() && possibleActions.contains(EAction.TurnLeft))) {
                action = EAction.TurnLeft;
            } else if (visibleLocations.isEmpty() && possibleActions.contains(EAction.TurnLeft)) {
                action = EAction.TurnLeft;
            } else {
                action = EAction.Pass;
            }
        } else {
            action = possibleActions.get(rnd.nextInt(possibleActions.size()));
            if (action == EAction.Attack || action == EAction.EatFood || action == EAction.DropFood) {
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
        System.out.println("ID: " + thisAnt.antID() + " onStartTurn(" + turn + ")" + "eggs(" + eggs + ")");
        turns = turn;
    }

    @Override
    public void onLayEgg(IAntInfo thisAnt, List<EAntType> types, IEgg egg) {
        EAntType type = null;
        
        if (turns < 10) {
            type = EAntType.CARRIER;
            egg.set(type, new FirstCarrierAI());
            eggs = +1;
        } else {
            type = EAntType.WARRIOR;
            egg.set(type, new SimpleWarriorAI());
        }
        System.out.println("ID: " + thisAnt.antID() + " onLayEgg: " + type);

    }

    @Override
    public void onAttacked(IAntInfo thisAnt, int dir, IAntInfo attacker, int damage) {
        System.out.println("ID: " + thisAnt.antID() + " onAttacked: " + damage + " damage");

        if (attacker.getAntType() == EAntType.QUEEN) {
            wasAttacked(thisAnt, attacker);
        } else if (thisAnt.getAntType() != EAntType.WARRIOR) {
            getOut(thisAnt);
        } else {
            wasAttacked(thisAnt, attacker);
        }
    }

    private void wasAttacked(IAntInfo thisAnt, IAntInfo attacker) {
        System.out.println("Im attacking back!");
        EAction action = null;
        acs.clear();
        //If we are face to face - return fire
        if ((attacker.getDirection() == 0 && thisAnt.getDirection() == 2)
                || (attacker.getDirection() == 1 && thisAnt.getDirection() == 3)
                || (attacker.getDirection() == 2 && thisAnt.getDirection() == 0)
                || (attacker.getDirection() == 3 && thisAnt.getDirection() == 1)) {
            action = EAction.Attack;
            acs.add(action);

            //Attacked from Right/East
        } else if (thisAnt.getDirection() == 0 && attacker.getDirection() == 3) {
            action = EAction.TurnLeft;
            acs.add(action);
        } else if (thisAnt.getDirection() == 2 && attacker.getDirection() == 3) {
            action = EAction.TurnRight;
            acs.add(action);
        } else if (thisAnt.getDirection() == 3 && attacker.getDirection() == 3) {
            action = EAction.TurnLeft;
            acs.add(action);
            acs.add(action);

            //Attacked from Top/North
        } else if (thisAnt.getDirection() == 1 && attacker.getDirection() == 2) {
            action = EAction.TurnRight;
            acs.add(action);
        } else if (thisAnt.getDirection() == 2 && attacker.getDirection() == 2) {
            action = EAction.TurnLeft;
            acs.add(action);
            acs.add(action);
        } else if (thisAnt.getDirection() == 3 && attacker.getDirection() == 2) {
            action = EAction.TurnLeft;
            acs.add(action);

            //Attacked from Left/West
        } else if (thisAnt.getDirection() == 0 && attacker.getDirection() == 1) {
            action = EAction.TurnRight;
            acs.add(action);
        } else if (thisAnt.getDirection() == 1 && attacker.getDirection() == 1) {
            action = EAction.TurnLeft;
            acs.add(action);
            acs.add(action);
        } else if (thisAnt.getDirection() == 2 && attacker.getDirection() == 1) {
            action = EAction.TurnLeft;
            acs.add(action);

            //Attacked from Bottom/South
        } else if (thisAnt.getDirection() == 0 && attacker.getDirection() == 0) {
            action = EAction.TurnLeft;
            acs.add(action);
            acs.add(action);
        } else if (thisAnt.getDirection() == 1 && attacker.getDirection() == 0) {
            action = EAction.TurnLeft;
            acs.add(action);
        } else if (thisAnt.getDirection() == 3 && attacker.getDirection() == 0) {
            action = EAction.TurnLeft;
            acs.add(action);
        }
    }

    private void getOut(IAntInfo thisAnt) {
        System.out.println("Im running away from battle!");
        EAction action = null;
        acs.clear();
        action = EAction.MoveBackward;
        //i was attacked, and need to get out ASAP
        acs.add(action);
    }

    @Override
    public void onDeath(IAntInfo thisAnt) {
        System.out.println("ID: " + thisAnt.antID() + " for Team: " + thisAnt.getTeamInfo().getTeamID() + " onDeath");
    }

}
