package au.com.carsguide.memberservice.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;

@Entity
@Validated
public class Member {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Name is required")
	@Size(min = 3, max = 50)
	private String name;

	@NotNull(message = "Email is required")
	@Email(message = "Email address not valid")
	@Size(min = 5, max = 100)
	private String email;

	public Member() {
	}
	
	public Member(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
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

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String toString() {
		return this.name + " (" + this.email + ")";
	}
}
