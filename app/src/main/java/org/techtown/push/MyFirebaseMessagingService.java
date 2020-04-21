package org.techtown.push;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {//FirebaseMessagingService상속
   private static final String TAG = "FMS";

    public MyFirebaseMessagingService() {
    }
    @Override
    public void onNewToken(String token) {//새로운 토큰를 확인했을때 호출되는 매서드
        super.onNewToken(token);
        Log.e(TAG, "onnewToken호출됨:"+token);
    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {//새로운 메시지를 받았을때 호출
        Log.d(TAG,"onmessageReceived() 호출됨.");

        String from = remoteMessage.getFrom();
        Map<String, String> data = remoteMessage.getData();
        String contents = data.get("contents");

        Log.d(TAG, "from : "+from+",contents : " + contents);
    }


    private void sendToActivity(Context context, String from, String contents) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("from", from);
        intent.putExtra("contents", contents);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        context.startActivity(intent);
    }

}
