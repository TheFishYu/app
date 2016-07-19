package com.cblue.camera.zxing;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成二维码的类
 * Created by pavel on 2016/7/19.
 */
public class QRUtils {

    /**
     * 生成二维码的图片
     * @param content 生成二维码的内容
     * @param width   生成二维码的宽度
     * @param height 生成二维码的高度
     * @param filePath 保存二维码的路径
     * @param  logo logo图片
     * @return
     */
    public static boolean createQR(String content,int width,int height,String filePath,Bitmap logo){
        boolean flag = false;
        //为二维码生成设定一些条件(字符编码，容错级别)
        Map<EncodeHintType,Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET,"utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

        try {
            //使用二维码的写对象，根据生成的内容，宽和高，条件，生成一个bit的矩阵对象
            BitMatrix bitMatrix =  new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE,width,height,hints);

            //创建数组，来保存所有的像素点
            int pixles [] = new int[width*height];
            //得到每一个像素点的颜色
            for(int x=0;x<width;x++){
                for(int y=0;y<height;y++){
                    //从矩阵中获得X轴，X轴包含的像素点
                    if(bitMatrix.get(x,y)){
                        pixles[y*width+x] = 0xff000000;
                    }else{
                        pixles[y*width+x] = 0xffffffff;
                    }
                }
            }
            //创建一个二维码的图片
            Bitmap bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
            //把二维码的像素画到图片上
            bitmap.setPixels(pixles,0,width,0,0,width,height);
            //如果添加logo
            if(logo!=null){
                bitmap = addLogo(bitmap,logo);
            }
            //压缩图片，保存图片
            bitmap.compress(Bitmap.CompressFormat.PNG,100,new FileOutputStream(filePath));
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;

    }

    /**
     * 给二维码上添加logo
     * @param src 原二维码图片
     * @param logo logo图片
     * @return
     */
    public static Bitmap addLogo(Bitmap src,Bitmap logo){

        Bitmap bitmap ;
        if(src!=null||logo!=null){
            int width = src.getWidth();
            int height = src.getHeight();
            int logowidth =logo.getWidth();
            int logoheight = logo.getHeight();
            //底板图片
            bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
            //创建画板对象
            Canvas canvas = new Canvas(bitmap);
            //画出二维码
            canvas.drawBitmap(src,0,0,null);
            //画logo
            canvas.drawBitmap(logo,(width-logowidth)/2,(height-logoheight)/2,null);
            //保存内容
            canvas.save(Canvas.ALL_SAVE_FLAG);
            canvas.restore();
            return bitmap;
        }
        return null;
    }

}
