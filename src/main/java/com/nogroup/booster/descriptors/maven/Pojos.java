
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
    "embeddeds"
})
public class Pojos implements Serializable
{

    @JsonProperty("embeddeds")
    private List<Embedded> embeddeds = new ArrayList<Embedded>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -2435028832116860396L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Pojos() {
    }

    /**
     * 
     * @param embeddeds
     */
    public Pojos(List<Embedded> embeddeds) {
        super();
        this.embeddeds = embeddeds;
    }

    @JsonProperty("embeddeds")
    public List<Embedded> getEmbeddeds() {
        return embeddeds;
    }

    @JsonProperty("embeddeds")
    public void setEmbeddeds(List<Embedded> embeddeds) {
        this.embeddeds = embeddeds;
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
