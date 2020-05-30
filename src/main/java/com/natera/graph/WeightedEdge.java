package com.natera.graph;

import lombok.Data;

/**
 * @author pavel on 29.05.2020.
 */
@Data(staticConstructor = "of")
public class WeightedEdge implements IndexedEdge {

    private final Integer source;
    private final Integer target;
    private final Integer weight;

}
