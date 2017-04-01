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

package epmc.prism.exporter.processor;

import static epmc.error.UtilError.ensure;

import java.util.HashMap;
import java.util.Map;

import epmc.error.EPMCException;
import epmc.expression.standard.ExpressionCoalition;
import epmc.expression.standard.ExpressionCoalitionProcessor;
import epmc.expression.standard.ExpressionFilter;
import epmc.expression.standard.ExpressionFilterProcessor;
import epmc.expression.standard.ExpressionIdentifierStandard;
import epmc.expression.standard.ExpressionIdentifierStandardProcessor;
import epmc.expression.standard.ExpressionLiteral;
import epmc.expression.standard.ExpressionLiteralProcessor;
import epmc.expression.standard.ExpressionMultiObjective;
import epmc.expression.standard.ExpressionMultiObjectiveProcessor;
import epmc.expression.standard.ExpressionOperator;
import epmc.expression.standard.ExpressionOperatorProcessor;
import epmc.expression.standard.ExpressionQuantifier;
import epmc.expression.standard.ExpressionQuantifierProcessor;
import epmc.expression.standard.ExpressionReward;
import epmc.expression.standard.ExpressionRewardProcessor;
import epmc.expression.standard.ExpressionSteadyState;
import epmc.expression.standard.ExpressionSteadyStateProcessor;
import epmc.expression.standard.ExpressionTemporal;
import epmc.expression.standard.ExpressionTemporalProcessor;
import epmc.expression.standard.TimeBound;
import epmc.expression.standard.TimeBoundProcessor;
import epmc.jani.model.Action;
import epmc.jani.model.ActionProcessor;
import epmc.jani.model.AssignmentSimple;
import epmc.jani.model.AssignmentSimpleProcessor;
import epmc.jani.model.Assignments;
import epmc.jani.model.AssignmentsProcessor;
import epmc.jani.model.Automata;
import epmc.jani.model.AutomataProcessor;
import epmc.jani.model.Automaton;
import epmc.jani.model.AutomatonProcessor;
import epmc.jani.model.Constant;
import epmc.jani.model.ConstantProcessor;
import epmc.jani.model.Constants;
import epmc.jani.model.ConstantsProcessor;
import epmc.jani.model.Destination;
import epmc.jani.model.DestinationProcessor;
import epmc.jani.model.Destinations;
import epmc.jani.model.DestinationsProcessor;
import epmc.jani.model.Edge;
import epmc.jani.model.EdgeProcessor;
import epmc.jani.model.Edges;
import epmc.jani.model.EdgesProcessor;
import epmc.jani.model.Guard;
import epmc.jani.model.GuardProcessor;
import epmc.jani.model.InitialStates;
import epmc.jani.model.InitialStatesProcessor;
import epmc.jani.model.Location;
import epmc.jani.model.LocationProcessor;
import epmc.jani.model.Locations;
import epmc.jani.model.LocationsProcessor;
import epmc.jani.model.Metadata;
import epmc.jani.model.MetadataProcessor;
import epmc.jani.model.ModelJANI;
import epmc.jani.model.ModelJANIProcessor;
import epmc.jani.model.TimeProgress;
import epmc.jani.model.TimeProgressProcessor;
import epmc.jani.model.Variable;
import epmc.jani.model.VariableProcessor;
import epmc.jani.model.Variables;
import epmc.jani.model.VariablesProcessor;
import epmc.jani.model.component.ComponentSynchronisationVectors;
import epmc.jani.model.component.ComponentSynchronisationVectorsProcessor;
import epmc.jani.model.property.ExpressionInitial;
import epmc.jani.model.property.ExpressionInitialProcessor;
import epmc.jani.model.property.JANIProperties;
import epmc.jani.model.property.JANIPropertiesProcessor;
import epmc.jani.model.property.JANIPropertyEntry;
import epmc.jani.model.property.JANIPropertyEntryProcessor;
import epmc.jani.model.type.JANITypeBool;
import epmc.jani.model.type.JANITypeBoolProcessor;
import epmc.jani.model.type.JANITypeBounded;
import epmc.jani.model.type.JANITypeBoundedProcessor;
import epmc.jani.model.type.JANITypeInt;
import epmc.jani.model.type.JANITypeIntProcessor;
import epmc.jani.model.type.JANITypeReal;
import epmc.jani.model.type.JANITypeRealProcessor;
import epmc.jani.type.ctmc.ModelExtensionCTMC;
import epmc.jani.type.ctmc.ModelExtensionCTMCProcessor;
import epmc.jani.type.ctmdp.ModelExtensionCTMDP;
import epmc.jani.type.ctmdp.ModelExtensionCTMDPProcessor;
import epmc.jani.type.dtmc.ModelExtensionDTMC;
import epmc.jani.type.dtmc.ModelExtensionDTMCProcessor;
import epmc.jani.type.lts.ModelExtensionLTS;
import epmc.jani.type.lts.ModelExtensionLTSProcessor;
import epmc.jani.type.ma.ModelExtensionMA;
import epmc.jani.type.ma.ModelExtensionMAProcessor;
import epmc.jani.type.mdp.ModelExtensionMDP;
import epmc.jani.type.mdp.ModelExtensionMDPProcessor;
import epmc.jani.type.smg.ModelExtensionSMG;
import epmc.jani.type.smg.ModelExtensionSMGProcessor;
import epmc.jani.type.smg.PlayerJANI;
import epmc.jani.type.smg.PlayerJANIProcessor;
import epmc.jani.type.smg.PlayersJANI;
import epmc.jani.type.smg.PlayersJANIProcessor;
import epmc.prism.exporter.error.ProblemsPRISMExporter;
import epmc.util.Util;
import epmc.value.ContextValue;

