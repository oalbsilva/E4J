package br.unioeste.jgoose.util;

import java.util.Properties;

/**
 *
 * @author Leonardo Merlin - leonardo.merlin at unioeste.br
 */
public class IStarElement {

    private String tagname;
    private Properties attributes;

    public IStarElement(String tagname, String label, String type) {
        this(tagname, label, type, new Properties());

    }

    public IStarElement(String tagname, String label, String type, Properties attributes) {
        this.tagname = tagname;
        this.attributes = attributes;
        if (this.attributes == null) {
            this.attributes = new Properties();
        }
        this.attributes.put("label", label);
        this.attributes.put("type", type);
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }
    
    public Properties getAttributes() {
        return attributes;
    }

    public void setAttributes(Properties attributes) {
        this.attributes = attributes;
    }
}
