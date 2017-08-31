package com.libertymutual.goforcode.wimp.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
		generator=ObjectIdGenerators.PropertyGenerator.class,
		property="id"	
		)
@Entity
public class Actor {

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(length = 75, nullable = false)
	private String firstName;
	
	@Column(length = 75)
	private String lastName;

	Long activeSinceYear;
	
	Date birthDate;
	
	//@JsonIgnore
	@ManyToMany(mappedBy="actors")
	private List<Movie> movies;
	
	public List<Movie> getMovies() {
		return movies;	
	}
	
	
	@OneToMany (cascade = CascadeType.ALL)
	private List<Award> awards;
	
	public List<Award> getAwards() {
		return awards;
	}
	
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public Actor() {}
	public Actor(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Actor(String firstName, String lastName, Date birthDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}
	
	public void addAward(Award award) {
		if (awards == null) {
			awards = new ArrayList<Award>();
		}
		awards.add(award);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getActiveSinceYear() {
		return activeSinceYear;
	}

	public void setActiveSinceYear(Long activeSinceYear) {
		this.activeSinceYear = activeSinceYear;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setAwards(List<Award> awards) {
		this.awards = awards;
	}
	
}
