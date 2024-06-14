package ibf2023.csf.backend.models;




import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "travelpics")
public class Picture {

  @DateTimeFormat
  private Instant date;
  private String title;
  private String comments;
  private String url;
  private double size;
  @Id
  private UUID pid;

  public Instant getDate() {
    return date;
  }
  public void setDate(Instant date) {
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
  public UUID getPid() {
    return pid;
  }
  public void setPid(UUID pid) {
    this.pid = pid;
  } 
  
}
