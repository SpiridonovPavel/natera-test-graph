package com.natera.graph;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author pavel on 27.05.2020.
 */
@Data
@Accessors(chain = true)
public class SimpleVertex<T> implements Vertex<T> {

    private T value;
}
