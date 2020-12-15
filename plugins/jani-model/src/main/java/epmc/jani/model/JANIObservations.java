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
 * Represents the set of observations of an automaton.
 * 
 * @author Andrea Turrini
 */
public final class JANIObservations implements JANINode, List<JANIObservation> {
    /** Maps names of valid variables to valid variables. */
    private Map<String, JANIIdentifier> validIdentifiers;
    /** Maps names of valid locations to valid locations. */
    private Map<String, Location> validLocations;

    /** List of edges. */
    private final List<JANIObservation> jANIObservations = new ArrayList<>();
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

    /**
     * Set locations of the automaton the edges belong to.
     * 
     * @param locations locations of the automaton the edges belong to
     */
    void setValidLocations(Map<String,Location> locations) {
        assert this.validLocations == null;
        assert locations != null;
        for (Entry<String, Location> entry : locations.entrySet()) {
            assert entry.getKey() != null;
            assert entry.getValue() != null;
        }
        this.validLocations = locations;
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
        assert validLocations != null;
        JsonArray array = UtilJSON.toArrayObject(value);
        for (JsonValue observationValue : array) {
            JANIObservation jANIObservation = new JANIObservation();
            jANIObservation.setValidIdentifiers(validIdentifiers);
            jANIObservation.setValidLocations(validLocations);
            jANIObservation.setModel(model);
            jANIObservation.parse(observationValue);
            jANIObservations.add(jANIObservation);
        }
        return this;
    }

    public void addObservation(JANIObservation jANIObservation) {
        jANIObservations.add(jANIObservation);
    }

    @Override
    public JsonValue generate() {
        JsonArrayBuilder result = Json.createArrayBuilder();
        for (JANIObservation jANIObservation : jANIObservations) {
            result.add(jANIObservation.generate());
        }
        return result.build();
    }

    @Override
    public Iterator<JANIObservation> iterator() {
        return jANIObservations.iterator();
    }	

    @Override
    public int size() {
        return jANIObservations.size();
    }

    @Override
    public String toString() {
        return UtilModelParser.toString(this);
    }

    @Override
    public boolean isEmpty() {
        return jANIObservations.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return jANIObservations.contains(o);
    }

    @Override
    public Object[] toArray() {
        return jANIObservations.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return jANIObservations.toArray(a);
    }

    @Override
    public boolean add(JANIObservation o) {
        return jANIObservations.add(o);
    }

    @Override
    public boolean remove(Object o) {
        return jANIObservations.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return jANIObservations.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends JANIObservation> c) {
        return jANIObservations.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends JANIObservation> c) {
        return jANIObservations.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return jANIObservations.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return jANIObservations.retainAll(c);
    }

    @Override
    public void clear() {
        jANIObservations.clear();
    }

    @Override
    public JANIObservation get(int index) {
        return jANIObservations.get(index);
    }

    @Override
    public JANIObservation set(int index, JANIObservation element) {
        return jANIObservations.set(index, element);
    }

    @Override
    public void add(int index, JANIObservation element) {
        jANIObservations.add(index, element);
    }

    @Override
    public JANIObservation remove(int index) {
        return jANIObservations.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return jANIObservations.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return jANIObservations.lastIndexOf(o);
    }

    @Override
    public ListIterator<JANIObservation> listIterator() {
        return jANIObservations.listIterator();
    }

    @Override
    public ListIterator<JANIObservation> listIterator(int index) {
        return jANIObservations.listIterator(index);
    }

    @Override
    public List<JANIObservation> subList(int fromIndex, int toIndex) {
        return jANIObservations.subList(fromIndex, toIndex);
    }
}
