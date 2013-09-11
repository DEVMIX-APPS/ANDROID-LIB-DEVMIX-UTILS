package br.com.devmix.libs.utils.bluetooth;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import android.annotation.TargetApi;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

/**
 * <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />
    <uses-permission android:name="android.permission.BLUETOOTH" />
 * @author echer
 *
 */
public class BluetoothUtils {
	static final UUID UUID_RFCOMM_GENERIC = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

	/*public static void startActivityDiscovery(Activity activity){
		activity.startActivity(new Intent(activity, ActivityBluetooth.class));
	}*/

	public static void makeDiscoverable(Activity activity,int duration) {
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, duration);
        activity.startActivity(discoverableIntent);
        Log.i("BluetoothUtils", "Discoverable ");
    }
	public static void onBluetooth() {
		BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(!bluetoothAdapter.isEnabled())
        {
            bluetoothAdapter.enable();
            Log.i("Log", "Bluetooth is Enabled");
        }
    }
    public static void offBluetooth() {
    	BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter.isEnabled())
        {
            bluetoothAdapter.disable();
        }
    }
    public static ArrayList<BluetoothDevice> getPairedDevices() {
        Set<BluetoothDevice> pairedDevice = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        ArrayList<BluetoothDevice> arrayListPairedBluetoothDevices = new ArrayList<BluetoothDevice>();
        if(pairedDevice != null && pairedDevice.size()>0)
        {
            for(BluetoothDevice device : pairedDevice)
            {
                arrayListPairedBluetoothDevices.add(device);
            }
        }
        return arrayListPairedBluetoothDevices;
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean createBond(BluetoothDevice btDevice)
			throws Exception
    {
        Class class1 = Class.forName("android.bluetooth.BluetoothDevice");
        Method createBondMethod = class1.getMethod("createBond");
        Boolean returnValue = (Boolean) createBondMethod.invoke(btDevice);
        return returnValue.booleanValue();
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean removeBond(BluetoothDevice btDevice)
    throws Exception
    {
        Class btClass = Class.forName("android.bluetooth.BluetoothDevice");
        Method removeBondMethod = btClass.getMethod("removeBond");
        Boolean returnValue = (Boolean) removeBondMethod.invoke(btDevice);
        return returnValue.booleanValue();
    }
    @SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public static Boolean connect(BluetoothDevice bdDevice) {
        Boolean bool = false;
        try {
            Log.i("Log", "service metohd is called ");
            Class cl = Class.forName("android.bluetooth.BluetoothDevice");
            Class[] par = {};
            Method method = cl.getMethod("createBond", par);
            Object[] args = {};
            bool = (Boolean) method.invoke(bdDevice);//, args);// this invoke creates the detected devices paired.
            //Log.i("Log", "This is: "+bool.booleanValue());
            //Log.i("Log", "devicesss: "+bdDevice.getName());
        } catch (Exception e) {
            Log.i("Log", "Inside catch of serviceFromDevice Method");
            e.printStackTrace();
        }
        return bool.booleanValue();
    };

    @TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1)
	public static boolean connectWrite(BluetoothDevice device,byte[] text,int delay) {
        BluetoothAdapter mBTAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice mBTDevice = mBTAdapter.getRemoteDevice(device.getAddress());
        BluetoothSocket mBTSocket;
		try {
        	mBTSocket = mBTDevice.createInsecureRfcommSocketToServiceRecord(UUID_RFCOMM_GENERIC);
        	mBTSocket.connect();
        	OutputStream mBTOutputStream = mBTSocket.getOutputStream();
            mBTOutputStream.write(text);
            mBTOutputStream.flush();
            Thread.sleep(delay);
            mBTOutputStream.close();
            return true;
        } catch (Exception e1) {
        	e1.printStackTrace();
            return false;
        }
    }
    @TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1)
	public static boolean connectWriteListWithDelay(BluetoothDevice device,List<String[]> nota) {
        BluetoothAdapter mBTAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice mBTDevice = mBTAdapter.getRemoteDevice(device.getAddress());
        BluetoothSocket mBTSocket;
		try {
			mBTSocket = mBTDevice.createInsecureRfcommSocketToServiceRecord(UUID_RFCOMM_GENERIC);
        	mBTSocket.connect();
        	OutputStream mBTOutputStream = mBTSocket.getOutputStream();

        	mBTOutputStream.write(nota.get(0)[0].getBytes());
            mBTOutputStream.flush();
			Thread.sleep(500);

			for(int i = 0;i<nota.get(1).length;i++){
				mBTOutputStream.write((nota.get(1)[i]).getBytes());
	            mBTOutputStream.flush();
	            Thread.sleep(500);
			}

			mBTOutputStream.write((nota.get(2)[0]+"\r\nP1,1").getBytes());
            mBTOutputStream.flush();

            mBTOutputStream.close();
            return true;
        } catch (Exception e1) {
        	e1.printStackTrace();
            return false;
        }
    }
    @TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1)
	public static boolean connectWriteBlocos(BluetoothDevice device,final List<String[]> nota) {
        BluetoothAdapter mBTAdapter = BluetoothAdapter.getDefaultAdapter();
        Log.i("Bluetooth MAC: ", device.getAddress());
        BluetoothDevice mBTDevice = mBTAdapter.getRemoteDevice(device.getAddress());
        BluetoothSocket mBTSocket;
		try {
			mBTSocket = mBTDevice.createRfcommSocketToServiceRecord(UUID_RFCOMM_GENERIC);
			//mBTSocket = mBTDevice.createInsecureRfcommSocketToServiceRecord(UUID_RFCOMM_GENERIC);
        	mBTSocket.connect();
        	final OutputStream mBTOutputStream = mBTSocket.getOutputStream();

        	mBTOutputStream.write(nota.get(0)[0].getBytes());
        	for(int i = 0;i<nota.get(1).length;i++){
				mBTOutputStream.write(nota.get(1)[i].getBytes());
        	}

        	mBTOutputStream.write(nota.get(2)[0].getBytes());
        	Thread.sleep(12000+(nota.get(1).length*8000));

            mBTOutputStream.close();
            return true;
        } catch (Exception e1) {
        	e1.printStackTrace();
            return false;
        }
    }
}
