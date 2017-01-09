package epmc.graph.explicit;

import gnu.trove.stack.TIntStack;
import gnu.trove.stack.array.TIntArrayStack;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.base.MoreObjects;

import epmc.error.EPMCException;
import epmc.util.BitSet;
import epmc.util.UtilBitSet;
import epmc.value.ContextValue;
import epmc.value.Type;
import epmc.value.UtilValue;
import epmc.value.Value;

/**
 * Stores the graph, node, and edge properties of an explicit-state graph.
 * 
 * @author Ernst Moritz Hahn
 */
public final class GraphExplicitProperties implements Serializable {
    /** 1L, because I don't know any better. */
    private static final long serialVersionUID = 1L;
    /** String "graphProperties". */
    private final static String GRAPH_PROPERTIES = "graphProperties";
    /** String "nodeProperties". */
    private final static String NODE_PROPERTIES = "nodeProperties";
    /** String "edgeProperties". */
    private final static String EDGE_PROPERTIES = "edgeProperties";
    
    private transient final BitSet exploredNodes;
    private transient int[] predecessorBounds;
    private transient int[] predecessorTargets;
    private final GraphExplicit graph;
    private final Map<Object,Value> graphProperties = new LinkedHashMap<>();
    private final Map<Object,Value> graphPropertiesExternal = Collections.unmodifiableMap(graphProperties);
    private final Map<Object,NodeProperty> nodeProperties = new LinkedHashMap<>();
    private final Map<Object,NodeProperty> nodePropertiesExternal = Collections.unmodifiableMap(nodeProperties);
    private final Map<Object,EdgeProperty> edgeProperties = new LinkedHashMap<>();
    private final Map<Object,EdgeProperty> edgePropertiesExternal = Collections.unmodifiableMap(edgeProperties);
    private final ContextValue contextValue;
    private boolean predecessorsComputed;
    
    public GraphExplicitProperties(GraphExplicit graph, ContextValue contextValue) {
        assert graph != null;
        assert contextValue != null;
        this.graph = graph;
        this.contextValue = contextValue;
        exploredNodes = UtilBitSet.newBitSetUnbounded();
    }
    
    // TODO maybe this method should be removed later
    public Set<Object> getGraphProperties() {
        return graphPropertiesExternal.keySet();
    }
    
    public Map<Object,Value> getGraphPropertiesMap() {
        return graphPropertiesExternal;
    }
    
    public Value getGraphProperty(Object property) {
        assert property != null;
        return graphProperties.get(property);
    }

    public void registerGraphProperty(Object propertyName, Value value) {
        assert propertyName != null;
        assert value != null;
        assert !graphProperties.containsKey(propertyName);
        graphProperties.put(propertyName, UtilValue.clone(value));
    }

    public void registerGraphProperty(Object propertyName, Type type) {
        assert propertyName != null;
        assert type != null;
        assert !graphProperties.containsKey(propertyName);
        graphProperties.put(propertyName, type.newValue());
    }

    public void setGraphProperty(Object property, Value value)
            throws EPMCException {
        assert property != null;
        assert value != null;
        assert graphProperties.containsKey(property);
        getGraphProperty(property).set(value);
    }

    
    public void registerNodeProperty(Object propertyName,
            NodeProperty property) {
        assert propertyName != null;
        assert property != null;
        assert property.getGraph() == this.graph;
        if (nodeProperties.containsKey(propertyName)) {
            return;
        }
        nodeProperties.put(propertyName, property);
    }
    
    public NodeProperty getNodeProperty(Object property) {
        assert property != null;
        return nodeProperties.get(property);
    }

    // TODO maybe this method should be removed later
    public Set<Object> getNodeProperties() {
        return nodePropertiesExternal.keySet();
    }
    
    public Map<Object,NodeProperty> getNodePropertiesMap() {
        return nodePropertiesExternal;
    }
    
    public void registerEdgeProperty(Object propertyName,
            EdgeProperty property) {
        assert propertyName != null;
        assert property != null;
        assert property.getGraph() == this.graph;
        if (edgeProperties.containsKey(propertyName)) {
            return;
        }
        edgeProperties.put(propertyName, property);
    }
    

    public EdgeProperty getEdgeProperty(Object property) {
        assert property != null;
        return edgeProperties.get(property);
    }

    // TODO maybe this method should be removed later
    public Set<Object> getEdgeProperties() {
        return edgePropertiesExternal.keySet();
    }
    
    public Map<Object,EdgeProperty> getEdgePropertiesMap() {
        return edgePropertiesExternal;
    }

