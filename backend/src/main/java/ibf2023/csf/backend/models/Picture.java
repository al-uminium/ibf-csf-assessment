package ibf2023.csf.backend.models;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "travelpics")
public class Picture {
  @Id
  private Date date;
  private String title;
  private String comments;
  private String url;
  private double size;

  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getComments() {
    return comments;
  }
  public void setComments(String comments) {
    this.comments = comments;
  }
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  public double getSize() {
    return size;
  }
  public void setSize(double size) {
    this.size = size;
  } 
}
