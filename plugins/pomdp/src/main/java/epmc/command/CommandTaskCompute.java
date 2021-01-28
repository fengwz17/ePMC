package epmc.command;

import java.math.BigInteger;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import epmc.dd.DD;
import epmc.error.EPMCException;
import epmc.graph.CommonProperties;
import epmc.graph.LowLevel;
import epmc.graph.SemanticsSMG;
import epmc.graph.dd.GraphDD;
import epmc.graph.explicit.GraphExplicit;
import epmc.graph.explicit.NodeProperty;
import epmc.messages.OptionsMessages;
import epmc.modelchecker.CommandTask;
import epmc.modelchecker.Engine;
import epmc.modelchecker.EngineDD;
import epmc.modelchecker.EngineExplicit;
import epmc.modelchecker.ExploreStatistics;
import epmc.modelchecker.Log;
import epmc.modelchecker.Model;
import epmc.modelchecker.ModelChecker;
import epmc.modelchecker.ModelCheckerResult;
import epmc.modelchecker.UtilModelChecker;
import epmc.options.Options;
import epmc.pomdp.model.ModelPOMDP;
import epmc.pomdp.model.*;

public class CommandTaskCompute implements CommandTask {

    public final static String IDENTIFIER = "compute";
    private ModelChecker modelChecker;

    @Override
    public String getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    public void setModelChecker(ModelChecker modelChecker) {
        this.modelChecker = modelChecker;
        Model model = modelChecker.getModel();
        ModelPOMDP modelmodel = (ModelPOMDP)model;
        System.out.println("DEBUG: setModelChecker");
        for(epmc.pomdp.model.Module m : modelmodel.getModules()){
            if(m.isCommands()){
                ModuleCommands mc = (ModuleCommands)m;
                System.out.println("DEBUG: ModuleCommands Name: " + m.getName());
            }
        }
        //int i = 1/0;
    }

    @Override
    public void executeInServer() {
        try {
            explore();
        } catch (EPMCException e) {
            Log log = getLog();
            log.send(e);
        }
    }

    public void explore() {
        Model model = modelChecker.getModel();
        Engine engine = modelChecker.getEngine();
        Log log = getLog();
        
        Set<Object> graphProperties = Collections.singleton(CommonProperties.SEMANTICS);
        Set<Object> nodeProperties = prepareNodeProperties(model);
        Set<Object> edgeProperties = prepareEdgeProperties(model);
        long time = System.nanoTime();
        LowLevel lowLevel = UtilModelChecker.buildLowLevel(modelChecker.getModel(), graphProperties, nodeProperties, edgeProperties);
        time = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - time);
        if (engine instanceof EngineExplicit) {
            GraphExplicit modelGraph = (GraphExplicit) lowLevel;
            int numStates = 0;
            int numTransitions = 0;
            NodeProperty isState = modelGraph.getNodeProperty(CommonProperties.STATE);
            for (int node = 0; node < modelGraph.getNumNodes(); node++) {
                if (isState.getBoolean(node)) {
                    numStates++;
                }
                numTransitions += modelGraph.getNumSuccessors(node);
            }
            // log.send(MessagesCommandCompute.COMPUTING_DONE, time);
            ExploreStatistics exploreStatistics = new ExploreStatistics(modelGraph.getNumNodes(), numStates, numTransitions);
            ModelCheckerResult result = new ModelCheckerResult(null, exploreStatistics);
            log.send(result);
        } else if (engine instanceof EngineDD) {
            System.out.println("COMPUTE: Does not support DD");
            /*GraphDD modelGraphDD = (GraphDD) lowLevel;
            DD space = modelGraphDD.getNodeSpace();
            BigInteger numNodes = space.countSat(modelGraphDD.getPresCube());
            DD states = modelGraphDD.getNodeProperty(CommonProperties.STATE);
            BigInteger numStates = space.and(states)
                    .countSatWith(modelGraphDD.getPresCube().clone());
            BigInteger numTransitions = space.and(modelGraphDD.getTransitions()).abstractExist(modelGraphDD.getActionCube().clone())
                    .countSatWith(modelGraphDD.getPresCube().
                            and(modelGraphDD.getNextCube()));
            ExploreStatistics statistics = new ExploreStatistics(numNodes, numStates, numTransitions);
            ModelCheckerResult result = new ModelCheckerResult(null, statistics);
            space.dispose();
            log.send(result);*/
        } else {
            assert false;
        }
    }

    private Set<Object> prepareNodeProperties(Model model) {
        assert model != null;
        Set<Object> result = new LinkedHashSet<>();
        result.add(CommonProperties.STATE);
        if (!SemanticsSMG.isSMG(model.getSemantics())) {
            result.add(CommonProperties.PLAYER);
        }
        result.add(CommonProperties.STATE);
        return result;
    }

    private Set<Object> prepareEdgeProperties(Model model)
    {
        assert model != null;
        Set<Object> result = new LinkedHashSet<>();
        result.add(CommonProperties.WEIGHT);
        return result;
    }

    private Log getLog() {
        return Options.get().get(OptionsMessages.LOG);
    }

}