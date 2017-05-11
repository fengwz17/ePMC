/****************************************************************************

    ePMC - an extensible probabilistic model checker
    Copyright (C) 2017

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

*****************************************************************************/

package epmc.dd.jdd;

import epmc.dd.ContextDD;
import epmc.dd.LibraryDD;
import epmc.dd.PermutationLibraryDD;
import epmc.error.EPMCException;
import epmc.options.Options;
import epmc.value.Operator;
import epmc.value.OperatorAnd;
import epmc.value.OperatorEq;
import epmc.value.OperatorId;
import epmc.value.OperatorIff;
import epmc.value.OperatorImplies;
import epmc.value.OperatorIte;
import epmc.value.OperatorNe;
import epmc.value.OperatorNot;
import epmc.value.OperatorOr;
import epmc.value.Type;
import epmc.value.TypeBoolean;
import epmc.value.Value;
import epmc.value.ValueBoolean;
import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;
import jdd.bdd.BDD;
import jdd.bdd.Permutation;

public final class LibraryDDJDD implements LibraryDD {
    public final static String IDENTIFIER = "jdd";
    
    private final static class LowLevelPermutationJDD
    implements PermutationLibraryDD{
        private final Permutation bddPerm;
        
        LowLevelPermutationJDD(Permutation bddPerm) {
            this.bddPerm = bddPerm;
        }
        
        Permutation getPermutation() {
            return bddPerm;
        }
    }
    
    private BDD bdd;
    private ContextDD contextDD;
    private Value oneValue;
    private Value zeroValue;
    private int zeroNode;
    private int oneNode;
    private boolean alive;
    private final TIntList variables = new TIntArrayList();
    
    @Override
    public long apply(String operator, Type type, long... operands)
            throws EPMCException {
        assert alive;
        assert operator != null;
        assert type != null;
        assert TypeBoolean.isBoolean(type);
        for (int opNr = 0; opNr < operands.length; opNr++) {
        	assert operands[opNr] >= 0 : opNr + " " + operands[opNr];
        }
        int result;
        switch (operator) {
        case OperatorId.IDENTIFIER:
        	result = (int) operands[0];
        	break;
        case OperatorNot.IDENTIFIER:
            result = bdd.not((int) operands[0]);
        	break;
        case OperatorAnd.IDENTIFIER:
            result = bdd.and((int) operands[0], (int) operands[1]);
            break;
        case OperatorEq.IDENTIFIER:
            result = bdd.biimp((int) operands[0], (int) operands[1]);
            break;
        case OperatorIff.IDENTIFIER:
            result = bdd.biimp((int) operands[0], (int) operands[1]);
            break;
        case OperatorImplies.IDENTIFIER:
            result = bdd.imp((int) operands[0], (int) operands[1]); 
            break;
        case OperatorNe.IDENTIFIER:
            result = bdd.xor((int) operands[0], (int) operands[1]);
            break;
        case OperatorOr.IDENTIFIER:
            result = bdd.or((int) operands[0], (int) operands[1]);
            break;
        case OperatorIte.IDENTIFIER:
            result = bdd.ite((int) operands[0], (int) operands[1], (int) operands[2]);
            break;
        default:
            assert false;
            return -1;
        }
        bdd.ref(result);
        return result;
    }

    @Override
    public long newConstant(Value value) throws EPMCException {
        assert alive;
        assert value != null;
        assert ValueBoolean.isBoolean(value);
        int result = ValueBoolean.asBoolean(value).getBoolean() ? bdd.getOne() : bdd.getZero();
        bdd.ref(result);
        return result;
    }

    @Override
    public long newVariable() throws EPMCException {
        assert alive;
        int result = bdd.createVar();
        bdd.ref(result);
        variables.add(result);
        return result;
    }

    @Override
    public boolean isLeaf(long dd) {
        assert alive;
        assert dd >= 0;
        return dd == zeroNode || dd == oneNode;
    }

    @Override
    public Value value(long dd) {
        assert alive;
        assert dd == oneNode || dd == zeroNode;
        return dd == oneNode ? oneValue : zeroValue;
    }

    @Override
    public int variable(long dd) {
        assert alive;
        assert dd >= 0;
        assert !isLeaf(dd);
        return bdd.getVar((int) dd);
    }

    @Override
    public void reorder() {
        assert alive;
    }

    @Override
    public void addGroup(int startVariable, int numVariables, boolean fixedOrder) {
        assert alive;
    }

    @Override
    public long permute(long dd, PermutationLibraryDD permutation)
            throws EPMCException {
        assert alive;
        assert dd >= 0;
        assert permutation != null;
        assert permutation instanceof LowLevelPermutationJDD;
        LowLevelPermutationJDD permJDD = (LowLevelPermutationJDD) permutation;
        Permutation bddPerm = permJDD.getPermutation();
        int result = bdd.replace((int) dd, bddPerm);
        bdd.ref(result);
        return result;
    }

    @Override
    public void free(long dd) {
        assert alive;
        assert dd >= 0;
        bdd.deref((int) dd);
    }

