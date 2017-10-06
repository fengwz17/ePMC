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

package epmc.value.operatorevaluator;

import epmc.value.Operator;
import epmc.value.OperatorEvaluator;
import epmc.value.Type;
import epmc.value.TypeAlgebra;
import epmc.value.TypeInteger;
import epmc.value.TypeReal;
import epmc.value.Value;
import epmc.value.ValueAlgebra;
import epmc.value.ValueNumber;
import epmc.value.operator.OperatorCeil;

public enum OperatorEvaluatorCeilDouble implements OperatorEvaluator {
    INSTANCE;

    @Override
    public Operator getOperator() {
        return OperatorCeil.CEIL;
    }

    @Override
    public boolean canApply(Type... types) {
        assert types != null;
        for (Type type : types) {
            assert type != null;
        }
        if (types.length != 1) {
            return false;
        }
        for (Type type : types) {
            if (!TypeAlgebra.isAlgebra(type)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Type resultType(Type... types) {
        assert types != null;
        for (Type type : types) {
            assert type != null;
        }
        Type result;
        if (!(TypeReal.isReal(types[0]) || TypeInteger.isInteger(types[0]))) {
            return null;
        }
        result = TypeInteger.get();
        return result;
    }

    @Override
    public void apply(Value result, Value... operands) {
        assert result != null;
        assert operands != null;
        for (Value operand : operands) {
            assert operand != null;
        }
        double value = ValueNumber.asNumber(operands[0]).getDouble();
        int ceil = (int) Math.ceil(value);
        ValueAlgebra.asAlgebra(result).set(ceil);
    }
}
