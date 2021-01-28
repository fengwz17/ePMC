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

import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import epmc.expression.Expression;
import epmc.expression.standard.ExpressionLiteral;
import epmc.jani.model.expression.ExpressionParser;
import epmc.util.UtilJSON;

public final class JANIReward implements JANINode {
    /** String identifying the action, if given. */
    private final static String ACTION = "action";
    /** String identifying the guard. */
    private final static String GUARD = "guard";
    /** String identifying rewardVal. */
    private final static String REWARDVAL = "rewardval";
    /** String identifying comment of edge. */
    private final static String COMMENT = "comment";
    /** Action of edge reward */
    private Action action;
    /** Guard of the edge reward. */
    private Guard guard;
    /** Expression of the reward */
    private Expression rewardVal;

    /** Model to which this edge belongs to. */
    private ModelJANI model;
    /** Comment for this edge. */
    private String comment;
    /** Possible variables which can be assigned by the edge. */
    private Map<String, JANIIdentifier> validIdentifiers;

    @Override
    public void setModel(ModelJANI model) {
        this.model = model;
    }

    @Override
    public ModelJANI getModel() {
        return model;
    }

    void setValidIdentifiers(Map<String,JANIIdentifier> validVariables) {
        this.validIdentifiers = validVariables;
    }

    @Override
    public JANINode parse(JsonValue value) {
        assert model != null;
        assert value != null;
        UtilJSON.ensureObject(value);
        JsonObject object = (JsonObject) value;
        action = null;
        if (object.get(ACTION) != null) {
            action = UtilJSON.toOneOf(object, ACTION, model.getActionsOrEmpty());
        }
        guard = UtilModelParser.parseOptional(model, () -> {
            Guard guard = new Guard();
            guard.setModel(model);
            guard.setIdentifier(validIdentifiers);
            return guard;
        }, object, GUARD);
        rewardVal = ExpressionParser.parseExpression(model, object.get(REWARDVAL), validIdentifiers);
        comment = UtilJSON.getStringOrNull(object, COMMENT);
        return this;
    }

    @Override
    public JsonValue generate() {
        JsonObjectBuilder result = Json.createObjectBuilder();
        if (action != null) {
            result.add(ACTION, action.getName());
        }
        UtilModelParser.addOptional(result, ACTION, action);
        UtilModelParser.addOptional(result, GUARD, guard);
        result.add(REWARDVAL, ExpressionParser.generateExpression(model, rewardVal));
        UtilJSON.addOptional(result, COMMENT, comment);
        return result.build();
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public boolean isEdgeReward(){
        return this.action != null;
    }

    public boolean isStateReward(){
        return this.action == null;
    }

    public Action getActionOrSilent() {
        Action result;
        if (action == null) {
            result = model.getSilentAction();
        } else {
            result = action;
        }
        return result;
    }

    public Action getAction() {
        return action;
    }

    public void setGuard(Guard guard) {
        this.guard = guard;
    }

    public Guard getGuard() {
        return guard;
    }

    public void setRewardVal(Expression rewardVal){
        this.rewardVal = rewardVal;
    }

    public Expression getRewardVal(){
        return rewardVal;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return UtilModelParser.toString(this);
    }

    public Expression getGuardExpressionOrTrue() {
        if (guard == null) {
            return ExpressionLiteral.getTrue();
        } else {
            return guard.getExp();
        }
    }
}
