
package com.nogroup.booster.descriptors.maven;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "super_",
    "attributes"
})
public class Embedded implements Serializable
{

    @JsonProperty("name")
    private String name;
    @JsonProperty("super_")
    private Object _super;
    @JsonProperty("attributes")
    private List<Attribute> attributes = new ArrayList<Attribute>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 8693399138957221910L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Embedded() {
    }

    /**
     * 
     * @param name
     * @param attributes
     * @param _super
     */
    public Embedded(String name, Object _super, List<Attribute> attributes) {
        super();
        this.name = name;
        this._super = _super;
        this.attributes = attributes;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("super_")
    public Object getSuper() {
        return _super;
    }

    @JsonProperty("super_")
    public void setSuper(Object _super) {
        this._super = _super;
    }

    @JsonProperty("attributes")
    public List<Attribute> getAttributes() {
        return attributes;
    }

    @JsonProperty("attributes")
    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