    public void explore(BitSet start) throws EPMCException {
        TIntStack todo = new TIntArrayStack();
        for (int node = start.nextSetBit(0); node >= 0; node = start.nextSetBit(node+1)) {
            if (!exploredNodes.get(node)) {
                exploredNodes.set(node);
                todo.push(node);
            }
        }

        while (todo.size() > 0) {
            int node = todo.pop();
            graph.queryNode(node);
            for (int succNr = 0; succNr < graph.getNumSuccessors(); succNr++) {
                int succ = graph.getSuccessorNode(succNr);
                assert succ >= 0;
                if (!exploredNodes.get(succ)) {
                    exploredNodes.set(succ);
                    todo.push(succ);
                }
            }
        }
    }

    public void computePredecessors() throws EPMCException {
        if (predecessorsComputed) {
            return;
        }
        predecessorsComputed = true;
        predecessorTargets = null;
        int numNodes = graph.getNumNodes();
        predecessorBounds = new int[numNodes + 1];
        for (int node = 0; node < numNodes; node++) {
            graph.queryNode(node);
            for (int succNr = 0; succNr < graph.getNumSuccessors(); succNr++) {
                int succ = graph.getSuccessorNode(succNr);
                predecessorBounds[succ + 1]++;
            }
        }
        for (int node = 0; node < numNodes; node++) {
            predecessorBounds[node + 1] += predecessorBounds[node];
        }
        predecessorTargets = new int[predecessorBounds[predecessorBounds.length - 1]];
        for (int node = 0; node < numNodes; node++) {
            graph.queryNode(node);
            for (int succNr = 0; succNr < graph.getNumSuccessors(); succNr++) {
                int succ = graph.getSuccessorNode(succNr);
                predecessorTargets[predecessorBounds[succ]] = node;
                predecessorBounds[succ]++;
            }
        }
        Arrays.fill(predecessorBounds, 0);
        for (int node = 0; node < numNodes; node++) {
            graph.queryNode(node);
            for (int succNr = 0; succNr < graph.getNumSuccessors(); succNr++) {
                int succ = graph.getSuccessorNode(succNr);
                predecessorBounds[succ + 1]++;
            }
        }
        for (int node = 0; node < numNodes; node++) {
            predecessorBounds[node + 1] += predecessorBounds[node];
        }
    }
    
    public void computePredecessors(BitSet states) throws EPMCException {
        predecessorTargets = null;
        predecessorBounds = new int[states.length() + 1];
        for (int node = 0; node < states.length(); node++) {
            if (states.get(node)) {
                graph.queryNode(node);
                for (int succNr = 0; succNr < graph.getNumSuccessors(); succNr++) {
                    int succ = graph.getSuccessorNode(succNr);
                    if (states.get(succ)) {
                        predecessorBounds[succ + 1]++;
                    }
                }
            }
        }
        for (int node = 0; node < states.length(); node++) {
            predecessorBounds[node + 1] += predecessorBounds[node];
        }
        predecessorTargets = new int[predecessorBounds[predecessorBounds.length - 1]];
        for (int node = 0; node < states.length(); node++) {
            if (states.get(node)) {
                graph.queryNode(node);
                for (int succNr = 0; succNr < graph.getNumSuccessors(); succNr++) {
                    int succ = graph.getSuccessorNode(succNr);
                    if (states.get(succ)) {
                        predecessorTargets[predecessorBounds[succ]] = node;
                        predecessorBounds[succ]++;
                    }
                }
            }
        }
        Arrays.fill(predecessorBounds, 0);
        for (int node = 0; node < states.length(); node++) {
            if (states.get(node)) {
                graph.queryNode(node);
                for (int succNr = 0; succNr < graph.getNumSuccessors(); succNr++) {
                    int succ = graph.getSuccessorNode(succNr);
                    if (states.get(succ)) {
                        predecessorBounds[succ + 1]++;
                    }
                }
            }
        }
        for (int node = 0; node < states.length(); node++) {
            predecessorBounds[node + 1] += predecessorBounds[node];
        }
    }

    public void clearPredecessors() {
        predecessorBounds = null;
        predecessorTargets = null;
    }

    public int getNumPredecessors(int currentNode) {
        return predecessorBounds[currentNode + 1] - predecessorBounds[currentNode];
    }

    public int getPredecessorNode(int currentNode, int predecessorNumber) {
        int index = predecessorBounds[currentNode] + predecessorNumber;
        return predecessorTargets[index];
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
        .add(GRAPH_PROPERTIES, graphProperties)
        .add(NODE_PROPERTIES, nodeProperties)
        .add(EDGE_PROPERTIES, edgeProperties)
        .toString();
    }
    
    public void removeGraphProperty(Object property) {
        graphProperties.remove(property);
    }
    
    public void removeNodeProperty(Object property) {
        nodeProperties.remove(property);
    }
    
    public void removeEdgeProperty(Object property) {
        edgeProperties.remove(property);
    }

    public ContextValue getContextValue() {
        return contextValue;
    }    
}
