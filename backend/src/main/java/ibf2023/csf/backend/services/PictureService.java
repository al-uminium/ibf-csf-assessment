package ibf2023.csf.backend.services;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ibf2023.csf.backend.models.Picture;
import ibf2023.csf.backend.repositories.ImageRepository;
import ibf2023.csf.backend.repositories.PictureRepository;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@Service
public class PictureService {
	@Autowired ImageRepository imgRepo;
	@Autowired PictureRepository picRepo;

// 	db.travelpics.aggregate([
// 		{
// 		 $match: { 
// 				 date: {
// 						 $gte: ISODate("2024-05-14")   
// 						 } 
// 				 }
// 		 },
// 		 {
// 				 $group: {
// 						 _id:null,
// 						 total: {
// 								 $sum: "$size"
// 						 }
// 				 }
// 		 }
//  ])

	// TODO Task 5.1
	// You may change the method signature by adding parameters and/or the return type
	// You may throw any exception 
	public JsonObject save(MultipartFile file, String title, String comments) throws IOException {
		
		try {
			double totalImgSize = picRepo.getTotalImageSize().getFirst().getSize() + (file.getSize()*0.00000095367432);
			Boolean limitExceeded = picRepo.isExceedLimit(totalImgSize);
			if (!limitExceeded) {
				imgRepo.save(file, title, comments);
				Picture pic = picRepo.save(file, title, comments);
				return Json.createObjectBuilder().add("id", pic.getPid().toString()).build();
			} else {
				return Json.createObjectBuilder().add("message", "The upload has exceeded your monthly upload quota of 1GB").build();
			}
			
		} catch (NoSuchElementException e) {

			imgRepo.save(file, title, comments);
			Picture pic = picRepo.save(file, title, comments);
			return Json.createObjectBuilder().add("id", pic.getPid().toString()).build();
		}

	}


	
}
