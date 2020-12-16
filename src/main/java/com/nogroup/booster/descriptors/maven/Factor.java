
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
    "values"
})
public class Factor implements Serializable
{

    @JsonProperty("name")
    private String name;
    @JsonProperty("values")
    private List<String> values = new ArrayList<String>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -6114911740824573883L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Factor() {
    }

    /**
     * 
     * @param values
     * @param name
     */
    public Factor(String name, List<String> values) {
        super();
        this.name = name;
        this.values = values;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("values")
    public List<String> getValues() {
        return values;
    }

    @JsonProperty("values")
    public void setValues(List<String> values) {
        this.values = values;
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
