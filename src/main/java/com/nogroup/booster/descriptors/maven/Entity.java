
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
    "attributes"
})
public class Entity implements Serializable
{

    @JsonProperty("name")
    private String name;
    @JsonProperty("attributes")
    private List<Attribute_> attributes = new ArrayList<Attribute_>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 336184302812897449L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Entity() {
    }

    /**
     * 
     * @param name
     * @param attributes
     */
    public Entity(String name, List<Attribute_> attributes) {
        super();
        this.name = name;
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

    @JsonProperty("attributes")
    public List<Attribute_> getAttributes() {
        return attributes;
    }

    @JsonProperty("attributes")
    public void setAttributes(List<Attribute_> attributes) {
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
