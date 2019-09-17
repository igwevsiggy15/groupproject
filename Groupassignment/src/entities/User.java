package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name", length = 40)
	private String name;

	@Column(name = "email", nullable = false, length = 80)
	private String email;

	@Column(name = "password", length = 256)
	private String password;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Image> images;

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

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public void addImage(Image image) {
		if(images==null)
			images= new ArrayList<Image>();
		images.add(image);
	}

	@Override
	public String toString() {
		int imageCount = 0;
		if (images != null)
			imageCount = images.size();

		return "User [id=" + id + ", name=" + name + ", email=" + email + ", images uploaded=" + imageCount
				+ ", images=" + images + "]";
	}

}
