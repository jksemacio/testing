package testing.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="testingUser")
@NamedQueries({
	@NamedQuery(query = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password", name = "FIND User BY Email AND Password"),
	@NamedQuery(query = "SELECT u FROM User u", name="FIND ALL User"),
	@NamedQuery(query = "SELECT u FROM User u LEFT JOIN FETCH u.contacts WHERE u.idNo = :idNo", name="GET Contacts")
})
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public User() {
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idNo;
	
	private String email;
	private String password;
	
	@OneToMany(mappedBy = "owner")
	private List<Contact> contacts;
	
	
	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	
	public Integer getIdNo() {
		return idNo;
	}
	
	public void setIdNo(Integer idNo) {
		this.idNo = idNo;
	}
	
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}

