/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myantwars;

import AAlgorithm.AStarAlgorithm;
import AAlgorithm.EulerHeristic;
import AAlgorithm.Graph;
import AAlgorithm.IHeuristic;
import AAlgorithm.Node;
import aiantwars.EAction;
import aiantwars.IAntInfo;
import aiantwars.ILocationInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author RolfMoikjær
 */
public class MyMovement {

    private final int nodeNumber = 16;
    private final Node[][] nodes = new Node[nodeNumber][nodeNumber];
    IHeuristic h = new EulerHeristic();
    Graph graph = new Graph();
    private boolean isMapSet = false;

    private Queue<EAction> moveNorth(IAntInfo thisAnt) {
        Queue<EAction> actions = new PriorityQueue();

        if (thisAnt.getDirection() == 0) {
            actions.add(EAction.MoveForward);
        } else if (thisAnt.getDirection() == 1) {
            actions.add(EAction.TurnLeft);
            actions.add(EAction.MoveForward);
        } else if (thisAnt.getDirection() == 2) {
            actions.add(EAction.TurnLeft);
            actions.add(EAction.TurnLeft);
            actions.add(EAction.MoveForward);
        } else if (thisAnt.getDirection() == 3) {
            actions.add(EAction.TurnRight);
            actions.add(EAction.MoveForward);
        }

        return actions;
    }

    private Queue<EAction> moveEast(IAntInfo thisAnt) {
        Queue<EAction> actions = new PriorityQueue();

        if (thisAnt.getDirection() == 0) {
            actions.add(EAction.TurnRight);
            actions.add(EAction.MoveForward);
        } else if (thisAnt.getDirection() == 1) {
            actions.add(EAction.MoveForward);
        } else if (thisAnt.getDirection() == 2) {
            actions.add(EAction.TurnLeft);
            actions.add(EAction.MoveForward);
        } else if (thisAnt.getDirection() == 3) {
            actions.add(EAction.TurnLeft);
            actions.add(EAction.TurnLeft);
            actions.add(EAction.MoveForward);
        }

        return actions;
    }

    private Queue<EAction> moveSouth(IAntInfo thisAnt) {
        Queue<EAction> actions = new PriorityQueue();

        if (thisAnt.getDirection() == 0) {
            actions.add(EAction.TurnLeft);
            actions.add(EAction.TurnLeft);
            actions.add(EAction.MoveForward);
        } else if (thisAnt.getDirection() == 1) {
            actions.add(EAction.TurnRight);
            actions.add(EAction.MoveForward);
        } else if (thisAnt.getDirection() == 2) {
            actions.add(EAction.MoveForward);
        } else if (thisAnt.getDirection() == 3) {
            actions.add(EAction.TurnLeft);
            actions.add(EAction.MoveForward);
        }

        return actions;
    }

    private Queue<EAction> moveWest(IAntInfo thisAnt) {
        Queue<EAction> actions = new PriorityQueue();

        if (thisAnt.getDirection() == 0) {
            actions.add(EAction.TurnLeft);
            actions.add(EAction.MoveForward);
        } else if (thisAnt.getDirection() == 1) {
            actions.add(EAction.TurnLeft);
            actions.add(EAction.TurnLeft);
            actions.add(EAction.MoveForward);
        } else if (thisAnt.getDirection() == 2) {
            actions.add(EAction.TurnRight);
            actions.add(EAction.MoveForward);
        } else if (thisAnt.getDirection() == 3) {
            actions.add(EAction.MoveForward);
        }

        return actions;
    }

    private boolean inside(int x, int y) {
        return (x >= 0 && x < nodeNumber && y >= 0 && y < nodeNumber);
    }

    private void edgeNeighbour(Graph g, Node[][] nodes, int x, int y, Node m, IHeuristic h) {
        if (inside(x, y)) {
            Node n = nodes[x][y];
            if (n != null) {
                g.createEdge(m, n, h.getMinimumDist(m, n));
            }
        }
    }

    public void findObstacles(List<ILocationInfo> visibleLocations) {
        List xList = new ArrayList();
        List yList = new ArrayList();
        for (ILocationInfo visibleLocation : visibleLocations) {
            if (visibleLocation.isFilled() || visibleLocation.isRock()) {
                xList.add(visibleLocation.getX());
                yList.add(visibleLocation.getY());
                updateMap(xList, yList);
            }
        }
    }

    private Node[][] updateMap(List xList, List yList) {
        for (int y = 0; y < nodeNumber; ++y) {
            for (int x = 0; x < nodeNumber; ++x) {
                for (int i = 0; i < xList.size(); i++) {
                    if (x == (int) xList.get(i) && y == (int) yList.get(i)) {
                        nodes[x][y] = null;
                    } else {
                        nodes[x][y] = graph.createNode("N", x, y);
                    }
                }

            }
        }

        for (int y = 0; y < nodeNumber; ++y) {
            for (int x = 0; x < nodeNumber; ++x) {
                Node m = nodes[x][y];
                if (m != null) {
                    //North
                    int nx = x;
                    int ny = y + 1;
                    edgeNeighbour(graph, nodes, nx, ny, m, h);
                    //North-East
                    nx = x + 1;
                    ny = y + 1;
                    edgeNeighbour(graph, nodes, nx, ny, m, h);
                    //East
                    nx = x + 1;
                    ny = y;
                    edgeNeighbour(graph, nodes, nx, ny, m, h);
                    //South-East
                    nx = x + 1;
                    ny = y - 1;
                    edgeNeighbour(graph, nodes, nx, ny, m, h);
                    //South
                    nx = x;
                    ny = y - 1;
                    edgeNeighbour(graph, nodes, nx, ny, m, h);
                    //South-West
                    nx = x - 1;
                    ny = y - 1;
                    edgeNeighbour(graph, nodes, nx, ny, m, h);
                    //West
                    nx = x - 1;
                    ny = y;
                    edgeNeighbour(graph, nodes, nx, ny, m, h);
                    //North-West
                    nx = x - 1;
                    ny = y + 1;
                    edgeNeighbour(graph, nodes, nx, ny, m, h);
                }
            }
        }
        return nodes;
    }