    @Override
    public long walkerLow(long dd) {
        assert alive;
        assert dd >= 0;
        return bdd.getLow((int) dd);
    }

    @Override
    public long walkerHigh(long dd) {
        assert alive;
        assert dd >= 0;
        return bdd.getHigh((int) dd);
    }

    @Override
    public boolean isComplement(long node) {
        assert alive;
        return false;
    }

    @Override
    public long walkerComplement(long from) {
        assert alive;
        assert false;
        return -1;
    }

    @Override
    public long walkerRegular(long from) {
        assert alive;
        assert from >= 0;
        return from;
    }

    @Override
    public long abstractExist(long dd, long cube) {
        assert alive;
        assert dd >= 0;
        assert cube >= 0;
        int result = bdd.exists((int) dd, (int) cube);
        bdd.ref(result);
        return result;
    }

    @Override
    public long abstractForall(long dd, long cube) {
        assert alive;
        assert dd >= 0;
        assert cube >= 0;
        int result = bdd.forall((int) dd, (int) cube);
        bdd.ref(result);
        return result;
    }

    @Override
    public long abstractSum(Type type, long dd, long cube) {
        assert alive;
        assert false;
        return -1;
    }

    @Override
    public long abstractProduct(Type type, long dd, long cube) {
        assert alive;
        assert false;
        return -1;
    }

    @Override
    public long abstractMax(Type type, long dd, long cube) throws EPMCException {
        assert alive;
        assert false;
        return -1;
    }

    @Override
    public long abstractMin(Type type, long dd, long cube) throws EPMCException {
        assert alive;
        assert false;
        return -1;
    }

    @Override
    public long abstractAndExist(long dd1, long dd2, long cube)
            throws EPMCException {
        assert alive;
        assert dd1 >= 0;
        assert dd2 >= 0;
        assert cube >= 0;
        int result = bdd.relProd((int) dd1, (int) dd2, (int) cube);
        bdd.ref(result);
        return result;
    }

    @Override
    public PermutationLibraryDD newPermutation(int[] permutation) {
        assert alive;
        assert permutation != null;
        // assuming validity has been checked by high level
        int[] from = new int[permutation.length];
        int[] to = new int[permutation.length];
        for (int index = 0; index < permutation.length; index++) {
            from[index] = variables.get(index);
            to[index] = variables.get(permutation[index]);
        }
        
        Permutation jddPerm = bdd.createPermutation(from, to);
        return new LowLevelPermutationJDD(jddPerm);
    }

    @Override
    public void close() {
        assert alive;
        bdd.cleanup();
        alive = false;
    }

    @Override
    public void setContextDD(ContextDD contextDD) throws EPMCException {
        assert contextDD != null;
        this.contextDD = contextDD;
        int initCache = Options.get().getInteger(OptionsDDJDD.DD_JDD_INIT_CACHE_SIZE);
        int initSlots = Options.get().getInteger(OptionsDDJDD.DD_JDD_INIT_NODES);
        this.bdd = new BDD(initSlots, initCache);
        this.zeroValue = TypeBoolean.get().getFalse();
        this.oneValue = TypeBoolean.get().getTrue();
        this.zeroNode = bdd.getZero();
        this.oneNode = bdd.getOne();
        this.alive = true;
    }

    @Override
    public ContextDD getContextDD() {
        return contextDD;
    }

    @Override
    public long clone(long uniqueId) {
        bdd.ref((int) uniqueId);
        return uniqueId;
    }

    @Override
    public boolean equals(long op1, long op2) {
        return op1 == op2;
    }

    @Override
    public long getWalker(long uniqueId) {
        return uniqueId;
    }

    @Override
    public boolean walkerIsLeaf(long dd) {
        return isLeaf(dd);
    }

    @Override
    public Value walkerValue(long dd) {
        return value(dd);
    }

    @Override
    public int walkerVariable(long dd) {
        return variable(dd);
    }

    @Override
    public boolean walkerIsComplement(long node) {
        return isComplement(node);
    }

    @Override
    public boolean hasInverterArcs() {
        return false;
    }

    @Override
    public int hashCode(long uniqueId) {
        return (int) uniqueId;
    }

    @Override
    public boolean hasAndExist() {
        return true;
    }

    @Override
    public String getIdentifier() {
        return IDENTIFIER;
    }
    
	@Override
	public boolean canApply(String operation, Type resultType, long... operands) {
		if (!TypeBoolean.isBoolean(resultType)) {
			return false;
		}
		switch (operation) {
		case OperatorId.IDENTIFIER:
		case OperatorNot.IDENTIFIER:
		case OperatorAnd.IDENTIFIER:
		case OperatorEq.IDENTIFIER:
		case OperatorIff.IDENTIFIER:
		case OperatorImplies.IDENTIFIER:
		case OperatorNe.IDENTIFIER:
		case OperatorOr.IDENTIFIER:
		case OperatorIte.IDENTIFIER:
			break;
		default:
			return false;
		}
		return true;
	}
}
