package inmite.android.styled.dialogs;

import android.app.Activity;
import android.app.AlertDialog;

public class DialogUtils {
	public static void exibeMsg(String string,Activity activity) {
		new AlertDialog.Builder(activity)
			.setTitle("Info")
			.setMessage(string)
			.setPositiveButton("Ok", null)
			.create()
			.show();
	}
}
