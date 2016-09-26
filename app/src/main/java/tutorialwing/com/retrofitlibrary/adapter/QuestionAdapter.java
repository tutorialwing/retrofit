package tutorialwing.com.retrofitlibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tutorialwing.com.retrofitlibrary.R;
import tutorialwing.com.retrofitlibrary.model.Question;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

	private List<Question> questions;
	private int rowLayout;
	private Context context;


	public QuestionAdapter(List<Question> questions, int rowLayout, Context context) {
		this.questions = questions;
		this.rowLayout = rowLayout;
		this.context = context;
	}

	@Override
	public QuestionAdapter.QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
		return new QuestionViewHolder(view);
	}


	@Override
	public void onBindViewHolder(QuestionViewHolder holder, final int position) {
		holder.positionNumber.setText("Question number : " + String.valueOf(position + 1));
		holder.questionTitle.setText("Title : " + questions.get(position).getTitle());
		holder.link.setText("Link : " + questions.get(position).getLink());
	}

	@Override
	public int getItemCount() {
		return questions.size();
	}

	public static class QuestionViewHolder extends RecyclerView.ViewHolder {
		TextView positionNumber;
		TextView questionTitle;
		TextView link;

		public QuestionViewHolder(View v) {
			super(v);
			positionNumber = (TextView) v.findViewById(R.id.positionNumber);
			questionTitle = (TextView) v.findViewById(R.id.title);
			link = (TextView) v.findViewById(R.id.link);
		}
	}
}