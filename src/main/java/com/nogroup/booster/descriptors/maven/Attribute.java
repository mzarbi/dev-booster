
package com.nogroup.booster.descriptors.maven;

import java.io.Serializable;
import java.util.HashMap;
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
    "dataType"
})
public class Attribute implements Serializable
{

    @JsonProperty("name")
    private String name;
    @JsonProperty("dataType")
    private String dataType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -2779663366434819851L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Attribute() {
    }

    /**
     * 
     * @param dataType
     * @param name
     */
    public Attribute(String name, String dataType) {
        super();
        this.name = name;
        this.dataType = dataType;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("dataType")
    public String getDataType() {
        return dataType;
    }

    @JsonProperty("dataType")
    public void setDataType(String dataType) {
        this.dataType = dataType;
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