/**
 * Class that is responsible for registering the JANI components and their corresponding JANI2PRISM processors.
 * 
 * @author Andrea Turrini
 *
 */
public class ProcessorRegistrar {
	
	private static final String UNKNOWN_PROCESSOR = "No converter found for";
	
	private static ContextValue contextValue = null;
	
	private static Map<Class<? extends Object>, Class<? extends JANI2PRISMProcessorStrict>> strictProcessors = registerStrictProcessors();
	private static Map<Class<? extends Object>, Class<? extends JANI2PRISMProcessorExtended>> extendedProcessors = registerExtendedProcessors();
	
	private static boolean allowMultipleLocations = false;
	private static boolean useExtendedSyntax = false;
	
	public static void setContextValue(ContextValue contextValue) {
		ProcessorRegistrar.contextValue = contextValue;
	}
	
	/**
	 * Add a new processor for a JANI component in the set of known strict processors.
	 * 
	 * @param JANIComponent the JANI component to which associate the processor
	 * @param JANI2PRISMProcessor the corresponding processor
	 */
	public static void registerStrictProcessor(Class<? extends Object> JANIComponent, Class<? extends JANI2PRISMProcessorStrict> JANI2PRISMProcessor) {
		assert !JANI2PRISMProcessorExtended.class.isAssignableFrom(JANI2PRISMProcessor);
		
		strictProcessors.put(JANIComponent, JANI2PRISMProcessor);
	}
	
	/**
	 * Add a new processor for a JANI component in the set of known extended processors.
	 * 
	 * @param JANIComponent the JANI component to which associate the processor
	 * @param JANI2PRISMProcessor the corresponding processor
	 */
	public static void registerExtendedProcessor(Class<? extends Object> JANIComponent, Class<? extends JANI2PRISMProcessorExtended> JANI2PRISMProcessor) {
		extendedProcessors.put(JANIComponent, JANI2PRISMProcessor);
	}
	
	/**
	 * Return the processor associated to the given JANI component.
	 * 
	 * @param JANIComponent the JANI component for which obtain the processor
	 * @return the corresponding processor
	 * @throws EPMCException if no processor is associated to the given JANI component
	 */
	public static JANI2PRISMProcessorStrict getProcessor(Object JANIComponent) throws EPMCException {
		assert JANIComponent != null;
		
		JANI2PRISMProcessorStrict processor = null;
		Class<? extends JANI2PRISMProcessorStrict> processorClass = strictProcessors.get(JANIComponent.getClass());
		if (processorClass != null) {
			processor = Util.getInstance(processorClass);
			processor.setContextValue(contextValue);
			processor.setElement(JANIComponent);
		} else {
			processorClass = extendedProcessors.get(JANIComponent.getClass());
			if (processorClass != null) {
				processor = Util.getInstance(processorClass);
				processor.setContextValue(contextValue);
				processor.setElement(JANIComponent);
				ensure(useExtendedSyntax, ProblemsPRISMExporter.PRISM_EXPORTER_UNSUPPORTED_INPUT_FEATURE, processor.getUnsupportedFeature().toArray());
			} else {
				ensure(false, ProblemsPRISMExporter.PRISM_EXPORTER_UNSUPPORTED_INPUT_FEATURE, UNKNOWN_PROCESSOR, JANIComponent.getClass().getSimpleName());
			}
		}
		
		return processor;
	}
	
	/**
	 * Allow to use the extended PRISM Syntax
	 */
	public static void useExtendedPRISMSyntax() {
		useExtendedSyntax = true;
	}
	
	public static void setAllowMultipleLocations(boolean allowMultipleLocations) {
		ProcessorRegistrar.allowMultipleLocations = allowMultipleLocations;
	}
	
	public static boolean getAllowMultipleLocations() {
		return allowMultipleLocations;
	}

