package ibf2023.csf.backend.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ibf2023.csf.backend.repositories.ImageRepository;
import ibf2023.csf.backend.repositories.PictureRepository;

@Service
public class PictureService {
	@Autowired ImageRepository imgRepo;
	@Autowired PictureRepository picRepo;
	// TODO Task 5.1
	// You may change the method signature by adding parameters and/or the return type
	// You may throw any exception 
	public void save(MultipartFile file, String title, String comments) throws IOException {
		imgRepo.save(file, title, comments);
		picRepo.save(file, title, comments);
	}
}
