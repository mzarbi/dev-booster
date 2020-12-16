
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
    "factors",
    "pojos",
    "entities"
})
public class Data implements Serializable
{

    @JsonProperty("factors")
    private List<Factor> factors = new ArrayList<Factor>();
    @JsonProperty("pojos")
    private Pojos pojos;
    @JsonProperty("entities")
    private List<Entity> entities = new ArrayList<Entity>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -4765261063583762556L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param pojos
     * @param entities
     * @param factors
     */
    public Data(List<Factor> factors, Pojos pojos, List<Entity> entities) {
        super();
        this.factors = factors;
        this.pojos = pojos;
        this.entities = entities;
    }

    @JsonProperty("factors")
    public List<Factor> getFactors() {
        return factors;
    }

    @JsonProperty("factors")
    public void setFactors(List<Factor> factors) {
        this.factors = factors;
    }

    @JsonProperty("pojos")
    public Pojos getPojos() {
        return pojos;
    }

    @JsonProperty("pojos")
    public void setPojos(Pojos pojos) {
        this.pojos = pojos;
    }

    @JsonProperty("entities")
    public List<Entity> getEntities() {
        return entities;
    }

    @JsonProperty("entities")
    public void setEntities(List<Entity> entities) {
        this.entities = entities;
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
