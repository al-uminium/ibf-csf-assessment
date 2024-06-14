package ibf2023.csf.backend.repositories;

import java.sql.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import ibf2023.csf.backend.Constants;
import ibf2023.csf.backend.models.Picture;

@Repository
public class PictureRepository implements Constants{

	@Autowired MongoTemplate template;
	// TODO Task 4.2
	// You may change the method signature by adding parameters and/or the  return type
	// You may throw any exception 
	/*
	 db.travelpics.insert({
	 	date: <date>,
		title: <title>,
		comments: <comments>,
		url: <url>
		size: <size>
		pid: <pid>
	 })
	 */
	public void save(MultipartFile img, String title, String comments) {
		Picture pic = new Picture();
		String url = "https://alzj-bucket.sgp1.digitaloceanspaces.com/assessment/%s".formatted(title);
		double size = img.getSize() * 0.00000095367432; //in MB
		pic.setComments(comments);
		pic.setTitle(title);
		pic.setDate(new Date(0));
		pic.setSize(size);
		pic.setUrl(url);
		Picture savedPic = template.insert(pic);
		savedPic.toString();
	}

}
