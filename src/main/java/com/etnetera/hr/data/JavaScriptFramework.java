package com.etnetera.hr.data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple data entity describing basic properties of every JavaScript framework.
 *
 * @author Etnetera
 */
@Entity
public class JavaScriptFramework {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Version> versions;

    @Column
    private int hypeLevel;

    public JavaScriptFramework() {
    }

    public JavaScriptFramework(String name) {
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

    public JavaScriptFramework setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Version> getVersions() {
        return versions;
    }

    public JavaScriptFramework setVersions(Set<Version> versions) {
        this.versions = versions;
        return this;
    }

    public JavaScriptFramework addVersion(Version version) {
        if (versions == null) {
            versions = new HashSet<>();
        }
        this.versions.add(version);
        return this;
    }

    public int getHypeLevel() {
        return hypeLevel;
    }

    public JavaScriptFramework setHypeLevel(int hypeLevel) {
        this.hypeLevel = hypeLevel;
        return this;
    }
}
