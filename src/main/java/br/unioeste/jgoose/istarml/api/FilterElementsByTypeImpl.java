package br.unioeste.jgoose.istarml.api;

import br.unioeste.jgoose.istarml.ActorTag;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Leonardo Merlin - leonardo.merlin at unioeste.br
 */
public class FilterElementsByTypeImpl implements FilterElements<ActorTag> {

    public static final FilterElementsByTypeImpl FILTER_AGENTS = new FilterElementsByTypeImpl("agent");
    public static final FilterElementsByTypeImpl FILTER_POSITIONS = new FilterElementsByTypeImpl("position");
    public static final FilterElementsByTypeImpl FILTER_ROLES = new FilterElementsByTypeImpl("role");
    private String type;

    public FilterElementsByTypeImpl(String type) {
        this.type = type;
    }

    /**
     * Return all agents. ActorTag.type == agent.
     *
     * @param actors
     * @return
     */
    @Override
    public Set<ActorTag> filter(Set<ActorTag> actors) {
        Set<ActorTag> result = new HashSet<>();

        for (ActorTag actorTag : actors) {
            if (this.match(actorTag)) {
            }
        }

        return result;
    }

    @Override
    public boolean match(ActorTag candidate) {
        return candidate.getType().equalsIgnoreCase(this.type);
    }
}
