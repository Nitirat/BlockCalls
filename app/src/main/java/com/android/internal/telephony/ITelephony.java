package com.android.internal.telephony;

/**
 * Created by son_g on 9/7/2017.
 */

public interface ITelephony {

    boolean endCall();

    void answerRingingCall();

    void silenceRinger();
}
