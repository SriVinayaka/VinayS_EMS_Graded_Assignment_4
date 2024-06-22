/**
 * 
 */
package com.gl.employeeservice.entity;

/**
 * 
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

   @Id
   @Column(name = "user_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name="username")
   private String username;
   @Column(name="password")
   private String password;
    
   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(
           name = "users_roles",
           joinColumns = @JoinColumn(name = "user_id"),
           inverseJoinColumns = @JoinColumn(name = "role_id")
           )
   private List<Role> roles = new ArrayList<>();

/**
 * @return the id
 */
public Long getId() {
	return id;
}

/**
 * @param id the id to set
 */
public void setId(Long id) {
	this.id = id;
}

/**
 * @return the username
 */
public String getUsername() {
	return username;
}

/**
 * @param username the username to set
 */
public void setUsername(String username) {
	this.username = username;
}

/**
 * @return the password
 */
public String getPassword() {
	return password;
}

/**
 * @param password the password to set
 */
public void setPassword(String password) {
	this.password = password;
}

/**
 * @return the roles
 */
public List<Role> getRoles() {
	return roles;
}

/**
 * @param roles the roles to set
 */
public void setRoles(List<Role> roles) {
	this.roles = roles;
}

   


}

