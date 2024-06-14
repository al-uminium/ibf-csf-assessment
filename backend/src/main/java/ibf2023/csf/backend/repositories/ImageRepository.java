package ibf2023.csf.backend.repositories;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import ibf2023.csf.backend.Constants;

@Repository
public class ImageRepository implements Constants {

	// TODO Task 4.1
	// You may change the method signature by adding parameters and/or the return type
	// You may throw any exception 
	@Autowired AmazonS3 s3;
	public void save(MultipartFile file, String title, String comments) throws IOException {
		PutObjectRequest putReq;
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(file.getSize());
		metadata.setContentType(file.getContentType());
		metadata.addUserMetadata("title", title);
		metadata.addUserMetadata("comments", comments);
		putReq = new PutObjectRequest(BUCKET_NAME, FOLDER_PATH.formatted(title), file.getInputStream(), metadata);
		putReq = putReq.withCannedAcl(CannedAccessControlList.PublicRead);
    s3.putObject(putReq);
	}
}

