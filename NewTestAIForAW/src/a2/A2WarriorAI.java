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
public class A2WarriorAI implements IAntAI {

    private final Random rnd = new Random();

    private int turns;
    private int nodeNumberX = 32;
    private int nodeNumberY = 17;
    private Node[][] nodes = new Node[nodeNumberX][nodeNumberY];
    private List<Node> blockList = new ArrayList();
    private MyMovement myM = new MyMovement(nodes, blockList);
    private final LinkedList<EAction> acs = new LinkedList();

    private int moveX;
    private int moveY;
    private int firstMoveX;
    private int firstMoveY;
    private int secondMoveX;
    private int secondMoveY;
    private int thirdMoveX;
    private int thirdMoveY;
    private int fourthMoveX;
    private int fourthMoveY;

    private boolean startWest;
    private boolean movingWarrior;

    public A2WarriorAI(boolean startWest) {
        this.startWest = startWest;
    }

    @Override
    public EAction chooseAction(IAntInfo thisAnt, ILocationInfo thisLocation, List<ILocationInfo> visibleLocations, List<EAction> possibleActions) {

        if (startWest == true) {
            moveX = 31;
            moveY = 7;
            firstMoveX = moveX;
            firstMoveY = moveY;
            secondMoveX = 31;
            secondMoveY = 9;
            thirdMoveX = 30;
            thirdMoveY = 9;
            fourthMoveX = 30;
            fourthMoveY = 7;
        } else if (startWest == false) {
            moveX = 0;
            moveY = 7;
            firstMoveX = moveX;
            firstMoveY = moveY;
            secondMoveX = 0;
            secondMoveY = 9;
            thirdMoveX = 1;
            thirdMoveY = 9;
            fourthMoveX = 1;
            fourthMoveY = 7;
        }

        if (!movingWarrior) {
            return movingWarrior(thisAnt, thisLocation, visibleLocations, possibleActions);
        } else {
            return randomWarrior(thisAnt, thisLocation, visibleLocations, possibleActions);
        }

    }

    private EAction randomWarrior(IAntInfo thisAnt, ILocationInfo thisLocation, List<ILocationInfo> visibleLocations, List<EAction> possibleActions) {
        EAction action = null;

        if (possibleActions.contains(EAction.EatFood) && thisAnt.getHitPoints() < 10) {
            action = EAction.EatFood;
        } else if (possibleActions.contains(EAction.Attack) && visibleLocations.get(0).getAnt().getTeamInfo().getTeamID() != thisAnt.getTeamInfo().getTeamID()) {
            action = EAction.Attack;
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

    private EAction movingWarrior(IAntInfo thisAnt, ILocationInfo thisLocation, List<ILocationInfo> visibleLocations, List<EAction> possibleActions) {
        EAction action = null;

        if (!visibleLocations.isEmpty() && (visibleLocations.get(0).isFilled() || visibleLocations.get(0).isRock())) {

            if (acs.isEmpty()) {
                acs.addAll(myM.moving(thisAnt, moveX, moveY, visibleLocations));

            } else {
                acs.clear();
                acs.addAll(myM.moving(thisAnt, moveX, moveY, visibleLocations));
            }

        }

        if (possibleActions.contains(EAction.Attack) && visibleLocations.get(0).getAnt().getTeamInfo().getTeamID() != thisAnt.getTeamInfo().getTeamID()) {
            action = EAction.Attack;
        } else if (thisAnt.getLocation().getX() == firstMoveX && thisAnt.getLocation().getY() == firstMoveY) {
            acs.clear();
            acs.addAll(myM.moving(thisAnt, secondMoveX, secondMoveY));
            if (possibleActions.contains(acs.peek())) {
                action = acs.poll();
            } else {
                action = EAction.Pass;
            }

        } else if (thisAnt.getLocation().getX() == secondMoveX && thisAnt.getLocation().getY() == secondMoveY) {

            acs.clear();
            acs.addAll(myM.moving(thisAnt, thirdMoveX, thirdMoveY));
            if (possibleActions.contains(acs.peek())) {
                action = acs.poll();
            } else {
                action = EAction.Pass;
            }

        } else if (thisAnt.getLocation().getX() == thirdMoveX && thisAnt.getLocation().getY() == thirdMoveY) {
            acs.clear();
            acs.addAll(myM.moving(thisAnt, fourthMoveX, fourthMoveY));
            if (possibleActions.contains(acs.peek())) {
                action = acs.poll();
            } else {
                action = EAction.Pass;
            }
        } else if (thisAnt.getLocation().getX() == fourthMoveX && thisAnt.getLocation().getY() == fourthMoveY) {
            acs.clear();
            movingWarrior = true;
            action = EAction.Pass;
        } else if (acs.isEmpty()) {
            acs.addAll(myM.moving(thisAnt, firstMoveX, firstMoveY));
            if (possibleActions.contains(acs.peek())) {
                action = acs.poll();
            } else {
                action = EAction.Pass;
            }
        } else {
            if (!acs.isEmpty()) {
                if (possibleActions.contains(acs.peek())) {
                    action = acs.poll();
                } else {
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
        acs.addAll(myM.moving(thisAnt, firstMoveX, firstMoveX));
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

}
