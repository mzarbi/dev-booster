
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
    "parentFolder",
    "groupId",
    "artifactId",
    "pomTemplate",
    "propertiesTemplate",
    "dockerfileTemplate",
    "dockerComposeTemplate",
    "k8DeploymentTemplate",
    "namespaceTemplate",
    "data"
})
public class MavenDescriptors implements Serializable
{

    @JsonProperty("parentFolder")
    private String parentFolder;
    @JsonProperty("groupId")
    private String groupId;
    @JsonProperty("artifactId")
    private String artifactId;
    @JsonProperty("pomTemplate")
    private String pomTemplate;
    @JsonProperty("propertiesTemplate")
    private String propertiesTemplate;
    @JsonProperty("dockerfileTemplate")
    private String dockerfileTemplate;
    @JsonProperty("dockerComposeTemplate")
    private String dockerComposeTemplate;
    @JsonProperty("k8DeploymentTemplate")
    private String k8DeploymentTemplate;
    @JsonProperty("namespaceTemplate")
    private String namespaceTemplate;
    @JsonProperty("data")
    private Data data;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 5101570772622626540L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MavenDescriptors() {
    }

    /**
     * 
     * @param k8DeploymentTemplate
     * @param parentFolder
     * @param namespaceTemplate
     * @param dockerComposeTemplate
     * @param data
     * @param pomTemplate
     * @param groupId
     * @param dockerfileTemplate
     * @param artifactId
     * @param propertiesTemplate
     */
    public MavenDescriptors(String parentFolder, String groupId, String artifactId, String pomTemplate, String propertiesTemplate, String dockerfileTemplate, String dockerComposeTemplate, String k8DeploymentTemplate, String namespaceTemplate, Data data) {
        super();
        this.parentFolder = parentFolder;
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.pomTemplate = pomTemplate;
        this.propertiesTemplate = propertiesTemplate;
        this.dockerfileTemplate = dockerfileTemplate;
        this.dockerComposeTemplate = dockerComposeTemplate;
        this.k8DeploymentTemplate = k8DeploymentTemplate;
        this.namespaceTemplate = namespaceTemplate;
        this.data = data;
    }

    @JsonProperty("parentFolder")
    public String getParentFolder() {
        return parentFolder;
    }

    @JsonProperty("parentFolder")
    public void setParentFolder(String parentFolder) {
        this.parentFolder = parentFolder;
    }

    @JsonProperty("groupId")
    public String getGroupId() {
        return groupId;
    }

    @JsonProperty("groupId")
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @JsonProperty("artifactId")
    public String getArtifactId() {
        return artifactId;
    }

    @JsonProperty("artifactId")
    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    @JsonProperty("pomTemplate")
    public String getPomTemplate() {
        return pomTemplate;
    }

    @JsonProperty("pomTemplate")
    public void setPomTemplate(String pomTemplate) {
        this.pomTemplate = pomTemplate;
    }

    @JsonProperty("propertiesTemplate")
    public String getPropertiesTemplate() {
        return propertiesTemplate;
    }

    @JsonProperty("propertiesTemplate")
    public void setPropertiesTemplate(String propertiesTemplate) {
        this.propertiesTemplate = propertiesTemplate;
    }

    @JsonProperty("dockerfileTemplate")
    public String getDockerfileTemplate() {
        return dockerfileTemplate;
    }

    @JsonProperty("dockerfileTemplate")
    public void setDockerfileTemplate(String dockerfileTemplate) {
        this.dockerfileTemplate = dockerfileTemplate;
    }

    @JsonProperty("dockerComposeTemplate")
    public String getDockerComposeTemplate() {
        return dockerComposeTemplate;
    }

    @JsonProperty("dockerComposeTemplate")
    public void setDockerComposeTemplate(String dockerComposeTemplate) {
        this.dockerComposeTemplate = dockerComposeTemplate;
    }

    @JsonProperty("k8DeploymentTemplate")
    public String getK8DeploymentTemplate() {
        return k8DeploymentTemplate;
    }

    @JsonProperty("k8DeploymentTemplate")
    public void setK8DeploymentTemplate(String k8DeploymentTemplate) {
        this.k8DeploymentTemplate = k8DeploymentTemplate;
    }

    @JsonProperty("namespaceTemplate")
    public String getNamespaceTemplate() {
        return namespaceTemplate;
    }

    @JsonProperty("namespaceTemplate")
    public void setNamespaceTemplate(String namespaceTemplate) {
        this.namespaceTemplate = namespaceTemplate;
    }

    @JsonProperty("data")
    public Data getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Data data) {
        this.data = data;
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
