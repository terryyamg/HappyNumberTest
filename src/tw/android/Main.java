package tw.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends Activity {

	private EditText etNumber;
	private Button btSend;
	private TextView tvInfo;

	private int[] numberArray;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		etNumber = (EditText) findViewById(R.id.etNumber);
		btSend = (Button) findViewById(R.id.btSend);
		tvInfo = (TextView) findViewById(R.id.tvInfo);

		btSend.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				int number = Integer.parseInt(etNumber.getText().toString()); // 輸入數字
				if (isHappy(number)) {
					tvInfo.setText(number + "是 Happy Number");
				} else {
					tvInfo.setText(number + "不是 Happy Number");
				}

			}
		});

	}

	/*
	 * Write an algorithm to determine if a number is "happy".
	 * 
	 * A happy number is a number defined by the following process: Starting
	 * with any positive integer, replace the number by the sum of the squares
	 * of its digits, and repeat the process until the number equals 1 (where it
	 * will stay), or it loops endlessly in a cycle which does not include 1.
	 * Those numbers for which this process ends in 1 are happy numbers.
	 * 
	 * Example: 19 is a happy number
	 * 
	 * 1^2 + 9^2 = 82 
	 * 8^2 + 2^2 = 68 
	 * 6^2 + 8^2 = 100 
	 * 1^2 + 0^2 + 0^2 = 1
	 */

	/* 回傳驗證是否為happy number */
	public boolean isHappy(int n) {
		Log.i("keyNumber:", n + "");
		int sum, length;
		do {
			sum = getNumber(n, Integer.toString(n).length());
			length = Integer.toString(sum).length(); // 總和數字長度
			n = sum;
		} while (length != 1);// 重複執行到剩個位數
		
		Log.i("sum:", sum + "");
		if (sum == 1) { // 個位數等於1 happy number!!
			return true;
		} else { // 不是happy number...
			return false;
		}
	}

	/* 取得每個數字 放入數字陣列 計算總合 */
	public int getNumber(int n, int length) {
		/* 建立每個數字陣列 */
		numberArray = new int[length];
		int quotient = 0;// 商數
		int sum = 0; // 總合
		for (int i = 0; i < length; i++) {
			if (i == 0) { // 一開始輸入的數字計算
				numberArray[i] = n % 10; // 獲得餘數
				quotient = n / 10; // 獲得商數
			} else { // 計算後的總合再計算
				numberArray[i] = quotient % 10; // 獲得餘數
				quotient = quotient / 10; // 獲得商數
			}
			sum += (int) Math.pow(numberArray[i], 2); // 計算總和

		}
		return sum; // 回傳總和
	}

}