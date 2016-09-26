package tutorialwing.com.retrofitlibrary.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import tutorialwing.com.retrofitlibrary.model.QuestionList;

public interface QuestionAPIService {
	@GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")	//End Url
	Call<QuestionList> fetchQuestions(@Query("tagged") String tags);
}
