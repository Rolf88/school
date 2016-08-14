/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myantwars;

import AAlgorithm.Graph;
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
public class MySimpleLogic implements IAntAI {

    private final Random rnd = new Random();
    private Queue<EAction> acs = new PriorityQueue();
    private int turns;
    private List<IAntInfo> teamList = new ArrayList();
    private MyMovement myM = new MyMovement();

    @Override
    public EAction chooseAction(IAntInfo thisAnt, ILocationInfo thisLocation, List<ILocationInfo> visibleLocations, List<EAction> possibleActions) {
        EAction action = null;

        if (!visibleLocations.isEmpty() && (visibleLocations.get(0).isFilled() || visibleLocations.get(0).isRock())) {
            myM.findObstacles(visibleLocations);
        }

        switch (thisAnt.getAntType().getTypeName()) {
            case "Queen":
                action = QueenAi(thisAnt, thisLocation, visibleLocations, possibleActions);
                break;
            case "Carrier":
                action = CarrierAi(thisAnt, thisLocation, visibleLocations, possibleActions);
                break;
            case "Scout":
                action = ScoutAi(thisAnt, thisLocation, visibleLocations, possibleActions);
                break;
            case "Warrier":
                action = WarriorAi(thisAnt, thisLocation, visibleLocations, possibleActions);
                break;
        }

        return action;
    }

    @Override
    public void onStartTurn(IAntInfo thisAnt, int turn) {
        System.out.println("ID: " + thisAnt.antID() + " onStartTurn(" + turn + ")");
        turns = turn;
    }

    @Override
    public void onAttacked(IAntInfo thisAnt, int dir, IAntInfo attacker, int damage) {
        EAntType type = null;
        System.out.println("ID: " + thisAnt.antID() + " onAttacked: " + damage + " damage");
    }

    @Override
    public void onDeath(IAntInfo thisAnt) {
        System.out.println("ID: " + thisAnt.antID() + " for Team: " + thisAnt.getTeamInfo().getTeamID() + " onDeath");
        teamList.remove(thisAnt);
    }

    @Override
    public void onLayEgg(IAntInfo thisAnt, List<EAntType> types, IEgg egg) {
        EAntType type = null;
        if (teamList.size() < 2) {
            type = EAntType.CARRIER;
        } else {
            type = EAntType.WARRIOR;
        }
        System.out.println("ID: " + thisAnt.antID() + " onLayEgg: " + type);
        egg.set(type, this);
    }

    @Override
    public void onHatch(IAntInfo thisAnt, ILocationInfo thisLocation, int worldSizeX, int worldSizeY) {
        System.out.println("ID: " + thisAnt.antID() + " onHatch for: " + thisAnt.getTeamInfo().getTeamID());
        teamList.add(thisAnt);
    }

    private EAction QueenAi(IAntInfo thisAnt, ILocationInfo thisLocation, List<ILocationInfo> visibleLocations, List<EAction> possibleActions) {
        EAction action = null;

        if (!acs.isEmpty()) {
            if (possibleActions.contains(acs.peek())) {
                action = acs.poll();
            } else {
                acs.clear();
                acs.addAll(myM.moving(thisAnt, 0, 0));
                action = acs.poll();
            }
        } else if (possibleActions.contains(EAction.LayEgg)) {
            action = EAction.LayEgg;
        } else if (possibleActions.contains(EAction.EatFood) && thisAnt.getHitPoints() < 10) {
            action = EAction.EatFood;
        } else if (possibleActions.contains(EAction.Attack) && visibleLocations.get(0).getAnt().getTeamInfo().getTeamID() != thisAnt.getTeamInfo().getTeamID()) {
            action = EAction.Attack;
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

    private EAction CarrierAi(IAntInfo thisAnt, ILocationInfo thisLocation, List<ILocationInfo> visibleLocations, List<EAction> possibleActions) {
        EAction action = null;

        if (!acs.isEmpty()) {
            if (possibleActions.contains(acs.peek())) {
                action = acs.poll();
            } else {
                acs.clear();
                acs.addAll(myM.moving(thisAnt, 0, 0));
                action = acs.poll();
            }
        } else if (thisAnt.getLocation().getX() == 0 && thisAnt.getLocation().getY() == 0 && possibleActions.contains(EAction.DropFood)) {

            action = EAction.DropFood;

        } else if (possibleActions.contains(EAction.EatFood) && thisAnt.getHitPoints() < 10) {
            action = EAction.EatFood;
        } else if (thisAnt.getFoodLoad() >= 10 && turns <= 25) {
            if (!acs.isEmpty()) {

                if (possibleActions.contains(acs.peek())) {
                    action = acs.poll();
                } else {
                    acs.clear();
                    acs.addAll(myM.moving(thisAnt, 0, 0));
                    action = acs.poll();
                }

            } else {
                acs.addAll(myM.moving(thisAnt, 0, 0));
                action = EAction.Pass;
            }
        } else if (thisAnt.getFoodLoad() == 30) {
            if (thisAnt.getLocation().getX() == 0 && thisAnt.getLocation().getY() == 1 && thisAnt.getFoodLoad() > 0) {

                action = EAction.DropFood;
            } else if (!acs.isEmpty()) {

                if (possibleActions.contains(acs.peek())) {
                    action = acs.poll();
                } else {
                    acs.clear();
                    acs.addAll(myM.moving(thisAnt, 0, 0));
                    action = acs.poll();
                }

            } else {
                acs.addAll(myM.moving(thisAnt, 0, 0));
                action = EAction.Pass;
            }
        } else if (possibleActions.contains(EAction.PickUpFood) && thisAnt.getFoodLoad() < 30 && thisAnt.getLocation().getX() != 0 && thisAnt.getLocation().getY() != 0) {
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

    private EAction ScoutAi(IAntInfo thisAnt, ILocationInfo thisLocation, List<ILocationInfo> visibleLocations, List<EAction> possibleActions) {
        EAction action = null;

        if (possibleActions.contains(EAction.LayEgg)) {
            action = EAction.LayEgg;
        } else if (possibleActions.contains(EAction.EatFood) && thisAnt.getHitPoints() < 10) {
            action = EAction.EatFood;
        } else if (possibleActions.contains(EAction.Attack) && visibleLocations.get(0).getAnt().getTeamInfo().getTeamID() != thisAnt.getTeamInfo().getTeamID()) {
            action = EAction.Attack;
        } else if (possibleActions.contains(EAction.PickUpFood) && thisAnt.getFoodLoad() < 15) {
            action = EAction.PickUpFood;
        } else {
            action = possibleActions.get(rnd.nextInt(possibleActions.size()));
            if (action == EAction.Attack || action == EAction.EatFood) {
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

    private EAction WarriorAi(IAntInfo thisAnt, ILocationInfo thisLocation, List<ILocationInfo> visibleLocations, List<EAction> possibleActions) {
        EAction action = null;

        if (possibleActions.contains(EAction.EatFood) && thisAnt.getHitPoints() < 10) {
            action = EAction.EatFood;
        } else if (possibleActions.contains(EAction.Attack) && visibleLocations.get(0).getAnt().getTeamInfo().getTeamID() != thisAnt.getTeamInfo().getTeamID()) {
            action = EAction.Attack;
        } else if (possibleActions.contains(EAction.PickUpFood) && thisAnt.getFoodLoad() < 15) {
            action = EAction.PickUpFood;
        } else {
            action = possibleActions.get(rnd.nextInt(possibleActions.size()));
            if (action == EAction.DropFood || action == EAction.EatFood) {
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

}
