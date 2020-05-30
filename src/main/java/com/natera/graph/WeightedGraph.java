package com.natera.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author pavel on 28.05.2020.
 */
public class WeightedGraph<VERTEX> extends AbstractGraph<VERTEX, WeightedEdge> {

    private WeightedGraph(boolean directed) {
        this.directed = directed;
    }

    public static <VERTEX> WeightedGraph<VERTEX> directed() {
        return new WeightedGraph<>(true);
    }

    public static <VERTEX> WeightedGraph<VERTEX> undirected() {
        return new WeightedGraph<>(false);
    }

    public void addEdge(VERTEX source, VERTEX target, Integer weight) {
        if (!vertices.contains(source)) {
            addVertex(source);
        }
        final int sourceIndex = vertices.indexOf(source);
        if (!vertices.contains(target)) {
            addVertex(target);
        }
        final int targetIndex = vertices.indexOf(target);
        adjacentIndexMap.computeIfAbsent(sourceIndex, key -> new ArrayList<>()).add(WeightedEdge.of(sourceIndex, targetIndex, weight));
        if (!directed) {
            adjacentIndexMap.computeIfAbsent(targetIndex, key -> new ArrayList<>()).add(WeightedEdge.of(targetIndex, sourceIndex, weight));
        }
    }

    @Override
    protected Integer getEdgeWeight(WeightedEdge edge) {
        return edge.getWeight();
    }
}
