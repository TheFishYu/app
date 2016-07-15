package com.meizhu.processservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AIDLService extends Service {
    public AIDLService() {
    }
    class AIDLInterface extends IMyAidlInterface.Stub{
        @Override
        public int add(int a, int b) throws RemoteException {
            return a+b;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new AIDLInterface();
    }
}
