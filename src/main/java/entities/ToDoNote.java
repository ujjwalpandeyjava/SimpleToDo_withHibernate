package entities;

import java.math.BigInteger;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.Table;
import jakarta.persistence.Table;

@Table
@Entity(name = "todonote")
public class ToDoNote {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger id;
	private String title;
	private String content;
	private Date addedDate;
	private String fileUrlResource;
	private String belogToUser;

	public ToDoNote() {
		super();
	}

	public ToDoNote(BigInteger id, String title, String content, String userIDString, String directoryPath,
			Date addedDate) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.belogToUser = userIDString;
		this.fileUrlResource = directoryPath;
		this.addedDate = addedDate;
	}

	public ToDoNote(String title, String content, String userIDString, String directoryPath, Date addedDate) {
		super();
		this.title = title;
		this.content = content;
		this.belogToUser = userIDString;
		this.fileUrlResource = directoryPath;
		this.addedDate = addedDate;
	}

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

	public String getFileUrlResource() {
		return fileUrlResource;
	}

	public void setFileUrlResource(String fileUrlResource) {
		this.fileUrlResource = fileUrlResource;
	}

	public String getBelogToUser() {
		return belogToUser;
	}

	public void setBelogToUser(String belogToUser) {
		this.belogToUser = belogToUser;
	}

	@Override
	public String toString() {
		return "ToDoNote [id:" + id + ", title:" + title + ", content:" + content + ", addedDate:" + addedDate
				+ ", fileUrlResource:" + fileUrlResource + ", belogToUser:" + belogToUser + "]";
	}

}
