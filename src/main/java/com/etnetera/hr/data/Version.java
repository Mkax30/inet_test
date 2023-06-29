package com.etnetera.hr.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple data entity describing basic properties of every JavaScript framework.
 * 
 * @author Etnetera
 *
 */
@Data
@NoArgsConstructor
@Entity
public class Version {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String versionNumber;

	@Column
	private LocalDate deprecationDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public Version setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
		return this;
	}

	public LocalDate getDeprecationDate() {
		return deprecationDate;
	}

	public Version setDeprecationDate(LocalDate deprecationDate) {
		this.deprecationDate = deprecationDate;
		return this;
	}
}
