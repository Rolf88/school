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
public class A2QueenAI implements IAntAI {

    private final Random rnd = new Random();
    private LinkedList<EAction> acs = new LinkedList();
    private int turns;
    private int numbInTeam = 0;
    private int nodeNumberX = 32;
    private int nodeNumberY = 17;
    private Node[][] nodes = new Node[nodeNumberX][nodeNumberY];
    private List<Node> blockList = new ArrayList<>();
    private MyMovement myM = new MyMovement(nodes, blockList);
    private boolean firstMove;
    private boolean startWest = false;
    private boolean antInfront;
    private boolean isMoving;
    private int moveX;
    private int moveY;

    public A2QueenAI() {

    }

    public void pickUpFiveFood() {
        acs.add(EAction.PickUpFood);
        acs.add(EAction.PickUpFood);
        acs.add(EAction.PickUpFood);
        acs.add(EAction.PickUpFood);
        acs.add(EAction.PickUpFood);
    }

    @Override
    public EAction chooseAction(IAntInfo thisAnt, ILocationInfo thisLocation, List<ILocationInfo> visibleLocations, List<EAction> possibleActions) {
        EAction action = null;

        if (startWest) {
            moveX = 0;
            moveY = 8;
        } else if (!startWest) {
            moveX = 31;
            moveY = 8;
        }

        if (thisAnt.getLocation().getX() == moveX && thisAnt.getLocation().getY() == moveY) {
            isMoving = false;
        }

//        if (firstMove) {
//            pickUpFiveFood();
//            acs.add(EAction.MoveForward);
//            pickUpFiveFood();
//            acs.add(EAction.TurnRight);
//            acs.add(EAction.LayEgg);
//            acs.add(EAction.TurnRight);
//            acs.add(EAction.MoveForward);
//            acs.add(EAction.TurnLeft);
//            acs.add(EAction.MoveForward);
//            pickUpFiveFood();
//            acs.add(EAction.TurnRight);
//            acs.add(EAction.LayEgg);
//            acs.add(EAction.TurnRight);
//            acs.add(EAction.MoveForward);
//            acs.add(EAction.TurnLeft);
//            acs.add(EAction.MoveForward);
//            pickUpFiveFood();
//            firstMove = false;
//        }
        if (antInfront) {
            if (possibleActions.contains(EAction.MoveForward)) {
                acs.add(0, EAction.MoveForward);
            } else if (possibleActions.contains(EAction.MoveBackward)) {
                acs.add(0, EAction.MoveBackward);
            }
            antInfront = false;
        }

//        if (!visibleLocations.isEmpty() && (visibleLocations.get(0).isFilled() || visibleLocations.get(0).isRock()) && isMoving) {
//
//            if (acs.isEmpty()) {
//                acs.addAll(myM.moving(thisAnt, moveX, moveY, visibleLocations));
//
//            } else {
//                acs.clear();
//                acs.addAll(myM.moving(thisAnt, moveX, moveY, visibleLocations));
//
//            }
//
//        }
//        if (!acs.isEmpty()) {
//            if (possibleActions.contains(acs.peek())) {
//                action = acs.poll();
//            } else if (!visibleLocations.isEmpty() && visibleLocations.get(0).getAnt() != null && visibleLocations.get(0).getAnt().getTeamInfo() == thisAnt.getTeamInfo() && possibleActions.contains(EAction.TurnLeft)) {
//                antInfront = true;
//                action = EAction.TurnLeft;
//            } else {
//                action = EAction.Pass;
//            }
//        } else 
        if (possibleActions.contains(EAction.EatFood) && thisAnt.getHitPoints() < 5) {
            action = EAction.EatFood;
//      }   else if (possibleActions.contains(EAction.LayEgg) && thisAnt.getFoodLoad() >= 7 && numbInTeam < 6) {
//            action = EAction.LayEgg;
        } else if (possibleActions.contains(EAction.PickUpFood)) {
            action = EAction.PickUpFood;
//        } else if (thisAnt.getFoodLoad() >= 7) {
//            if (!visibleLocations.isEmpty()
//                    && (visibleLocations.get(0).isFilled()
//                    || visibleLocations.get(0).isRock()
//                    || visibleLocations.get(0).getAnt() != null
//                    && possibleActions.contains(EAction.TurnLeft))) {
//                action = EAction.TurnLeft;
//            } else if (visibleLocations.isEmpty() && possibleActions.contains(EAction.TurnLeft)) {
//                action = EAction.TurnLeft;
//            } else {
//                action = EAction.Pass;
//            }
//        } else if ((turns >= 95 && turns < 105) || (turns >= 65 && turns < 75) && !(thisAnt.getLocation().getX() == moveX && thisAnt.getLocation().getY() == moveY)) {
//            if (!acs.isEmpty()) {
//
//                if (possibleActions.contains(acs.peek())) {
//                    action = acs.poll();
//                } else {
//                    acs.clear();
//                    acs.addAll(myM.moving(thisAnt, moveX, moveY));
//                    isMoving = true;
//                    action = EAction.Pass;
//                }
//
//            } else if (!(thisAnt.getLocation().getX() == moveX && thisAnt.getLocation().getY() == moveY)) {
//                acs.addAll(myM.moving(thisAnt, moveX, moveY));
//                isMoving = true;
//                action = EAction.Pass;
//            } else {
//                action = EAction.Pass;
//            }
        } else if (possibleActions.contains(EAction.TurnRight)) {
            boolean mover = false;
            do {
                action = possibleActions.get(rnd.nextInt(possibleActions.size()));
                if (action == EAction.MoveForward || action == EAction.MoveBackward || action == EAction.TurnLeft || action == EAction.TurnRight) {
                    mover = true;
                }
            } while (!mover);
        }

        if (action == null) {
            action = EAction.Pass;
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
        firstMove = true;
        StartLocation(thisAnt);
    }

    @Override
    public void onStartTurn(IAntInfo thisAnt, int turn) {
        System.out.println("ID: " + thisAnt.antID() + " onStartTurn(" + turn + ")");
        turns = turn;
    }

    @Override
    public void onLayEgg(IAntInfo thisAnt, List<EAntType> types, IEgg egg) {
        EAntType type = null;
        if (numbInTeam < 1) {
            numbInTeam++;
            type = EAntType.CARRIER;
            egg.set(type, new A2CarrierAI(numbInTeam, startWest));
        } else if (numbInTeam < 5) {
            numbInTeam++;
            type = EAntType.WARRIOR;
            egg.set(type, new A2WarriorAI(startWest));
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
        blockList.clear();
    }

    @Override
    public void onEndRound(int yourMajor, int yourMinor, int enemyMajor, int enemyMinor) {
    }

    @Override
    public void onEndMatch(int yourScore, int yourWins, int enemyScore, int enemyWins) {
    }

    private void StartLocation(IAntInfo thisAnt) {
        if (thisAnt.getLocation().getX() == 0 && thisAnt.getLocation().getY() == 8) {
            startWest = true;
        } else {
            startWest = false;
        }
    }
}
