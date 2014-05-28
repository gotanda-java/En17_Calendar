package com.example.calendar_base;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TextView;

public class CalendarActivity extends Activity {

	private static final String FILE_NAME ="CalendarFile";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calendar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.item1:
			showDatePickerDialog();
			return true;
		case R.id.item2:
			SharedPreferences preference
			=getSharedPreferences(FILE_NAME, MODE_PRIVATE);

			String date= preference.getString("DATE", "����܂���");

			showDialog(date);
			//�v���t�@�����X�t�@�C���ɕۑ����ꂽ�f�[�^�̕\��
			showDate(date);
			return true;
		}
		return true;
	}

	//��ʂɕۑ��f�[�^��\��
	public void showDate(String date) {
		//TextView�擾
		TextView message = (TextView)findViewById(R.id.tv_message);
		message.setText(date);
	}

	private void showDatePickerDialog() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		Calendar cal = Calendar.getInstance();

		DatePickerDialog dialog
		=new DatePickerDialog(CalendarActivity.this, new DatePickerDialog.OnDateSetListener() {


			public void onDateSet(DatePicker picker, int year, int month,
					int day) {

				SharedPreferences preference
				=getSharedPreferences(FILE_NAME, MODE_PRIVATE);
				// TODO �����������ꂽ���\�b�h�E�X�^�u
				SharedPreferences.Editor editor = preference.edit();
				editor.putString("DATE",
						year+"�N"+(month +1)+"��"+day+"��");
				editor.commit();
			}
		}
		,cal.get(Calendar.YEAR)
		,cal.get(Calendar.MONTH)
		,cal.get(Calendar.DAY_OF_MONTH)
		);
		dialog.show();
	}

	private void showDialog(String text){
		AlertDialog.Builder dialog
		=new AlertDialog.Builder(CalendarActivity.this);
		dialog.setTitle("�ۑ�����");
		dialog.setMessage(text);
		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {


			public void onClick(DialogInterface dialog, int whichButton) {
				// TODO �����������ꂽ���\�b�h�E�X�^�u
				CalendarActivity.this.setResult(Activity.RESULT_OK);
		}
	});
	dialog.create();
	dialog.show();
}

}
