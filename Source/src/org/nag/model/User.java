package org.nag.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

//import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="USERS_TABLE")
public class User {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Size(min=3, max=30, message="User's First name must be between 3 and 30 characters long")
	@Pattern(regexp="^[a-zA-z]+$", message="User's first name must be alphabetic with no spaces")
	@Column(name="FIRST_NAME")
	private String firstName;

	@Size(min=3, max=50, message="User's Last name must be between 3 and 50 characters long")
	@Pattern(regexp="^[a-zA-z]+$", message="User's last name must be alphabetic with no spaces")
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Email(message="Inalid email address.")
	//@Pattern(regexp="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,4}",
	//		 message="Inalid email address.")
	@Column(name="EMAIL")
	private String email;
	
	@ManyToOne
	//@JoinTable(name="USERS_GROUPS", 
	//		   joinColumns=@JoinColumn(name="USER_ID"),
	//		   inverseJoinColumns=@JoinColumn(name="GROUPS_ID"))
	@JoinColumn(name="GROUP_ID")
	private Group group;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Group getGroup() {
		return group;
	}
	
	public void setGroup(Group group) {
		this.group = group;
	}
}
