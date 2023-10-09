package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import constant.NameStored;



//@NamedStoredProcedureQueries({
//	@NamedStoredProcedureQueries(name = "User.FindListLikedByVideoHref",
//			procedureName = "sp_selectUsersLikeVideoByVideoHref",
//			resultClasses = {User.class},
//			parameters = @StoredProcedureParameter(name = "videoHref", type = String.class))
//	
//})
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = NameStored.FIND_USER_LIKED_BY_VIDEO_HREF,
			procedureName = "sp_selectUsersLikeVideoByVideoHref", 
			parameters = {
				@StoredProcedureParameter(name = "videoHref", type = String.class) }, 
				resultClasses = { User.class }) })

@Entity
@Table(name="[user]")
public class User {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="email")
	private String email;
	@Column(name="isAdmin")
	private Boolean isAdmin;
	@Column(name="isActive")
	private Boolean isActive;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getisAdmin() {
		return isAdmin;
	}
	public void setAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Boolean isActive() {
		return isActive;
	}
	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	

}
