package com.natera.graph;

import org.junit.jupiter.api.Test;

import static com.natera.graph.TestData.randomString;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author pavel on 27.05.2020.
 */
public class VertexTest {

    @Test
    void testGetValue(){
        final String value = randomString();
        Vertex<String> vertex = new SimpleVertex<String>().setValue(value);
        assertEquals(value, vertex.getValue());
    }
}
