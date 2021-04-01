package entities;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ToDos")
public class ToDoNote {
	public ToDoNote() {
	}
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private BigInteger id;
	private String title;
	private String content;
	private Date addedDate;
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	public ToDoNote(String title, String content, Date addedDate) {
		super();
		this.title = title;
		this.content = content;
		this.addedDate = addedDate;
	}

}
