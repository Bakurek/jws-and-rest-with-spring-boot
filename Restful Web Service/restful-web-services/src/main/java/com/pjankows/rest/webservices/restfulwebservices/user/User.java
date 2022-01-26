package com.pjankows.rest.webservices.restfulwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.pjankows.rest.webservices.restfulwebservices.post.Post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description="User details.")
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=2, message="Name should have at least 2 chars.")
	@ApiModelProperty(notes="Should have at least 2 chars.")
	private String name;
	
	@Past(message="Date can't be in the future.")
	@ApiModelProperty(notes="Birth date can't be in the future.")
	private Date birthDate;
	
	@OneToMany(mappedBy="user")
	private List<JpaPost> jpaPosts;
	
	protected User() {
	}
	
	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		//userPosts = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<JpaPost> getJpaPosts() {
		return jpaPosts;
	}

	public void setJpaPosts(List<JpaPost> jpaPosts) {
		this.jpaPosts = jpaPosts;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, birthDate=%s]", id, name, birthDate);
	}
	
	public void addPost(Post post) {
		/*
		 * if(post.getId()==null) { int postCount = userPosts.size();
		 * post.setId(++postCount); } userPosts.add(post);
		 */
	}
	
}
