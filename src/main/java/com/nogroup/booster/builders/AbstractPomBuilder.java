package com.nogroup.booster.builders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import org.apache.maven.model.Build;
import org.apache.maven.model.CiManagement;
import org.apache.maven.model.Contributor;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.DependencyManagement;
import org.apache.maven.model.Developer;
import org.apache.maven.model.DistributionManagement;
import org.apache.maven.model.InputLocation;
import org.apache.maven.model.IssueManagement;
import org.apache.maven.model.License;
import org.apache.maven.model.MailingList;
import org.apache.maven.model.Model;
import org.apache.maven.model.Organization;
import org.apache.maven.model.Parent;
import org.apache.maven.model.Prerequisites;
import org.apache.maven.model.Profile;
import org.apache.maven.model.Reporting;
import org.apache.maven.model.Repository;
import org.apache.maven.model.Scm;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public abstract class AbstractPomBuilder extends AbstractFileBuilder {

	private Model model = new Model();
	
	@Override
	public void build() {
		MavenXpp3Writer writer = new MavenXpp3Writer();
		try {
			writer.write(new FileOutputStream(new File(parentDirectory(), "/pom.xml")), model);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public AbstractPomBuilder fromTemplate(String templatePath) {
		ClassLoader classLoader = AbstractPomBuilder.class.getClassLoader();
		 
        try (InputStream inputStream = classLoader.getResourceAsStream("templates/" + templatePath)) {
        	BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
    	    MavenXpp3Reader reader = new MavenXpp3Reader();
    	    model = reader.read(in);
        } catch (IOException | XmlPullParserException e) {
			e.printStackTrace();
			System.exit(-1);
        }
		return this;
	}
	
	public AbstractPomBuilder fromTemplateFile(String templatePath) {		 
        try (InputStream inputStream = new FileInputStream(new File(templatePath))) {
        	BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
    	    MavenXpp3Reader reader = new MavenXpp3Reader();
    	    model = reader.read(in);
        } catch (IOException | XmlPullParserException e) {
			e.printStackTrace();
			System.exit(-1);
        }
		return this;
	}
	public abstract String parentDirectory() throws JsonMappingException, JsonProcessingException, IOException;

	public AbstractPomBuilder addDependency(Dependency dependency) {
		model.addDependency(dependency);
		return this ;
	}

	public AbstractPomBuilder addModule(String string) {
		model.addModule(string);
		return this ;
	}

	public AbstractPomBuilder addPluginRepository(Repository repository) {
		model.addPluginRepository(repository);
		return this ;
	}

	public AbstractPomBuilder addProperty(String key, String value) {
		model.addProperty(key, value);
		return this ;
	}

	public AbstractPomBuilder addRepository(Repository repository) {
		model.addRepository(repository);
		return this ;
	}

	public AbstractPomBuilder addContributor(Contributor contributor) {
		model.addContributor(contributor);
		return this ;
	}

	public AbstractPomBuilder addDeveloper(Developer developer) {
		model.addDeveloper(developer);
		return this ;
	}

	public AbstractPomBuilder addLicense(License license) {
		model.addLicense(license);
		return this ;
	}

	public AbstractPomBuilder addMailingList(MailingList mailingList) {
		model.addMailingList(mailingList);
		return this ;
	}

	public AbstractPomBuilder addProfile(Profile profile) {
		model.addProfile(profile);
		return this ;
	}

	public List<Dependency> getDependencies() {
		return model.getDependencies();
	}

	public DependencyManagement getDependencyManagement() {
		return model.getDependencyManagement();
	}

	public DistributionManagement getDistributionManagement() {
		return model.getDistributionManagement();
	}

	public InputLocation getLocation(Object key) {
		return model.getLocation(key);
	}

	public List<String> getModules() {
		return model.getModules();
	}

	public List<Repository> getPluginRepositories() {
		return model.getPluginRepositories();
	}

	public Properties getProperties() {
		return model.getProperties();
	}

	public String getArtifactId() {
		return model.getArtifactId();
	}

	public Reporting getReporting() {
		return model.getReporting();
	}

	public Build getBuild() {
		return model.getBuild();
	}

	public Object getReports() {
		return model.getReports();
	}

	public CiManagement getCiManagement() {
		return model.getCiManagement();
	}

	public List<Repository> getRepositories() {
		return model.getRepositories();
	}

	public List<Contributor> getContributors() {
		return model.getContributors();
	}

	public AbstractPomBuilder removeDependency(Dependency dependency) {
		model.removeDependency(dependency);
		return this ;
	}

	public String getDescription() {
		return model.getDescription();
	}

	public AbstractPomBuilder removeModule(String string) {
		model.removeModule(string);
		return this ;
	}

	public AbstractPomBuilder removePluginRepository(Repository repository) {
		model.removePluginRepository(repository);
		return this ;
	}

	public AbstractPomBuilder removeRepository(Repository repository) {
		model.removeRepository(repository);
		return this ;
	}

	public List<Developer> getDevelopers() {
		return model.getDevelopers();
	}

	public AbstractPomBuilder setDependencies(List<Dependency> dependencies) {
		model.setDependencies(dependencies);
		return this ;
	}

	public String getGroupId() {
		return model.getGroupId();
	}

	public String getInceptionYear() {
		return model.getInceptionYear();
	}

	public AbstractPomBuilder setDependencyManagement(DependencyManagement dependencyManagement) {
		model.setDependencyManagement(dependencyManagement);
		return this ;
	}

	public IssueManagement getIssueManagement() {
		return model.getIssueManagement();
	}

	public List<License> getLicenses() {
		return model.getLicenses();
	}

	public AbstractPomBuilder setDistributionManagement(DistributionManagement distributionManagement) {
		model.setDistributionManagement(distributionManagement);
		return this ;
	}

	public List<MailingList> getMailingLists() {
		return model.getMailingLists();
	}

	public String getModelEncoding() {
		return model.getModelEncoding();
	}

	public AbstractPomBuilder setLocation(Object key, InputLocation location) {
		model.setLocation(key, location);
		return this ;
	}

	public String getModelVersion() {
		return model.getModelVersion();
	}

	public String getName() {
		return model.getName();
	}

	public AbstractPomBuilder setModules(List<String> modules) {
		model.setModules(modules);
		return this ;
	}

	public Organization getOrganization() {
		return model.getOrganization();
	}

	public AbstractPomBuilder setPluginRepositories(List<Repository> pluginRepositories) {
		model.setPluginRepositories(pluginRepositories);
		return this ;
	}

	public String getPackaging() {
		return model.getPackaging();
	}

	public AbstractPomBuilder setProperties(Properties properties) {
		model.setProperties(properties);
		return this ;
	}

	public Parent getParent() {
		return model.getParent();
	}

	public AbstractPomBuilder setReporting(Reporting reporting) {
		model.setReporting(reporting);
		return this ;
	}

	public Prerequisites getPrerequisites() {
		return model.getPrerequisites();
	}

	public AbstractPomBuilder setReports(Object reports) {
		model.setReports(reports);
		return this ;
	}

	public List<Profile> getProfiles() {
		return model.getProfiles();
	}

	public AbstractPomBuilder setRepositories(List<Repository> repositories) {
		model.setRepositories(repositories);
		return this ;
	}

	public Scm getScm() {
		return model.getScm();
	}

	public String getUrl() {
		return model.getUrl();
	}

	public String getVersion() {
		return model.getVersion();
	}

	public AbstractPomBuilder removeContributor(Contributor contributor) {
		model.removeContributor(contributor);
		return this ;
	}

	public AbstractPomBuilder removeDeveloper(Developer developer) {
		model.removeDeveloper(developer);
		return this ;
	}

	public AbstractPomBuilder removeLicense(License license) {
		model.removeLicense(license);
		return this ;
	}

	public AbstractPomBuilder removeMailingList(MailingList mailingList) {
		model.removeMailingList(mailingList);
		return this ;
	}

	public AbstractPomBuilder removeProfile(Profile profile) {
		model.removeProfile(profile);
		return this ;
	}

	public AbstractPomBuilder setArtifactId(String artifactId) {
		model.setArtifactId(artifactId);
		return this ;
	}

	public AbstractPomBuilder setBuild(Build build) {
		model.setBuild(build);
		return this ;
	}

	public AbstractPomBuilder setCiManagement(CiManagement ciManagement) {
		model.setCiManagement(ciManagement);
		return this ;
	}

	public AbstractPomBuilder setContributors(List<Contributor> contributors) {
		model.setContributors(contributors);
		return this ;
	}

	public AbstractPomBuilder setDescription(String description) {
		model.setDescription(description);
		return this ;
	}

	public AbstractPomBuilder setDevelopers(List<Developer> developers) {
		model.setDevelopers(developers);
		return this ;
	}

	public AbstractPomBuilder setGroupId(String groupId) {
		model.setGroupId(groupId);
		return this ;
	}

	public AbstractPomBuilder setInceptionYear(String inceptionYear) {
		model.setInceptionYear(inceptionYear);
		return this ;
	}

	public AbstractPomBuilder setIssueManagement(IssueManagement issueManagement) {
		model.setIssueManagement(issueManagement);
		return this ;
	}

	public AbstractPomBuilder setLicenses(List<License> licenses) {
		model.setLicenses(licenses);
		return this ;
	}

	public AbstractPomBuilder setMailingLists(List<MailingList> mailingLists) {
		model.setMailingLists(mailingLists);
		return this ;
	}

	public AbstractPomBuilder setModelEncoding(String modelEncoding) {
		model.setModelEncoding(modelEncoding);
		return this ;
	}

	public AbstractPomBuilder setModelVersion(String modelVersion) {
		model.setModelVersion(modelVersion);
		return this ;
	}

	public AbstractPomBuilder setName(String name) {
		model.setName(name);
		return this ;
	}

	public AbstractPomBuilder setOrganization(Organization organization) {
		model.setOrganization(organization);
		return this ;
	}

	public AbstractPomBuilder setPackaging(String packaging) {
		model.setPackaging(packaging);
		return this ;
	}

	public AbstractPomBuilder setParent(Parent parent) {
		model.setParent(parent);
		return this ;
	}

	public AbstractPomBuilder setPrerequisites(Prerequisites prerequisites) {
		model.setPrerequisites(prerequisites);
		return this ;
	}

	public AbstractPomBuilder setProfiles(List<Profile> profiles) {
		model.setProfiles(profiles);
		return this ;
	}

	public AbstractPomBuilder setScm(Scm scm) {
		model.setScm(scm);
		return this ;
	}

	public AbstractPomBuilder setUrl(String url) {
		model.setUrl(url);
		return this ;
	}

	public AbstractPomBuilder setVersion(String version) {
		model.setVersion(version);
		return this ;
	}

	public File getPomFile() {
		return model.getPomFile();
	}

	public AbstractPomBuilder setPomFile(File pomFile) {
		model.setPomFile(pomFile);
		return this ;
	}

	public File getProjectDirectory() {
		return model.getProjectDirectory();
	}

	public String getId() {
		return model.getId();
	}
	
	public AbstractPomBuilder fromModel(Model pomModel) {
		model = pomModel;
		return this;
	}
}
