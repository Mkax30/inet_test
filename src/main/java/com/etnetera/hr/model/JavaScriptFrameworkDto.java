package com.etnetera.hr.model;

import com.etnetera.hr.data.Version;

import java.util.Set;

public class JavaScriptFrameworkDto {

    private Long id;
    private String name;
    private Set<VersionDto> versions;
    private int hypeLevel;

	public JavaScriptFrameworkDto() {
	}

	public JavaScriptFrameworkDto(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<VersionDto> getVersions() {
        return versions;
    }

    public void setVersions(Set<VersionDto> versions) {
        this.versions = versions;
    }

    public int getHypeLevel() {
        return hypeLevel;
    }

    public void setHypeLevel(int hypeLevel) {
        this.hypeLevel = hypeLevel;
    }
}
