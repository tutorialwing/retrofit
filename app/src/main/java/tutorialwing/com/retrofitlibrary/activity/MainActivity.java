package tutorialwing.com.retrofitlibrary.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tutorialwing.com.retrofitlibrary.R;
import tutorialwing.com.retrofitlibrary.adapter.QuestionAdapter;
import tutorialwing.com.retrofitlibrary.model.Question;
import tutorialwing.com.retrofitlibrary.model.QuestionList;
import tutorialwing.com.retrofitlibrary.rest.QuestionAPIService;
import tutorialwing.com.retrofitlibrary.rest.RestClient;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = MainActivity.class.getSimpleName();

	QuestionAPIService apiService;

	RecyclerView recyclerView;
	QuestionAdapter adapter;
	List<Question> questions = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		apiService = RestClient.getClient().create(QuestionAPIService.class);

		recyclerView = (RecyclerView) findViewById(R.id.questionListRecyclerView);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		adapter = new QuestionAdapter(questions, R.layout.question_item, getApplicationContext());
		recyclerView.setAdapter(adapter);

		fetchQuetionList();
	}

	private void fetchQuetionList() {
		Call<QuestionList> call = apiService.fetchQuestions("android");
		call.enqueue(new Callback<QuestionList>() {
			@Override
			public void onResponse(Call<QuestionList> call, Response<QuestionList> response) {
				Log.d(TAG, "Total number of questions fetched : " + response.body().getQuestions().size());

				questions.addAll(response.body().getQuestions());
				adapter.notifyDataSetChanged();
			}

			@Override
			public void onFailure(Call<QuestionList> call, Throwable t) {
				Log.e(TAG, "Got error : " + t.getLocalizedMessage());
			}
		});
	}
}




