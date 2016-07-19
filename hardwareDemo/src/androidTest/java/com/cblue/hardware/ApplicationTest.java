package com.cblue.hardware;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.test.ApplicationTestCase;

import com.cblue.camera.zxing.CreateQRActivity;

import java.io.File;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }


    public void testSavePicToSDcard(){

       Bitmap bitmap =   BitmapFactory.decodeResource(getContext().getResources(),R.mipmap.ic_launcher);
       String path =   Environment.getExternalStorageDirectory().getAbsolutePath();
        System.out.println("path="+path);
        CameraActivity01.savePicToSdCard(bitmap,path,"aaa.png");

        File file = new File(path,"aaa.png");
        String filepath =  file.getAbsolutePath();
        System.out.println("filepath="+filepath);
        System.out.println(file.exists());
        if(file.exists()){
            System.out.println("文件存在");
        }

    }

    public void testFile(){
       String path =  CreateQRActivity.getFileRoot(getContext());
        System.out.println("path="+path);
    }
}

