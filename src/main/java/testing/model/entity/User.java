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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="testingUser")
@NamedQueries({
	@NamedQuery(query = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password", name = "FIND User BY Email AND Password"),
	@NamedQuery(query = "SELECT u FROM User u", name="FIND ALL User"),
	@NamedQuery(query = "SELECT u FROM User u LEFT JOIN FETCH u.contacts WHERE u.id = :id", name="GET Contacts")
})
@XmlRootElement  
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public User() {
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String email;
	private String password;
	
	@OneToMany(mappedBy = "owner")
	private List<Contact> contacts;
	
	
	public List<Contact> getContacts() {
		return contacts;
	}

	@XmlElement
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	
	public int getId() {
		return id;
	}
	
	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getEmail() {
		return email;
	}
	
	@XmlElement
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

