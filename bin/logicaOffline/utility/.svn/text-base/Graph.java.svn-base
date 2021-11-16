package logicaOffline.utility;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import logicaOffline.common.AbstractStaticObject;

public class Graph {
    private Map<AbstractStaticObject, LinkedHashSet<AbstractStaticObject>> map = new HashMap<>();

    public void addEdge(AbstractStaticObject node1, AbstractStaticObject node2) {
        LinkedHashSet<AbstractStaticObject> adjacent = map.get(node1);
        if(adjacent==null) {
            adjacent = new LinkedHashSet<AbstractStaticObject>();
            map.put(node1, adjacent);
        }
        adjacent.add(node2);
    }

    public void addTwoWayVertex(AbstractStaticObject node1, AbstractStaticObject node2) {
        addEdge(node1, node2);
        addEdge(node2, node1);
    }

    public boolean isConnected(AbstractStaticObject node1, AbstractStaticObject node2) {
        Set<?> adjacent = map.get(node1);
        if(adjacent==null) {
            return false;
        }
        return adjacent.contains(node2);
    }

    public LinkedList<AbstractStaticObject> adjacentNodes(AbstractStaticObject last) {
        LinkedHashSet<AbstractStaticObject> adjacent = map.get(last);
        if(adjacent==null) {
            return new LinkedList<AbstractStaticObject>();
        }
        
        return new LinkedList<AbstractStaticObject>(adjacent);
    }
}