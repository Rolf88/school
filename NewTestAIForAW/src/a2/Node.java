/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Tobias Grundtvig
 */
public class Node implements Iterable<Edge>, Comparable<Node> {

    private String name;
    private double xPos;
    private double yPos;
    private Node prev;
    private double gVal;
    private double hVal;
    private ArrayList<Edge> edges;

    public Node(String name, double xPos, double yPos) {
        this.name = name;
        this.xPos = xPos;
        this.yPos = yPos;
        prev = null;
        gVal = Double.POSITIVE_INFINITY;
        hVal = Double.POSITIVE_INFINITY;
        edges = new ArrayList<>();
    }

    public Node() {
    }

    public double getXPos() {
        return xPos;
    }

    public double getYPos() {
        return yPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void removeEdges() {
        if (!edges.isEmpty()) {
            edges.clear();
        }
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public double getGVal() {
        return gVal;
    }

    public void setGVal(double gVal) {
        this.gVal = gVal;
    }

    public double getFVal() {
        return gVal + hVal;
    }

    public double getHVal() {
        return hVal;
    }

    public void setHVal(double hVal) {
        this.hVal = hVal;
    }

    @Override
    public String toString() {
        return name + ": (" + xPos + ", " + yPos + ")";
    }

    @Override
    public Iterator<Edge> iterator() {
        return edges.iterator();
    }

    @Override
    public int compareTo(Node o) {
        if (this.getFVal() < o.getFVal()) {
            return -1;
        }
        if (this.getFVal() > o.getFVal()) {
            return 1;
        }
        if (this.getHVal() < o.getHVal()) {
            return -1;
        }
        if (this.getHVal() > o.getHVal()) {
            return 1;
        }
        return 0;
    }

}
