package org.nag.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//import org.hibernate.validator.constraints.Length;
//import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="GROUPS_TABLE")
public class Group {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="GROUP_NAME")
	//@NotEmpty
	//@Length(min=3, max=30, message="Group name must be between 3 and 30 characters long")
	@NotNull
	@Size(min=3, max=30, message="Group name must be between 3 and 30 characters long")
	@Pattern(regexp="^[a-zA-z0-9]+$", message="Group name must be alphanumeric with no spaces")
	private String name;

	@OneToMany(mappedBy="group", cascade=CascadeType.REMOVE/*, fetch=FetchType.EAGER*/)
	private List<User> users = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
}
