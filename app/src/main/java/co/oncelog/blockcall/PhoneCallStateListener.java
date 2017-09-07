package co.oncelog.blockcall;

import java.lang.reflect.Method;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.android.internal.telephony.ITelephony;

import co.oncelog.blockcall.dao.BlackList;
import co.oncelog.blockcall.manager.PhoneListManager;

/**
 * Created by son_g on 9/7/2017.
 */

public class PhoneCallStateListener extends PhoneStateListener {

    private Context context;
    public PhoneCallStateListener(Context context){
        this.context = context;
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        switch (state) {
            case TelephonyManager.CALL_STATE_RINGING:
                AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                //Turn ON the mute
                audioManager.setStreamMute(AudioManager.STREAM_RING, true);
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                try {
                    Class clazz = Class.forName(telephonyManager.getClass().getName());
                    Method method = clazz.getDeclaredMethod("getITelephony");
                    method.setAccessible(true);
                    ITelephony telephonyService;
                    System.out.println("Call "+incomingNumber);
//                    Toast.makeText(context, PhoneListManager.getInstance().getDao().containsAll(incomingNumber)+"", Toast.LENGTH_LONG).show();
                    boolean flag=false;
                    for(BlackList a : PhoneListManager.getInstance().getDao()){
                        if(incomingNumber.equalsIgnoreCase(a.getPhoneNumber())){
                            flag = true;
                        }
                    }
                    if (flag) {
                        telephonyService = (ITelephony) method.invoke(telephonyManager);
                        telephonyService.silenceRinger();
                        telephonyService.endCall();
                    }
                } catch (Exception e) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
                }
                audioManager.setStreamMute(AudioManager.STREAM_RING, false);
                break;
            case PhoneStateListener.LISTEN_CALL_STATE:
        }
        super.onCallStateChanged(state, incomingNumber);
    }}