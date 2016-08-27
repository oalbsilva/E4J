package br.unioeste.jgoose.istarml;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;

/**
 * An intentional element is an entity which allows to relate different actors
 * conforming a social network or, also, to express the internal rationality of
 * an actor.
 * <p>Broadly used types of intentional elements are:
 * <ul>
 * <li>goal</li>
 * <li>softgoal</li>
 * <li>resource</li>
 * <li>task</li>
 * </ul>
 * </p>
 *
 * @author Leonardo
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ielement")
public class IElementTag {

    @XmlAttribute
    @XmlID
    private String id;
    @XmlAttribute
    private String name;
    @XmlAnyAttribute
    protected Map<QName, String> extraAttrs = new HashMap<>();
    @XmlAttribute
    private String type; // goal, softgoal, task, resources, other*
    @XmlAttribute
    private String state; // undecided, satisfied, weakly satisfied, denied, weakly denied, other*
    @XmlAttribute
    private String iref;
    @XmlElement(required = false)
    private GraphicTag graphicNode;
    @XmlAnyElement(lax=true)
    private Set<IElementLinkTag> links;
    @XmlElement
    private DependencyTag dependency;

    public IElementTag() {
        this.links = new HashSet<>();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<QName, String> getExtraAttrs() {
        return extraAttrs;
    }

    public void setExtraAttrs(Map<QName, String> extraAttrs) {
        this.extraAttrs = extraAttrs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIref() {
        return iref;
    }

    public void setIref(String iref) {
        this.iref = iref;
    }

    public GraphicTag getGraphicNode() {
        return graphicNode;
    }

    public void setGraphicNode(GraphicTag graphicNode) {
        this.graphicNode = graphicNode;
    }

    public Set<IElementLinkTag> getLinks() {
        return links;
    }

    public void setLinks(Set<IElementLinkTag> links) {
        this.links = links;
    }

    public DependencyTag getDependency() {
        return dependency;
    }

    public void setDependency(DependencyTag dependency) {
        this.dependency = dependency;
    }

    /**
     *
     * @param attributeName - Can not be 'id', 'name', 'author' or other class
     * attribute name.
     * @return the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public String getAttributeValue(String attributeName) {
        if ((attributeName.equals("id") || (attributeName.equals("name"))
                || attributeName.equals("author"))) {
            throw new IllegalArgumentException("Can not be 'id', 'name', 'author' or other class attribute name.");
        }
        return this.extraAttrs.get(new QName(attributeName));
    }

    public void setAttribute(String name, String value) {
        this.extraAttrs.put(new QName(name), value);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IElementTag other = (IElementTag) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
}