    private Node[][] makeMap() {
        for (int y = 0; y < nodeNumber; ++y) {
            for (int x = 0; x < nodeNumber; ++x) {
                nodes[x][y] = graph.createNode("N", x, y);
            }
        }

        for (int y = 0; y < nodeNumber; ++y) {
            for (int x = 0; x < nodeNumber; ++x) {
                Node m = nodes[x][y];
                if (m != null) {
                    //North
                    int nx = x;
                    int ny = y + 1;
                    edgeNeighbour(graph, nodes, nx, ny, m, h);
                    //North-East
                    nx = x + 1;
                    ny = y + 1;
                    edgeNeighbour(graph, nodes, nx, ny, m, h);
                    //East
                    nx = x + 1;
                    ny = y;
                    edgeNeighbour(graph, nodes, nx, ny, m, h);
                    //South-East
                    nx = x + 1;
                    ny = y - 1;
                    edgeNeighbour(graph, nodes, nx, ny, m, h);
                    //South
                    nx = x;
                    ny = y - 1;
                    edgeNeighbour(graph, nodes, nx, ny, m, h);
                    //South-West
                    nx = x - 1;
                    ny = y - 1;
                    edgeNeighbour(graph, nodes, nx, ny, m, h);
                    //West
                    nx = x - 1;
                    ny = y;
                    edgeNeighbour(graph, nodes, nx, ny, m, h);
                    //North-West
                    nx = x - 1;
                    ny = y + 1;
                    edgeNeighbour(graph, nodes, nx, ny, m, h);
                }
            }
        }
        return nodes;
    }

    private Iterable<Node> move(IAntInfo thisAnt, int moveX, int moveY) {
        Node[][] nodes2;

//        if (!isMapSet) {
//            nodes2 = makeMap();
//            isMapSet = true;
//        } else {
//            nodes2 = nodes;
//        }
        nodes2 = makeMap();

        AStarAlgorithm algo = new AStarAlgorithm(h);
        Iterable<Node> path
                = algo.findShortestPath(graph,
                        nodes2[thisAnt.getLocation().getX()][thisAnt.getLocation().getY()],
                        nodes2[moveX][moveY]);

        return path;
    }

    public Queue<EAction> moving(IAntInfo thisAnt, int moveX, int moveY) {
        Queue<EAction> moveQueue = new PriorityQueue();
        Iterable<Node> path = move(thisAnt, moveX, moveY);

        for (Node loc : path) {
            if (thisAnt.getLocation().getX() == loc.getXPos()
                    && thisAnt.getLocation().getY() == loc.getYPos() - 1) {
                moveQueue.addAll(moveNorth(thisAnt));
            } else if (thisAnt.getLocation().getX() == loc.getXPos() - 1
                    && thisAnt.getLocation().getY() == loc.getYPos() - 1) {
                moveQueue.addAll(moveNorth(thisAnt));
                moveQueue.addAll(moveEast(thisAnt));
            } else if (thisAnt.getLocation().getX() == loc.getXPos() - 1
                    && thisAnt.getLocation().getY() == loc.getYPos()) {
                moveQueue.addAll(moveEast(thisAnt));
            } else if (thisAnt.getLocation().getX() == loc.getXPos() - 1
                    && thisAnt.getLocation().getY() == loc.getYPos() + 1) {
                moveQueue.addAll(moveEast(thisAnt));
                moveQueue.addAll(moveSouth(thisAnt));
            } else if (thisAnt.getLocation().getX() == loc.getXPos()
                    && thisAnt.getLocation().getY() == loc.getYPos() + 1) {
                moveQueue.addAll(moveSouth(thisAnt));
            } else if (thisAnt.getLocation().getX() == loc.getXPos() + 1
                    && thisAnt.getLocation().getY() == loc.getYPos() + 1) {
                moveQueue.addAll(moveSouth(thisAnt));
                moveQueue.addAll(moveWest(thisAnt));
            } else if (thisAnt.getLocation().getX() == loc.getXPos() + 1
                    && thisAnt.getLocation().getY() == loc.getYPos()) {
                moveQueue.addAll(moveWest(thisAnt));
            } else if (thisAnt.getLocation().getX() == loc.getXPos() + 1
                    && thisAnt.getLocation().getY() == loc.getYPos() - 1) {
                moveQueue.addAll(moveNorth(thisAnt));
                moveQueue.addAll(moveWest(thisAnt));
            }
        }
        return moveQueue;
    }

}
