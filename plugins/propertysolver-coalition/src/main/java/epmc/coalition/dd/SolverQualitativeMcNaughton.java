package epmc.coalition.dd;

import java.util.ArrayList;
import java.util.List;

import epmc.algorithms.dd.ComponentsDD;
import epmc.automaton.AutomatonParity;
import epmc.automaton.AutomatonParityLabel;
import epmc.automaton.ProductGraphDDExplicit;
import epmc.dd.ContextDD;
import epmc.dd.DD;
import epmc.error.EPMCException;
import epmc.graph.CommonProperties;
import epmc.graph.Player;
import epmc.graph.dd.GraphDD;
import gnu.trove.map.TObjectIntMap;

public final class SolverQualitativeMcNaughton implements SolverQualitative {
	public final static String IDENTIFIER = "schewe";

    private DDPair EMPTY_BIT_SET_PAIR;
	private GraphDD game;
	private boolean strictEven;

	private List<DD> priorities;

	private DD playerEven;

	private DD playerOdd;

	private int zeroMcNaughtonCalls;

	@Override
	public String getIdentifier() {
		return IDENTIFIER;
	}

	@Override
	public void setGame(GraphDD game) {
		this.game = game;
	}

	@Override
	public void setStrictEven(boolean strictEven) {
		this.strictEven = strictEven;		
	}

