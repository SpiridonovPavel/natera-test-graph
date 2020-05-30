package com.natera.graph;

import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Integer.MAX_VALUE;

/**
 * @author pavel on 30.05.2020.
 */
public abstract class AbstractGraph<VERTEX, EDGE extends IndexedEdge> implements GraphI<VERTEX> {

    protected AtomicInteger verticesCount = new AtomicInteger(0);

    @Getter
    protected boolean directed;

    protected List<VERTEX> vertices = new ArrayList<>();

    protected Map<Integer, List<EDGE>> adjacentIndexMap = new HashMap<>();

    @Override
    public Collection<VERTEX> getVertices() {
        return Collections.unmodifiableCollection(vertices);
    }

    @Override
    public List<VERTEX> getAdjacentList(VERTEX source) {
        if (vertices.contains(source)) {
            return adjacentIndexMap.get(vertices.indexOf(source)).stream()
                    .map(edge -> vertices.get(edge.getTarget()))
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    @Override
    public void addVertex(VERTEX vertex) {
        if (!vertices.contains(vertex)) {
            final int index = verticesCount.getAndIncrement();
            vertices.add(index, vertex);
            adjacentIndexMap.putIfAbsent(index, new ArrayList<>());
        }
    }

    @Override
    public void visit(Function<VERTEX, VERTEX> function) {
        vertices = vertices.stream().map(function).collect(Collectors.toList());
    }

    @Override
    public List<VERTEX> getAnyPath(VERTEX start, VERTEX end) {
        if (!vertices.contains(start) && !vertices.contains(end)) {
            return Collections.emptyList();
        }
        final int startIndex = vertices.indexOf(start);
        final int endIndex = vertices.indexOf(end);
        Set<Integer> visited = new HashSet<>();
        Set<Integer> toVisit = new HashSet<>();
        Map<Integer, EDGE> edges = new LinkedHashMap<>();
        toVisit.add(startIndex);
        while (CollectionUtils.isNotEmpty(toVisit)) {
            toVisit.stream()
                    .findFirst()
                    .ifPresent(sourceIndex -> {
                        visited.add(sourceIndex);
                        toVisit.remove(sourceIndex);
                        adjacentIndexMap.getOrDefault(sourceIndex, List.of()).stream()
                                .filter(edge -> !visited.contains(edge.getTarget()))
                                .forEach(edge -> {
                                    final Integer edgeIndex = edge.getTarget();
                                    edges.put(edgeIndex, edge);
                                    if (end.equals(edgeIndex)) {
                                        toVisit.clear();
                                    }
                                    toVisit.add(edgeIndex);
                                });
                    });
        }
        return getPath(endIndex, edges);
    }

    @Override
    public List<VERTEX> getShortestPath(VERTEX start, VERTEX end) {
        if (!vertices.contains(start) && !vertices.contains(end)) {
            return Collections.emptyList();
        }
        final int startIndex = vertices.indexOf(start);
        final int endIndex = vertices.indexOf(end);
        Set<Integer> visited = new HashSet<>();
        Set<Integer> toVisit = new HashSet<>();
        Map<Integer, EDGE> edges = new LinkedHashMap<>();
        toVisit.add(startIndex);
        Map<Integer, Integer> distances = new HashMap<>(verticesCount.get());
        IntStream.range(0, verticesCount.get() + 1).forEach(index -> distances.put(index, MAX_VALUE));
        distances.put(startIndex, 0);
        toVisit.add(startIndex);
        while (CollectionUtils.isNotEmpty(toVisit)) {
            toVisit.stream()
                    .reduce((a, b) -> distances.get(a) < distances.get(b) ? a : b)
                    .ifPresent(sourceIndex -> {
                        visited.add(sourceIndex);
                        toVisit.remove(sourceIndex);
                        adjacentIndexMap.getOrDefault(sourceIndex, List.of()).stream()
                                .filter(edge -> !visited.contains(edge.getTarget()))
                                .forEach(edge -> {
                                    final Integer targetIndex = edge.getTarget();
                                    final int newDistance = distances.get(sourceIndex) + getEdgeWeight(edge);
                                    if (distances.get(targetIndex) > newDistance) {
                                        distances.put(targetIndex, newDistance);
                                        edges.put(targetIndex, edge);
                                        toVisit.add(targetIndex);
                                    }
                                });
                    });
        }
        return getPath(endIndex, edges);
    }

    protected abstract Integer getEdgeWeight(EDGE edge);

    private List<VERTEX> getPath(Integer endIndex, Map<Integer, EDGE> edges) {
        List<VERTEX> path = new LinkedList<>();
        EDGE edge = edges.get(endIndex);
        if (Objects.isNull(edge)) {
            return List.of();
        }
        path.add(vertices.get(edge.getTarget()));
        while (Objects.nonNull(edge)) {
            path.add(vertices.get(edge.getSource()));
            edge = edges.get(edge.getSource());
        }
        Collections.reverse(path);
        return path;
    }
}
