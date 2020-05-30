package com.natera.graph;

import java.util.ArrayList;

/**
 * @author pavel on 28.05.2020.
 */
public class Graph<VERTEX> extends AbstractGraph<VERTEX, Edge> {

    private Graph(boolean directed) {
        this.directed = directed;
    }

    public static <VERTEX> Graph<VERTEX> directed() {
        return new Graph<>(true);
    }

    public static <VERTEX> Graph<VERTEX> undirected() {
        return new Graph<>(false);
    }

    public void addEdge(VERTEX source, VERTEX target) {
        if (!vertices.contains(source)) {
            addVertex(source);
        }
        final int sourceIndex = vertices.indexOf(source);
        if (!vertices.contains(target)) {
            addVertex(target);
        }
        final int targetIndex = vertices.indexOf(target);
        adjacentIndexMap.computeIfAbsent(sourceIndex, key -> new ArrayList<>()).add(Edge.of(sourceIndex, targetIndex));
        if (!directed) {
            adjacentIndexMap.computeIfAbsent(targetIndex, key -> new ArrayList<>()).add(Edge.of(targetIndex, sourceIndex));
        }
    }

    @Override
    protected Integer getEdgeWeight(Edge edge) {
        return 1;
    }
}
