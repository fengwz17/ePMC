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

package epmc.jani.model;

import epmc.error.EPMCException;
import epmc.prism.exporter.processor.JANI2PRISMProcessorStrict;
import epmc.prism.exporter.processor.ProcessorRegistrar;

public class RateProcessor implements JANI2PRISMProcessorStrict {

	private Rate rate = null;
	
	@Override
	public JANI2PRISMProcessorStrict setElement(Object obj) throws EPMCException {
		assert obj != null;
		assert obj instanceof Rate; 
		
		rate = (Rate) obj;
		return this;
	}

	@Override
	public String toPRISM() throws EPMCException {
		assert rate != null;
		
		return ProcessorRegistrar.getProcessor(rate.getExp())
							     .toPRISM();
	}
	
	@Override
	public void validateTransientVariables() throws EPMCException {
		assert rate != null;
		
		ProcessorRegistrar.getProcessor(rate.getExp())
						  .validateTransientVariables();
	}

	@Override
	public boolean usesTransientVariables() throws EPMCException {
		assert rate != null;
		
		return ProcessorRegistrar.getProcessor(rate.getExp())
								 .usesTransientVariables();
	}	
}
