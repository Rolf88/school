/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aalgorithm;

/**
 *
 * @author Tobias Grundtvig
 */
public class Test {

    private int nodeNumber = 16;

    public boolean inside(int x, int y) {
        return (x >= 0 && x < nodeNumber && y >= 0 && y < nodeNumber);
    }

    public void edgeNeighbour(Graph g, Node[][] nodes, int x, int y, Node m, IHeuristic h) {
        if (inside(x, y)) {
            Node n = nodes[x][y];
            if (n != null) {
                g.createEdge(m, n, h.getMinimumDist(m, n));
            }
        }
    }

//    public static void main(String[] args) {
//        aAlgorithm();
//    }
//
//    public static Iterable<Node> aAlgorithm() {
//
//        IHeuristic h = new EulerHeristic();
//        Graph graph = new Graph();
//        Node[][] nodes = new Node[5][5];
//        for (int y = 0; y < 5; ++y) {
//            for (int x = 0; x < 5; ++x) {
//                if ((x == 1 && y == 3)
//                        || (x == 2 && y == 1)
//                        || (x == 2 && y == 2)
//                        || (x == 2 && y == 3)) {
//                    nodes[x][y] = null;
//                } else {
//                    nodes[x][y] = graph.createNode("N", x, y);
//                }
//            }
//        }
//
//        for (int y = 0; y < 5; ++y) {
//            for (int x = 0; x < 5; ++x) {
//                Node m = nodes[x][y];
//                if (m != null) {
//                    //North
//                    int nx = x;
//                    int ny = y + 1;
//                    edgeNeighbour(graph, nodes, nx, ny, m, h);
//                    //North-East
//                    nx = x + 1;
//                    ny = y + 1;
//                    edgeNeighbour(graph, nodes, nx, ny, m, h);
//                    //East
//                    nx = x + 1;
//                    ny = y;
//                    edgeNeighbour(graph, nodes, nx, ny, m, h);
//                    //South-East
//                    nx = x + 1;
//                    ny = y - 1;
//                    edgeNeighbour(graph, nodes, nx, ny, m, h);
//                    //South
//                    nx = x;
//                    ny = y - 1;
//                    edgeNeighbour(graph, nodes, nx, ny, m, h);
//                    //South-West
//                    nx = x - 1;
//                    ny = y - 1;
//                    edgeNeighbour(graph, nodes, nx, ny, m, h);
//                    //West
//                    nx = x - 1;
//                    ny = y;
//                    edgeNeighbour(graph, nodes, nx, ny, m, h);
//                    //North-West
//                    nx = x - 1;
//                    ny = y + 1;
//                    edgeNeighbour(graph, nodes, nx, ny, m, h);
//                }
//            }
//        }
//
//        AStarAlgorithm algo = new AStarAlgorithm(h);
//        Iterable<Node> path = algo.findShortestPath(graph, nodes[1][2], nodes[3][4]);
//
//        for (Node n : path) {
//            System.out.println(n + " dist " + n.getGVal());
//        }
//
//        return path;
//    }
    public Iterable<Node> astarForViz() {

        IHeuristic h = new EulerHeristic();
        Graph graph = new Graph();
        Node[][] nodes = new Node[nodeNumber][nodeNumber];
        for (int y = 0; y < nodeNumber; ++y) {
            for (int x = 0; x < nodeNumber; ++x) {
                if ((x == 1 && y == 1)
                        || (x == 2 && y == 1)
                        || (x == 3 && y == 1)
                        || (x == 1 && y == 2)) {
                    nodes[x][y] = null;
                } else {
                    nodes[x][y] = graph.createNode("N", x, y);
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

        AStarAlgorithm algo = new AStarAlgorithm(h);
        Iterable<Node> path = algo.findShortestPath(nodes[0][0], nodes[15][15]);

        return path;
    }
}
