/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2;

import aiantwars.EAction;
import aiantwars.EAntType;
import aiantwars.IAntAI;
import aiantwars.IAntInfo;
import aiantwars.IEgg;
import aiantwars.ILocationInfo;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author RolfMoikjær
 */
public class A2CarrierAI implements IAntAI {

    private final Random rnd = new Random();
    private LinkedList<EAction> acs = new LinkedList();
    private int nodeNumberX = 32;
    private int nodeNumberY = 17;
    private Node[][] nodes = new Node[nodeNumberX][nodeNumberY];
    private List<Node> blockList = new ArrayList<>();
    private MyMovement myM = new MyMovement(nodes, blockList);
    private int putDownX;
    private int putDownY;
    private boolean startWest;
    private boolean antInfront;
    private int numbInTeam;

    public A2CarrierAI(int numbInTeam, boolean startWest) {
        this.startWest = startWest;
        this.numbInTeam = numbInTeam;
    }

    private EAction TeamNumbOne(IAntInfo thisAnt, ILocationInfo thisLocation, List<ILocationInfo> visibleLocations, List<EAction> possibleActions) {
        EAction action = null;

        if (startWest) {
            putDownX = 0;
            putDownY = 8;
        } else if (!startWest) {
            putDownX = 31;
            putDownY = 8;
        }

        if (antInfront) {
            if (possibleActions.contains(EAction.MoveForward)) {
                acs.add(0, EAction.MoveForward);
            } else if (possibleActions.contains(EAction.MoveBackward)) {
                acs.add(0, EAction.MoveBackward);
            }
            antInfront = false;
        }

        if (!visibleLocations.isEmpty() && (visibleLocations.get(0).isFilled() || visibleLocations.get(0).isRock())) {

            if (acs.isEmpty()) {
                if (thisAnt.getFoodLoad() >= 15 && !(thisAnt.getLocation().getX() == putDownX && thisAnt.getLocation().getY() == putDownY)) {
                    acs.addAll(myM.moving(thisAnt, putDownX, putDownY, visibleLocations));
                }
            } else {
                acs.clear();
                acs.addAll(myM.moving(thisAnt, putDownX, putDownY, visibleLocations));

            }

        }

        if (!acs.isEmpty()) {
            if (possibleActions.contains(acs.peek())) {
                action = acs.poll();
            } else if (!visibleLocations.isEmpty() && visibleLocations.get(0).getAnt() != null && visibleLocations.get(0).getAnt().getTeamInfo() == thisAnt.getTeamInfo() && possibleActions.contains(EAction.TurnLeft)) {
                antInfront = true;
                action = EAction.TurnLeft;
            } else {
                acs.clear();
                acs.addAll(myM.moving(thisAnt, putDownX, putDownY));
                action = acs.poll();
            }
        } else if (thisAnt.getLocation()
                .getX() == putDownX && thisAnt.getLocation().getY() == putDownY
                && possibleActions.contains(EAction.DropFood)) {

            action = EAction.DropFood;

        } else if (possibleActions.contains(EAction.EatFood)
                && thisAnt.getHitPoints() < 10) {
            action = EAction.EatFood;
        } else if (thisAnt.getFoodLoad()
                >= 15 && !(thisAnt.getLocation().getX() == putDownX
                && thisAnt.getLocation().getY() == putDownY)) {
            if (!acs.isEmpty()) {

                if (possibleActions.contains(acs.peek())) {
                    action = acs.poll();
                } else {
                    acs.clear();
                    acs.addAll(myM.moving(thisAnt, putDownX, putDownY));
                    action = EAction.Pass;
                }

            } else if (!(thisAnt.getLocation().getX() == putDownX && thisAnt.getLocation().getY() == putDownY)) {
                acs.addAll(myM.moving(thisAnt, putDownX, putDownY));
                action = EAction.Pass;
            } else {
                action = EAction.Pass;
            }
        } else if (possibleActions.contains(EAction.PickUpFood)
                && !(thisAnt.getLocation().getX() == putDownX && thisAnt.getLocation().getY() == putDownY)) {
            action = EAction.PickUpFood;
        } else if (possibleActions.contains(EAction.Attack)
                && visibleLocations.get(0).getAnt().getTeamInfo().getTeamID() != thisAnt.getTeamInfo().getTeamID()) {
            action = EAction.Attack;
        } else {
            action = possibleActions.get(rnd.nextInt(possibleActions.size()));
            if (action == EAction.DigOut || action == EAction.PickUpFood || action == EAction.Attack || action == EAction.DropFood || action == EAction.DropSoil || action == EAction.EatFood) {
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

    private EAction TeamNumbTwo(IAntInfo thisAnt, ILocationInfo thisLocation, List<ILocationInfo> visibleLocations, List<EAction> possibleActions) {
        EAction action = null;
        List<EAction> movements = new ArrayList();

        movements.add(EAction.MoveForward);
        movements.add(EAction.TurnLeft);
        movements.add(EAction.TurnRight);

        if (startWest) {
            putDownX = 1;
            putDownY = 8;
        } else if (!startWest) {
            putDownX = 30;
            putDownY = 8;
        }

        if (antInfront) {
            if (possibleActions.contains(EAction.MoveForward)) {
                acs.add(0, EAction.MoveForward);
            } else if (possibleActions.contains(EAction.MoveBackward)) {
                acs.add(0, EAction.MoveBackward);
            }
            antInfront = false;
        }

        if (!visibleLocations.isEmpty() && (visibleLocations.get(0).isFilled() || visibleLocations.get(0).isRock())) {

            if (acs.isEmpty()) {
                if (thisAnt.getFoodLoad() >= 15 && !(thisAnt.getLocation().getX() == putDownX && thisAnt.getLocation().getY() == putDownY)) {
                    acs.addAll(myM.moving(thisAnt, putDownX, putDownY, visibleLocations));
                }
            } else {
                acs.clear();
                acs.addAll(myM.moving(thisAnt, putDownX, putDownY, visibleLocations));

            }

        }

        if (!acs.isEmpty()) {
            if (possibleActions.contains(acs.peekFirst())) {
                acs.pollFirst();
            } else {
            }
        } else if (possibleActions.contains(EAction.PickUpFood)) {
            action = EAction.PickUpFood;
        } else if (possibleActions.contains(EAction.MoveForward)) {
            action = EAction.MoveForward;
        } else if (possibleActions.contains(EAction.TurnRight)) {
            action = EAction.TurnRight;
        } else if (thisAnt.getFoodLoad() >= 15) {
            if (acs.isEmpty()) {
                acs.addAll(myM.moving(thisAnt, putDownX, putDownY));
            } else {
                acs.clear();
                acs.addAll(myM.moving(thisAnt, putDownX, putDownY));
            }
        } else {
            action = EAction.Pass;
        }

        return action;
    }

    @Override
    public EAction chooseAction(IAntInfo thisAnt, ILocationInfo thisLocation, List<ILocationInfo> visibleLocations, List<EAction> possibleActions) {

        if (numbInTeam == 1) {
            return TeamNumbOne(thisAnt, thisLocation, visibleLocations, possibleActions);
        } else {
            return TeamNumbTwo(thisAnt, thisLocation, visibleLocations, possibleActions);
        }
    }

    @Override
    public void onStartTurn(IAntInfo thisAnt, int turn) {
        System.out.println("ID: " + thisAnt.antID() + " onStartTurn(" + turn + ")");
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

        if (attacker.getAntType() == EAntType.QUEEN) {
            wasAttacked(thisAnt, attacker);
        } else if (thisAnt.getAntType() != EAntType.WARRIOR) {
            getOut(thisAnt);
        } else {
            wasAttacked(thisAnt, attacker);
        }
    }

    private void wasAttacked(IAntInfo thisAnt, IAntInfo attacker) {
        System.out.println("****Im attacking back!**** " + thisAnt.getAntType().getTypeName());
        EAction action = null;
        acs.clear();
        //If we are face to face - return fire
        if ((attacker.getDirection() == 0 && thisAnt.getDirection() == 2)
                || (attacker.getDirection() == 1 && thisAnt.getDirection() == 3)
                || (attacker.getDirection() == 2 && thisAnt.getDirection() == 0)
                || (attacker.getDirection() == 3 && thisAnt.getDirection() == 1)) {
            action = EAction.Attack;
            acs.add(action);

            //If not face to face, turn in given direction, based on your current direction, and your attackers
            //Attacked from Right/East
        } else if (thisAnt.getDirection() == 0 && attacker.getDirection() == 3) {
            action = EAction.TurnRight;
            acs.add(action);
        } else if (thisAnt.getDirection() == 2 && attacker.getDirection() == 3) {
            action = EAction.TurnLeft;
            acs.add(action);
        } else if (thisAnt.getDirection() == 3 && attacker.getDirection() == 3) {
            action = EAction.TurnLeft;
            acs.add(action);
            acs.add(action);

            //Attacked from Top/North
        } else if (thisAnt.getDirection() == 1 && attacker.getDirection() == 2) {
            action = EAction.TurnLeft;
            acs.add(action);
        } else if (thisAnt.getDirection() == 2 && attacker.getDirection() == 2) {
            action = EAction.TurnLeft;
            acs.add(action);
            acs.add(action);
        } else if (thisAnt.getDirection() == 3 && attacker.getDirection() == 2) {
            action = EAction.TurnRight;
            acs.add(action);

            //Attacked from Left/West
        } else if (thisAnt.getDirection() == 0 && attacker.getDirection() == 1) {
            action = EAction.TurnLeft;
            acs.add(action);
        } else if (thisAnt.getDirection() == 1 && attacker.getDirection() == 1) {
            action = EAction.TurnLeft;
            acs.add(action);
            acs.add(action);
        } else if (thisAnt.getDirection() == 2 && attacker.getDirection() == 1) {
            action = EAction.TurnRight;
            acs.add(action);

            //Attacked from Bottom/South
        } else if (thisAnt.getDirection() == 0 && attacker.getDirection() == 0) {
            action = EAction.TurnLeft;
            acs.add(action);
            acs.add(action);
        } else if (thisAnt.getDirection() == 1 && attacker.getDirection() == 0) {
            action = EAction.TurnRight;
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

    @Override
    public void onStartMatch(int worldSizeX, int worldSizeY) {
    }

    @Override
    public void onStartRound(int round) {
    }

    @Override
    public void onEndRound(int yourMajor, int yourMinor, int enemyMajor, int enemyMinor) {
    }

    @Override
    public void onEndMatch(int yourScore, int yourWins, int enemyScore, int enemyWins) {
    }

    @Override
    public void onHatch(IAntInfo thisAnt, ILocationInfo thisLocation, int worldSizeX, int worldSizeY) {
        System.out.println("ID: " + thisAnt.antID() + " onHatch for: " + thisAnt.getTeamInfo().getTeamID());
    }

}
