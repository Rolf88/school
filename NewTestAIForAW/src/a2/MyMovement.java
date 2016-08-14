/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2;

import aiantwars.EAction;
import aiantwars.IAntInfo;
import aiantwars.ILocationInfo;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author RolfMoikjær
 */
public class MyMovement {

    private final int nodeNumberX = 32;
    private final int nodeNumberY = 17;
    private Node[][] nodes;
    private IHeuristic h = new EulerHeristic();
    private Graph graph = new Graph();
    private AStarAlgorithm algo = new AStarAlgorithm(h);
    private List<Node> blockList;

    public MyMovement(Node[][] nodes, List<Node> blockList) {
        this.nodes = nodes;
        this.blockList = blockList;
    }

    private LinkedList<EAction> moveNorth(int direction) {
        LinkedList<EAction> actions = new LinkedList();

        if (direction == 0) {
            actions.add(EAction.MoveForward);
        } else if (direction == 1) {
            actions.add(EAction.TurnLeft);
            actions.add(EAction.MoveForward);
        } else if (direction == 2) {
            actions.add(EAction.TurnRight);
            actions.add(EAction.TurnRight);
            actions.add(EAction.MoveForward);
        } else if (direction == 3) {
            actions.add(EAction.TurnRight);
            actions.add(EAction.MoveForward);
        }

        return actions;
    }

    private LinkedList<EAction> moveEast(int direction) {
        LinkedList<EAction> actions = new LinkedList();

        if (direction == 0) {
            actions.add(EAction.TurnRight);
            actions.add(EAction.MoveForward);
        } else if (direction == 1) {
            actions.add(EAction.MoveForward);
        } else if (direction == 2) {
            actions.add(EAction.TurnLeft);
            actions.add(EAction.MoveForward);
        } else if (direction == 3) {
            actions.add(EAction.TurnRight);
            actions.add(EAction.TurnRight);
            actions.add(EAction.MoveForward);
        }

        return actions;
    }

    private LinkedList<EAction> moveSouth(int direction) {
        LinkedList<EAction> actions = new LinkedList();

        if (direction == 0) {
            actions.add(EAction.TurnRight);
            actions.add(EAction.TurnRight);
            actions.add(EAction.MoveForward);
        } else if (direction == 1) {
            actions.add(EAction.TurnRight);
            actions.add(EAction.MoveForward);
        } else if (direction == 2) {
            actions.add(EAction.MoveForward);
        } else if (direction == 3) {
            actions.add(EAction.TurnLeft);
            actions.add(EAction.MoveForward);
        }

        return actions;
    }

    private LinkedList<EAction> moveWest(int direction) {
        LinkedList<EAction> actions = new LinkedList();

        if (direction == 0) {
            actions.add(EAction.TurnLeft);
            actions.add(EAction.MoveForward);
        } else if (direction == 1) {
            actions.add(EAction.TurnRight);
            actions.add(EAction.TurnRight);
            actions.add(EAction.MoveForward);
        } else if (direction == 2) {
            actions.add(EAction.TurnRight);
            actions.add(EAction.MoveForward);
        } else if (direction == 3) {
            actions.add(EAction.MoveForward);
        }

        return actions;
    }

    private boolean inside(int x, int y) {
        return (x >= 0 && x < nodeNumberX && y >= 0 && y < nodeNumberY);
    }

    private void edgeNeighbour(Graph g, Node[][] nodes, int x, int y, Node m, IHeuristic h) {
        if (inside(x, y)) {
            Node n = nodes[x][y];
            if (n != null) {
                g.createEdge(m, n, h.getMinimumDist(m, n));
            }
        }
    }

    private void updateMap() {

        for (int y = 0; y < nodeNumberY; ++y) {
            for (int x = 0; x < nodeNumberX; ++x) {
                Node m = nodes[x][y];
                if (m != null) {
                    //North
                    int nx = x;
                    int ny = y + 1;
                    edgeNeighbour(graph, nodes, nx, ny, m, h);
                    //East
                    nx = x + 1;
                    ny = y;
                    edgeNeighbour(graph, nodes, nx, ny, m, h);
                    //South
                    nx = x;
                    ny = y - 1;
                    edgeNeighbour(graph, nodes, nx, ny, m, h);
                    //West
                    nx = x - 1;
                    ny = y;
                    edgeNeighbour(graph, nodes, nx, ny, m, h);
                }
            }
        }
    }

    private void makeMap(List<Node> blockList) {
        boolean isMatch = true;
        Node tempN = null;
        graph.clearLists();
        for (int y = 0; y < nodeNumberY; ++y) {
            for (int x = 0; x < nodeNumberX; ++x) {
                if (blockList.isEmpty()) {
                    nodes[x][y] = graph.createNode("N", x, y);
                } else {
                    for (Node node : blockList) {

                        if (x == node.getXPos() && y == node.getYPos()) {
                            //System.out.println(node);
                            tempN = node;
                            isMatch = false;
                        }
                    }
                    if (!isMatch && tempN != null) {
                        if (x == tempN.getXPos() && y == tempN.getYPos()) {
                            nodes[x][y] = null;
                            tempN = null;
                            isMatch = true;
                        } else {
                            nodes[x][y] = graph.createNode("N", x, y);
                            tempN = null;
                            isMatch = true;
                        }
                    } else {
                        nodes[x][y] = graph.createNode("N", x, y);
                    }
                }

            }
        }
        updateMap();
    }

    private Iterable<Node> move(IAntInfo thisAnt, int moveX, int moveY) {

        makeMap(blockList);

        Iterable<Node> path = algo.findShortestPath(
                nodes[thisAnt.getLocation().getX()][thisAnt.getLocation().getY()],
                nodes[moveX][moveY]);

        return path;
    }

