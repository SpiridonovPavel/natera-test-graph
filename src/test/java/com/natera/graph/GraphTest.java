package com.natera.graph;

import org.junit.jupiter.api.Test;

import java.util.Collection;

import static com.natera.graph.TestData.randomInt;
import static com.natera.graph.TestData.randomString;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author pavel on 27.05.2020.
 */
public class GraphTest {

    @Test
    void testAddVertices() {
        final String vertex1 = randomString();
        final String vertex2 = randomString();
        final String vertex3 = randomString();
        Graph<String> graph = Graph.directed();
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);

        assertThat(graph.getVertices())
                .containsExactlyInAnyOrder(vertex1, vertex2, vertex3);
    }

    @Test
    void testDirectedGraphAddEdges() {
        final String vertex1 = randomString();
        final String vertex2 = randomString();
        final String vertex3 = randomString();
        Graph<String> graph = Graph.directed();
        graph.addEdge(vertex1, vertex2);
        graph.addEdge(vertex2, vertex3);

        assertThat(graph.getVertices())
                .containsExactlyInAnyOrder(vertex1, vertex2, vertex3);

        assertThat(graph.getAdjacentList(vertex1))
                .containsExactlyInAnyOrder(vertex2);

        assertThat(graph.getAdjacentList(vertex2))
                .containsExactlyInAnyOrder(vertex3);

        assertThat(graph.getAdjacentList(vertex3)).isEmpty();
    }

    @Test
    void testUndirectedGraphAddEdges() {
        final String vertex1 = randomString();
        final String vertex2 = randomString();
        final String vertex3 = randomString();
        Graph<String> graph = Graph.undirected();
        graph.addEdge(vertex1, vertex2);
        graph.addEdge(vertex2, vertex3);

        assertThat(graph.getVertices())
                .containsExactlyInAnyOrder(vertex1, vertex2, vertex3);

        assertThat(graph.getAdjacentList(vertex1))
                .containsExactlyInAnyOrder(vertex2);

        assertThat(graph.getAdjacentList(vertex2))
                .containsExactlyInAnyOrder(vertex1, vertex3);

        assertThat(graph.getAdjacentList(vertex3))
                .containsExactlyInAnyOrder(vertex2);
    }

    @Test
    void testVisit() {
        final int vertex1 = randomInt(100);
        final int vertex2 = randomInt(100);
        final int vertex3 = randomInt(100);
        Graph<Integer> graph = Graph.undirected();
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);

        graph.visit(vertex -> vertex / 2);

        assertThat(graph.getVertices())
                .containsExactlyInAnyOrder(vertex1 / 2, vertex2 / 2, vertex3 / 2);
    }

    @Test
    void testUndirectedGraphAnyPath() {
        Graph<Integer> graph = Graph.undirected();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
        graph.addEdge(3, 6);
        graph.addEdge(4, 7);
        graph.addEdge(5, 8);
        graph.addEdge(8, 9);
        graph.addEdge(6, 9);
        graph.addEdge(9, 10);
        graph.addEdge(6, 12);
        graph.addEdge(10, 11);
        graph.addEdge(12, 11);
        graph.addEdge(11, 7);
        final Collection<Integer> path = graph.getAnyPath(1, 11);

        assertThat(path)
                .containsExactly(1, 4, 7, 11);
    }

    @Test
    void testUndirectedGraphShortestPath() {
        Graph<Integer> graph = Graph.undirected();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
        graph.addEdge(3, 6);
        graph.addEdge(4, 7);
        graph.addEdge(5, 8);
        graph.addEdge(8, 9);
        graph.addEdge(6, 9);
        graph.addEdge(9, 10);
        graph.addEdge(6, 12);
        graph.addEdge(10, 11);
        graph.addEdge(12, 11);
        graph.addEdge(11, 7);
        final Collection<Integer> path = graph.getShortestPath(1, 11);

        assertThat(path)
                .containsExactly(1, 4, 7, 11);
    }

    @Test
    void testDirectedGraphAnyPath() {
        Graph<Integer> graph = Graph.directed();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
        graph.addEdge(6, 3);
        graph.addEdge(4, 7);
        graph.addEdge(5, 8);
        graph.addEdge(8, 9);
        graph.addEdge(6, 9);
        graph.addEdge(9, 10);
        graph.addEdge(6, 12);
        graph.addEdge(10, 11);
        graph.addEdge(12, 11);
        graph.addEdge(11, 7);
        final Collection<Integer> path = graph.getAnyPath(1, 11);

        assertThat(path)
                .containsExactly(1, 2, 5, 8, 9, 10, 11);
    }

    @Test
    void testDirectedGraphShortestPath() {
        Graph<Integer> graph = Graph.directed();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(6, 2);
        graph.addEdge(3, 6);
        graph.addEdge(4, 7);
        graph.addEdge(5, 8);
        graph.addEdge(8, 9);
        graph.addEdge(6, 9);
        graph.addEdge(9, 10);
        graph.addEdge(12, 6);
        graph.addEdge(10, 11);
        graph.addEdge(12, 11);
        graph.addEdge(11, 7);
        final Collection<Integer> path = graph.getShortestPath(1, 11);

        assertThat(path)
                .containsExactly(1, 3, 6, 9, 10, 11);
    }
}
