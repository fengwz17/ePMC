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

package epmc.expression.standard;

import epmc.error.EPMCException;
import epmc.expression.Expression;
import epmc.prism.exporter.processor.JANI2PRISMProcessorStrict;
import epmc.prism.exporter.processor.ProcessorRegistrar;

public class ExpressionRewardProcessor implements JANI2PRISMProcessorStrict {

	private ExpressionReward reward = null;
	
	@Override
	public JANI2PRISMProcessorStrict setElement(Object obj) throws EPMCException {
		assert obj != null;
		assert obj instanceof ExpressionReward; 
		
		reward = (ExpressionReward) obj;
		return this;
	}

	@Override
	public String toPRISM() throws EPMCException {
		assert reward != null;
		
		StringBuilder prism = new StringBuilder();
		
		RewardType type = reward.getRewardType();
        prism.append(type.toString());
        switch(type) {
        case REACHABILITY:
        	prism.append("(")
        	     .append(ProcessorRegistrar.getProcessor(reward.getRewardReachSet())
        	    		 				   .toPRISM())
        	     .append(")");
        	break;
        case INSTANTANEOUS:
        case CUMULATIVE:
        	prism.append("(")
        	     .append(ProcessorRegistrar.getProcessor(reward.getTime())
        	    		 				   .toPRISM())
        	     .append(")");
        	break;
        case DISCOUNTED:
            prism.append(ProcessorRegistrar.getProcessor(reward.getTime())
            							   .toPRISM())
                 .append(",")
                 .append(ProcessorRegistrar.getProcessor(reward.getDiscount())
                		 				   .toPRISM());
        	break;
    	default:
        }

		return prism.toString();
	}

	@Override
	public void validateTransientVariables() throws EPMCException {
		assert reward != null;
		
		for (Expression child : reward.getChildren()) {
			ProcessorRegistrar.getProcessor(child)
							  .validateTransientVariables();
		}
	}
	
	@Override
	public boolean usesTransientVariables() throws EPMCException {
		assert reward != null;
		
		boolean usesTransient = false;
		for (Expression child : reward.getChildren()) {
			usesTransient |= ProcessorRegistrar.getProcessor(child)
											   .usesTransientVariables();
		}
		
		return usesTransient;
	}	
}
