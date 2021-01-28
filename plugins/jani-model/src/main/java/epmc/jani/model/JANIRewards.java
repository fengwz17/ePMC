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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonValue;

import epmc.util.UtilJSON;

/**
 * Represents the rewards of a stochastic process
 * 
 * @author Xie Li
 */
public final class JANIRewards implements JANINode, List<JANIReward> {
    /** Maps names of valid variables to valid variables. */
    private Map<String, JANIIdentifier> validIdentifiers;

    /** List of rewards. */
    private final List<JANIReward> jANIRewards = new ArrayList<>();
    private ModelJANI model;

    /**
     * Sets the map of actions of the automaton the edges are part of.
     * The parameter given is a map from variable names to variables.
     * This method must be called exactly once before parsing. It must not be
     * called with a {@code null} parameter or with a parameter containing
     * {@code null} entries.
     * 
     * @param validVariables variables of the model the edges description is part of
     */
    void setValidIdentifiers(Map<String,JANIIdentifier> validVariables) {
        assert this.validIdentifiers == null;
        assert validVariables != null;
        for (Entry<String, JANIIdentifier> entry : validVariables.entrySet()) {
            assert entry.getKey() != null;
            assert entry.getValue() != null;
        }
        this.validIdentifiers = validVariables;
    }


    public JANIRewards(ModelJANI model){
        this.model = model;
    }

    @Override
    public void setModel(ModelJANI model) {
        this.model = model;
    }

    @Override
    public ModelJANI getModel() {
        return model;
    }

    @Override
    public JANINode parse(JsonValue value) {
        assert model != null;
        assert value != null;
        assert validIdentifiers != null;
        JsonArray array = UtilJSON.toArrayObject(value);
        for (JsonValue rewardValue : array) {
            JANIReward jANIReward = new JANIReward();
            jANIReward.setValidIdentifiers(validIdentifiers);
            jANIReward.setModel(model);
            jANIReward.parse(rewardValue);
            jANIRewards.add(jANIReward);
        }
        return this;
    }

    public void addReward(JANIReward jANIReward) {
        jANIRewards.add(jANIReward);
    }

    @Override
    public JsonValue generate() {
        JsonArrayBuilder result = Json.createArrayBuilder();
        for (JANIReward jANIReward : jANIRewards) {
            result.add(jANIReward.generate());
        }
        return result.build();
    }

    @Override
    public Iterator<JANIReward> iterator() {
        return jANIRewards.iterator();
    }	

    @Override
    public int size() {
        return jANIRewards.size();
    }

    @Override
    public String toString() {
        return UtilModelParser.toString(this);
    }

    @Override
    public boolean isEmpty() {
        return jANIRewards.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return jANIRewards.contains(o);
    }

    @Override
    public Object[] toArray() {
        return jANIRewards.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return jANIRewards.toArray(a);
    }

    @Override
    public boolean add(JANIReward o) {
        return jANIRewards.add(o);
    }

    @Override
    public boolean remove(Object o) {
        return jANIRewards.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return jANIRewards.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends JANIReward> c) {
        return jANIRewards.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends JANIReward> c) {
        return jANIRewards.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return jANIRewards.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return jANIRewards.retainAll(c);
    }

    @Override
    public void clear() {
        jANIRewards.clear();
    }

    @Override
    public JANIReward get(int index) {
        return jANIRewards.get(index);
    }

    @Override
    public JANIReward set(int index, JANIReward element) {
        return jANIRewards.set(index, element);
    }

    @Override
    public void add(int index, JANIReward element) {
        jANIRewards.add(index, element);
    }

    @Override
    public JANIReward remove(int index) {
        return jANIRewards.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return jANIRewards.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return jANIRewards.lastIndexOf(o);
    }

    @Override
    public ListIterator<JANIReward> listIterator() {
        return jANIRewards.listIterator();
    }

    @Override
    public ListIterator<JANIReward> listIterator(int index) {
        return jANIRewards.listIterator(index);
    }

    @Override
    public List<JANIReward> subList(int fromIndex, int toIndex) {
        return jANIRewards.subList(fromIndex, toIndex);
    }
}
