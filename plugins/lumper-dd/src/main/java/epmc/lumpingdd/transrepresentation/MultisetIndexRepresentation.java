package epmc.lumpingdd.transrepresentation;

import gnu.trove.map.TLongObjectMap;
import gnu.trove.map.hash.TLongObjectHashMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import epmc.dd.ContextDD;
import epmc.dd.DD;
import epmc.dd.VariableDD;
import epmc.dd.Walker;
import epmc.error.EPMCException;
import epmc.graph.CommonProperties;
import epmc.graph.Semantics;
import epmc.graph.SemanticsNonDet;
import epmc.graph.dd.GraphDD;
import epmc.lumpingdd.LumperDDSignature;
import epmc.value.Value;

/**
 * This class can translate a transition relation
 * with doubles to a transition relation with only
 * integers, preventing floating point errors.
 * It keeps a list of all the transition values
 * occurring in the original transition relation.
 * The additional variables indicate the index in that
 * list. The leafs of the ADD indicate the multiplicity
 * of that element.
 */
public class MultisetIndexRepresentation implements TransitionRepresentation {

    private final static String MULTISET_INDEX = "%multiset_index";
    
	private VariableDD multisetVarDD;
	private GraphDD original;
	private ContextDD contextDD;
	private boolean isNonDet;
	private Value[] weights;
	private DD resultCache;

	@Override
	public void setOriginal(GraphDD original) throws EPMCException {
		this.original = original;
		this.contextDD = original.getContextDD();
        Semantics semantics = original.getGraphPropertyObject(CommonProperties.SEMANTICS);
        isNonDet = SemanticsNonDet.isNonDet(semantics);
	}
	
	@Override
	public DD fromTransWeights() throws EPMCException {
		return fromTransWeightsWithActions().abstractSum(original.getActionCube());
	}
	
	private DD fromTransWeightsWithActions() throws EPMCException {
		if(resultCache != null) {
			return resultCache;
		}
		
    	DD edgeWeights = original.getEdgeProperty(CommonProperties.WEIGHT);
    	Set<Value> weightSet = new HashSet<>();
        contextDD.collectValues(weightSet, edgeWeights, contextDD.newConstant(true));
        weightSet.remove(null);
    	weights = new Value[weightSet.size()];
    	weightSet.toArray(weights);
		
    	multisetVarDD = contextDD.newInteger(MULTISET_INDEX, isNonDet ? 2 : 1, 0, weights.length - 1);
    	Map<Value, DD> valueReplacements = new HashMap<>();
    	for (int i = 0; i < weights.length; i++) {
    	    valueReplacements.put(weights[i], multisetVarDD.newIntValue(0, i));
    	}
    	// and with the boolean transition relation to take 0 out of the multiset
    	// we are not interested in actions
        TLongObjectMap<DD> cache = new TLongObjectHashMap<>();
    	resultCache = replaceSymbols(contextDD, edgeWeights.walker(), valueReplacements, cache)
    			.and(original.getTransitions()).toMTWith();
    	LumperDDSignature.disposeCachedDDs(cache);
    	return resultCache;
	}
	
	/**
     * Replace the leafs by DDs according to the relation
     * in replacements.
     */
    private DD replaceSymbols(ContextDD contextDD, Walker w, Map<Value, DD> replacements, TLongObjectMap<DD> computedCache) throws EPMCException {
    	if(computedCache.containsKey(w.uniqueId())) {
    		return computedCache.get(w.uniqueId());
    	}
    	
    	DD result;
        if(w.isLeaf()) {
            result = replacements.get(w.value());
        } else {
            w.low();
            DD low = replaceSymbols(contextDD, w, replacements, computedCache);
            w.back();
            w.high();
            DD high = replaceSymbols(contextDD, w, replacements, computedCache);
            w.back();
            DD thisNode = contextDD.variable(w.variable());
            result = thisNode.ite(high, low);
        }
        computedCache.put(w.uniqueId(), result);
        return result;
    }
}
