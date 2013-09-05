package br.com.devmix.libs.utils.nfc;

import java.io.IOException;
import java.nio.charset.Charset;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Build;
import android.widget.Toast;
import br.com.devmix.libs.utils.R;

/**
 * <uses-permission android:name="android.permission.NFC" />
   <uses-feature android:name="android.hardware.nfc" android:required="true" />
   <intent-filter>
            <action android:name="android.nfc.action.NDEF_DISCOVERED"/>
            <data android:mimeType="application/br.com.devmix.libs.hardware.nfc"/>
            <category android:name="android.intent.category.DEFAULT"/>
    </intent-filter>

    @Override
    public void onNewIntent(Intent intent) {
		if(mNFCUtils.mInWriteMode) {
			mNFCUtils.mInWriteMode = false;
			Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
			mNFCUtils.writeTag(tag);
		}
    }
 * @author echer
 *
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class NFCUtils {
	private NfcAdapter nfcAdapter;
	private Activity activity;
	private boolean mInWriteMode = true;
	public NFCUtils(Activity activity){
		nfcAdapter = NfcAdapter.getDefaultAdapter(activity);
		this.activity = activity;
	}
	public boolean isEnabledNFC(){
		if (nfcAdapter.isEnabled()) {
			return true;
        }
        else {
            return false;
        }
	}
	public void openNFCConfigs(){
		Toast.makeText(activity, "Por favor habilite o suporte NFC do seu aparelho, após isso pressione voltar para voltar para o aplicativo!", Toast.LENGTH_LONG).show();
        activity.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
	}
	public void disableWriteMode(){
		nfcAdapter.disableForegroundDispatch(activity);
	}
	public void enableWriteMode() {
		setmInWriteMode(true);
		PendingIntent pendingIntent = PendingIntent.getActivity(activity, 0,
		            new Intent(activity, activity.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

		IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
		        IntentFilter[] filters = new IntentFilter[] { tagDetected };

        nfcAdapter.enableForegroundDispatch(activity, pendingIntent, filters, null);
	}
	public String writeTag(Tag tag,String MimeType,String textToWrite) {
		NdefRecord appRecord = NdefRecord.createApplicationRecord(MimeType);
        byte[] payload = textToWrite.getBytes();
        byte[] mimeBytes = ("application/"+MimeType).getBytes(Charset.forName("US-ASCII"));
        NdefRecord cardRecord = new NdefRecord(NdefRecord.TNF_MIME_MEDIA, mimeBytes, new byte[0], payload);
        NdefMessage message = new NdefMessage(new NdefRecord[] { cardRecord, appRecord});

			try {
				Ndef ndef = Ndef.get(tag);
				if (ndef != null) {
					ndef.connect();
					if (!ndef.isWritable()) {
						return activity.getResources().getString(R.string.error_nfc_tag_readonly);
					}

					int size = message.toByteArray().length;

					if (ndef.getMaxSize() < size) {
						return activity.getResources().getString(R.string.error_nfc_tag_not_space);
					}
					ndef.writeNdefMessage(message);
					return activity.getResources().getString(R.string.sucess_nfc_tag_writed);
				} else {
	                NdefFormatable format = NdefFormatable.get(tag);
	                if (format != null) {
	                    try {
	                            format.connect();
	                            format.format(message);
	                            return activity.getResources().getString(R.string.sucess_nfc_tag_writed);
	                    } catch (IOException e) {
	                    		return activity.getResources().getString(R.string.error_nfc_tag_ndef);
	                    }
	                } else {
	                		return activity.getResources().getString(R.string.error_nfc_tag_support_ndef);
	                }
	            }
	        } catch (Exception e) {
	        	return activity.getResources().getString(R.string.error_nfc_tag_write_error);
	        }
		}


	public String getNFCID(Intent intent){
	    byte[] byte_id = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
	    return byte_id.toString();
	}
	@SuppressLint("UseValueOf")
	public String byteToStr(byte[] input) {
	    StringBuffer buffer = new StringBuffer();
	    for (int i = 0; i < input.length; i++)
	        if (input[i] != 0) {
	            buffer.append( new Character((char)input[i]).toString());
	        }
	    return buffer.toString();
	}
	public byte[] strToByte(String input) {

	    byte[] buffer = new byte[(input.length()+1)*2];
	    for (int i = 0; i < buffer.length-2; i = i+2) {

	        buffer[i] = (byte)input.charAt(i/2);
	        buffer[i+1] = 0;
	    }
	    buffer[buffer.length-2] = 0;
	    buffer[buffer.length-1] = 0;


	    return buffer;
	}
	public boolean ismInWriteMode() {
		return mInWriteMode;
	}
	public void setmInWriteMode(boolean mInWriteMode) {
		this.mInWriteMode = mInWriteMode;
	}

}