	@Override
	public void setComputeStrategies(boolean playerEven, boolean playerOdd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DDPair solve() throws EPMCException {
        this.EMPTY_BIT_SET_PAIR = new DDPair(getContextDD().newConstant(false), getContextDD().newConstant(false));
		ProductGraphDDExplicit product = (ProductGraphDDExplicit) game;
		AutomatonParity automaton = (AutomatonParity) product.getAutomaton();
        this.priorities = computePriorities(automaton, product.getLabeling());
        DD player = game.getNodeProperty(CommonProperties.PLAYER);
        playerEven = player.clone().eqWith(getContextDD().newConstant(Player.ONE_STOCHASTIC));
        playerOdd = player.clone().eqWith(getContextDD().newConstant(Player.TWO_STOCHASTIC));
        DD nodes = game.getNodeSpace().clone();
        DDPair result = zeroMcNaughton(nodes);
        return result;
	}
	
    private List<DD> computePriorities(AutomatonParity automaton,
            TObjectIntMap<DD> labelsMap) throws EPMCException {
    	assert automaton != null;
        assert labelsMap != null;

        List<DD> priorities = new ArrayList<>();
        int numPriorities = automaton.getNumPriorities();
        priorities.clear();
        
        for (int labelNr = 0; labelNr < numPriorities; labelNr++) {
            priorities.add(getContextDD().newConstant(false));
        }

        for (DD labelDD : labelsMap.keySet()) {
            int labelNr = labelsMap.get(labelDD);
            AutomatonParityLabel label = (AutomatonParityLabel) automaton.numberToLabel(labelNr);
            int priority = label.getPriority();
            if (priority != Integer.MAX_VALUE) {
                priorities.set(priority, priorities.get(priority).orWith(labelDD.clone()));
            }
        }
        return priorities;
    }

    private DDPair zeroMcNaughton(DD p) throws EPMCException {
        zeroMcNaughtonCalls++;
        // note that p might be destroyed by call to this algorithm
        assert p != null;
        if (p.isFalse()) {
            return EMPTY_BIT_SET_PAIR;
        }
        int minPriority = Integer.MAX_VALUE;
        boolean allEven = true;
        boolean allOdd = true;
        for (int parity = priorities.size() - 1; parity >= 0; parity--) {
            if (!p.and(priorities.get(parity)).isFalseWith()) {
                minPriority = parity;
                if (parity % 2 == 0) {
                    allOdd = false;
                } else {
                    allEven = false;
                }
            }
        }
        assert !allEven || !allOdd;
        if (allEven) {
            return new DDPair(p, getContextDD().newConstant(false));
        } else if (allOdd) {
            return new DDPair(getContextDD().newConstant(false), p);
        }
        DD minPrioDD = priorities.get(minPriority);
        
        DD mapToMinPriority = p.and(minPrioDD);
        DD pPrimed = null;
        
        assert minPriority >= 0 : minPriority;
        if (minPriority % 2 == 0) {
            DD w1 = getContextDD().newConstant(false);
            do {
                pPrimed = p.clone();
                pPrimed = pPrimed.andNot(satr0(mapToMinPriority, p));
                DD wPrimed1 = zeroMcNaughton(pPrimed).getSet1();
                if (wPrimed1.isFalse()) {
                    DD w0 = p.clone();
                    w0 = w0.andNot(w1);
                    return new DDPair(w0, w1);
                }
                DD atr1 = !strictEven ? watr1(wPrimed1, p) : satr1(wPrimed1, p);
                w1 = w1.or(atr1);
                p = p.andNot(atr1);
            } while (true);
        } else {
            assert minPriority % 2 == 1 : minPriority;
            DD w0 = getContextDD().newConstant(false);
            do {
                pPrimed = p.clone();
                pPrimed = pPrimed.andNot(satr1(mapToMinPriority, p));
                DD wPrimed0 = zeroMcNaughton(pPrimed).getSet0();
                if (wPrimed0.isFalse()) {
                    DD w1 = p.clone();
                    w1 = w1.andNot(w0);
                    return new DDPair(w0, w1);
                }
                DD atr0 = strictEven ? watr0(wPrimed0, p) : satr0(wPrimed0, p);
                w0 = w0.or(atr0);
                p = p.andNot(atr0);
            } while (true);
        }
    }

    private DD satr0(DD target, DD nodes) throws EPMCException {
        DD exist = playerEven;
        DD forall = getContextDD().newConstant(false);
        DD forallExist = playerOdd;
        DD existForall = getContextDD().newConstant(false);
        
        DD result = ComponentsDD.attract(game, target, nodes, forall, exist, forallExist, existForall);
        forall.dispose();
        existForall.dispose();
        return result;
    }

    private DD satr1(DD target, DD nodes) throws EPMCException {
        DD exist = playerOdd;
        DD forall = getContextDD().newConstant(false);
        DD forallExist = playerEven;
        DD existForall = getContextDD().newConstant(false);
        
        DD result = ComponentsDD.attract(game, target, nodes, forall, exist, forallExist, existForall);
        existForall.dispose();
        forall.dispose();
        return result;
    }
    
    private DD watr0(DD target, DD nodes) throws EPMCException {
        return watr(target, nodes, false);
    }

    private DD watr1(DD target, DD nodes) throws EPMCException {
        return watr(target, nodes, true);
    }
    
    private DD satr(DD target, DD nodes, boolean odd)
            throws EPMCException {
        return odd ? satr1(target, nodes) : satr0(target, nodes);
    }

    private DD watr(DD target, DD nodes, boolean odd)
            throws EPMCException {
        DD satrSame = satr(target, nodes, odd);
        DD nodesMSatrSame = getContextDD().newConstant(false);
        DD nodesMTarget = getContextDD().newConstant(false);
        DD satrOther = getContextDD().newConstant(false);
        DD nodesMSatrOther = getContextDD().newConstant(false);
        nodes = nodes.clone();
        while (!nodes.equals(satrSame)) {
            nodesMSatrSame = nodes.andNot(satrSame);
            nodesMTarget = nodes.clone();
            nodesMSatrSame = nodesMSatrSame.andNot(target);
            satrOther = satr(nodesMSatrSame, nodesMTarget, !odd);
            nodesMSatrOther = nodes.andNot(satrOther);
            nodes = nodesMSatrOther.clone();
            satrSame = satr(target, nodes, odd);            
        }
        return satrSame;
    }

    public ContextDD getContextDD() throws EPMCException {
    	return game.getContextDD();
	}
}
