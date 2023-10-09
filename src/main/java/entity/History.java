package entity;

import java.io.ObjectInputStream.GetField;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "history")
public class History {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "userID", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications","hibernatelazyIntitializer"})
	private User user;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "videoID", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications","hibernatelazyIntitializer"})
	private Video video;
	
	@Column(name="viewDate")
	@CreationTimestamp
	private Timestamp viewDate;
	
	@Column(name="islike")
	private boolean islike;
	
	@Column(name="likeDate")
	private Timestamp likeDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Timestamp getviewDate() {
		return viewDate;
	}

	public void setViewDate(Timestamp viewDate) {
		this.viewDate = viewDate;
	}

	public boolean getislike() {
		return islike;
	}

	public void setislike(Boolean islike) {
		this.islike = islike;
	}

	public Timestamp getLikeDate() {
		return likeDate;
	}

	public void setLikeDate(Timestamp likeDate) {
		this.likeDate = likeDate;
	}
	
	
	
}
