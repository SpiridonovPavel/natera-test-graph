package com.natera.graph;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author pavel on 28.05.2020.
 */
public interface GraphI<VERTEX> {

    boolean isDirected();

    Collection<VERTEX> getVertices();

    List<VERTEX> getAdjacentList(VERTEX source);

    void addVertex(VERTEX node);

    void visit(Function<VERTEX, VERTEX> function);

    Collection<VERTEX> getAnyPath(VERTEX start, VERTEX end);

    Collection<VERTEX> getShortestPath(VERTEX start, VERTEX end);
}