	private static Map<Class<? extends Object>, Class<? extends JANI2PRISMProcessorStrict>> registerStrictProcessors() {
		Map<Class<? extends Object>, Class<? extends JANI2PRISMProcessorStrict>> processors = new HashMap<>();
		
		//Semantic types
		processors.put(ModelExtensionCTMC.class, ModelExtensionCTMCProcessor.class);
		processors.put(ModelExtensionCTMDP.class, ModelExtensionCTMDPProcessor.class);
		processors.put(ModelExtensionDTMC.class, ModelExtensionDTMCProcessor.class);
		processors.put(ModelExtensionMDP.class, ModelExtensionMDPProcessor.class);
		
		//JANI types
		processors.put(JANITypeBool.class, JANITypeBoolProcessor.class);
		processors.put(JANITypeBounded.class, JANITypeBoundedProcessor.class);
		processors.put(JANITypeInt.class, JANITypeIntProcessor.class);
		processors.put(JANITypeReal.class, JANITypeRealProcessor.class);
		
		//JANI metadata
		processors.put(Metadata.class, MetadataProcessor.class);
		
		//JANI model
		processors.put(ModelJANI.class, ModelJANIProcessor.class);
		
		//Constants
		processors.put(Constants.class, ConstantsProcessor.class);
		processors.put(Constant.class, ConstantProcessor.class);
		
		//Variables
		processors.put(Variables.class, VariablesProcessor.class);
		processors.put(Variable.class, VariableProcessor.class);
		
		//Initial states
		processors.put(InitialStates.class, InitialStatesProcessor.class);
		
		//Automata
		processors.put(Automata.class, AutomataProcessor.class);
		processors.put(Automaton.class, AutomatonProcessor.class);
		
		//Synchronisation vectors
		processors.put(ComponentSynchronisationVectors.class, ComponentSynchronisationVectorsProcessor.class);
		
		//Locations
		processors.put(Locations.class, LocationsProcessor.class);
		processors.put(Location.class, LocationProcessor.class);
		
		//Time progress/invariants
		processors.put(TimeProgress.class, TimeProgressProcessor.class);
		
		//Time bounds
		processors.put(TimeBound.class, TimeBoundProcessor.class);
		
		//Edges
		processors.put(Edges.class, EdgesProcessor.class);
		processors.put(Edge.class, EdgeProcessor.class);
		
		//Actions
		processors.put(Action.class, ActionProcessor.class);
		
		//Destinations
		processors.put(Destinations.class, DestinationsProcessor.class);
		processors.put(Destination.class, DestinationProcessor.class);
		
		//Guards
		processors.put(Guard.class, GuardProcessor.class);
		
		//Assignments
		processors.put(Assignments.class, AssignmentsProcessor.class);
		processors.put(AssignmentSimple.class, AssignmentSimpleProcessor.class);
		
		//Expressions
		processors.put(ExpressionOperator.class, ExpressionOperatorProcessor.class);
		processors.put(ExpressionIdentifierStandard.class, ExpressionIdentifierStandardProcessor.class);
		processors.put(ExpressionLiteral.class, ExpressionLiteralProcessor.class);
		processors.put(ExpressionFilter.class, ExpressionFilterProcessor.class);
		processors.put(ExpressionQuantifier.class, ExpressionQuantifierProcessor.class);
		processors.put(ExpressionTemporal.class, ExpressionTemporalProcessor.class);
		processors.put(ExpressionReward.class, ExpressionRewardProcessor.class);
		processors.put(ExpressionSteadyState.class, ExpressionSteadyStateProcessor.class);
		processors.put(ExpressionInitial.class, ExpressionInitialProcessor.class);
		processors.put(ExpressionMultiObjective.class, ExpressionMultiObjectiveProcessor.class);
		
		//JANI properties
		processors.put(JANIProperties.class, JANIPropertiesProcessor.class);
		processors.put(JANIPropertyEntry.class, JANIPropertyEntryProcessor.class);
		
		return processors;
	}
	
	private static Map<Class<? extends Object>, Class<? extends JANI2PRISMProcessorExtended>> registerExtendedProcessors() {
		Map<Class<? extends Object>, Class<? extends JANI2PRISMProcessorExtended>> processors = new HashMap<>();
		
		//Semantic types
		processors.put(ModelExtensionLTS.class, ModelExtensionLTSProcessor.class);
		processors.put(ModelExtensionMA.class, ModelExtensionMAProcessor.class);
		processors.put(ModelExtensionSMG.class, ModelExtensionSMGProcessor.class);
		
		//Expressions
		processors.put(ExpressionCoalition.class, ExpressionCoalitionProcessor.class);
		
		//SMG players
		processors.put(PlayersJANI.class, PlayersJANIProcessor.class);
		processors.put(PlayerJANI.class, PlayerJANIProcessor.class);
		
		return processors;
	}
	
}