    private Iterable<Node> move(IAntInfo thisAnt, int moveX, int moveY, List<ILocationInfo> visibleLocations) {
        int blockX = visibleLocations.get(0).getX();
        int blockY = visibleLocations.get(0).getY();

        if (nodes[blockX][blockY] != null) {
            blockList.add(nodes[blockX][blockY]);
            makeMap(blockList);
        } else {
            makeMap(blockList);
        }

        Iterable<Node> path = algo.findShortestPath(
                nodes[thisAnt.getLocation().getX()][thisAnt.getLocation().getY()],
                nodes[moveX][moveY]);

        return path;
    }

    public LinkedList<EAction> moving(IAntInfo thisAnt, int moveX, int moveY, List<ILocationInfo> visibleLocations) {
        LinkedList<EAction> moveQueue = new LinkedList();
        Iterable<Node> path = move(thisAnt, moveX, moveY, visibleLocations);
        int direction = thisAnt.getDirection();
        int xLoc = thisAnt.getLocation().getX();
        int yLoc = thisAnt.getLocation().getY();

        for (Node loc : path) {
            if (xLoc == loc.getXPos()
                    && yLoc == loc.getYPos() - 1) {
                moveQueue.addAll(moveNorth(direction));
                direction = 0;
                yLoc++;
            } else if (xLoc == loc.getXPos() - 1
                    && yLoc == loc.getYPos() - 1) {
                moveQueue.addAll(moveNorth(direction));
                direction = 0;
                moveQueue.addAll(moveEast(direction));
                direction = 1;
                xLoc++;
                yLoc++;
            } else if (xLoc == loc.getXPos() - 1
                    && yLoc == loc.getYPos()) {
                moveQueue.addAll(moveEast(direction));
                direction = 1;
                xLoc++;
            } else if (xLoc == loc.getXPos() - 1
                    && yLoc == loc.getYPos() + 1) {
                moveQueue.addAll(moveSouth(direction));
                direction = 2;
                moveQueue.addAll(moveEast(direction));
                direction = 1;
                xLoc++;
                yLoc--;
            } else if (xLoc == loc.getXPos()
                    && yLoc == loc.getYPos() + 1) {
                moveQueue.addAll(moveSouth(direction));
                direction = 2;
                yLoc--;
            } else if (xLoc == loc.getXPos() + 1
                    && yLoc == loc.getYPos() + 1) {
                moveQueue.addAll(moveSouth(direction));
                direction = 2;
                moveQueue.addAll(moveWest(direction));
                direction = 3;
                xLoc--;
                yLoc--;
            } else if (xLoc == loc.getXPos() + 1
                    && yLoc == loc.getYPos()) {
                moveQueue.addAll(moveWest(direction));
                direction = 3;
                xLoc--;
            } else if (xLoc == loc.getXPos() + 1
                    && yLoc == loc.getYPos() - 1) {
                moveQueue.addAll(moveNorth(direction));
                direction = 0;
                moveQueue.addAll(moveWest(direction));
                direction = 3;
                xLoc--;
                yLoc++;
            }
        }
        return moveQueue;
    }

    public LinkedList<EAction> moving(IAntInfo thisAnt, int moveX, int moveY) {
        LinkedList<EAction> moveQueue = new LinkedList();
        Iterable<Node> path = move(thisAnt, moveX, moveY);
        int direction = thisAnt.getDirection();
        int xLoc = thisAnt.getLocation().getX();
        int yLoc = thisAnt.getLocation().getY();

            for (Node loc : path) {
                if (xLoc == loc.getXPos()
                        && yLoc == loc.getYPos() - 1) {
                    moveQueue.addAll(moveNorth(direction));
                    direction = 0;
                    yLoc++;
                } else if (xLoc == loc.getXPos() - 1
                        && yLoc == loc.getYPos() - 1) {
                    moveQueue.addAll(moveNorth(direction));
                    direction = 0;
                    moveQueue.addAll(moveEast(direction));
                    direction = 1;
                    xLoc++;
                    yLoc++;
                } else if (xLoc == loc.getXPos() - 1
                        && yLoc == loc.getYPos()) {
                    moveQueue.addAll(moveEast(direction));
                    direction = 1;
                    xLoc++;
                } else if (xLoc == loc.getXPos() - 1
                        && yLoc == loc.getYPos() + 1) {
                    moveQueue.addAll(moveSouth(direction));
                    direction = 2;
                    moveQueue.addAll(moveEast(direction));
                    direction = 1;
                    xLoc++;
                    yLoc--;
                } else if (xLoc == loc.getXPos()
                        && yLoc == loc.getYPos() + 1) {
                    moveQueue.addAll(moveSouth(direction));
                    direction = 2;
                    yLoc--;
                } else if (xLoc == loc.getXPos() + 1
                        && yLoc == loc.getYPos() + 1) {
                    moveQueue.addAll(moveSouth(direction));
                    direction = 2;
                    moveQueue.addAll(moveWest(direction));
                    direction = 3;
                    xLoc--;
                    yLoc--;
                } else if (xLoc == loc.getXPos() + 1
                        && yLoc == loc.getYPos()) {
                    moveQueue.addAll(moveWest(direction));
                    direction = 3;
                    xLoc--;
                } else if (xLoc == loc.getXPos() + 1
                        && yLoc == loc.getYPos() - 1) {
                    moveQueue.addAll(moveNorth(direction));
                    direction = 0;
                    moveQueue.addAll(moveWest(direction));
                    direction = 3;
                    xLoc--;
                    yLoc++;
                }
            }

        return moveQueue;
    }

}
