package ibf2023.csf.backend.repositories;



import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import ibf2023.csf.backend.Constants;
import ibf2023.csf.backend.models.Picture;
import ibf2023.csf.backend.models.Result;

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
	public Picture save(MultipartFile img, String title, String comments) {
		Picture pic = new Picture();
		String url = "https://alzj-bucket.sgp1.digitaloceanspaces.com/assessment/%s".formatted(title);
		double size = img.getSize() * 0.00000095367432; //in MB
		UUID uuid = UUID.randomUUID();
		pic.setComments(comments);
		pic.setTitle(title);
		Instant date = Instant.now();
		pic.setDate(date);
		pic.setSize(size);
		pic.setUrl(url);
		pic.setPid(uuid);
		template.insert(pic);

		return pic;
	}

	public Instant getOneMonthFromToday() {
		Instant today = Instant.now();
		return today.minus(30, ChronoUnit.DAYS);
	}

	public List<Result> getTotalImageSize() {
		Instant oneMonthBefore = getOneMonthFromToday();
		System.out.println(">>> one month before: " + oneMonthBefore);
		MatchOperation match = Aggregation.match(Criteria.where("date").gte(oneMonthBefore));
		GroupOperation grp = Aggregation.group().sum("size").as("size");
		Aggregation aggr = Aggregation.newAggregation(match,grp);
		AggregationResults<Result> res = template.aggregate(aggr, "travelpics", Result.class);
		return res.getMappedResults();
	}

	public Boolean isExceedLimit(double sum) {
		int limit = 1000;
		if ((limit - sum) < 0) {
			return true;
		}
		return false;
	}
}
