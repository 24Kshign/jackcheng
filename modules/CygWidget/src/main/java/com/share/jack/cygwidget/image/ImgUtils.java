//package com.share.jack.cygwidget.image;
//
///**
// * 创建时间：2017/2/15
// * 编 写 人：@小包子
// * 功能描素：图片选择
// */
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Matrix;
//import android.graphics.Paint;
//import android.graphics.PorterDuff;
//import android.graphics.PorterDuffXfermode;
//import android.graphics.Rect;
//import android.graphics.RectF;
//import android.media.ExifInterface;
//import android.net.Uri;
//import android.os.Build;
//import android.provider.MediaStore;
//import android.util.Log;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//public class ImgUtils {
//    private static final int NONE = 0;
//    private static final int PHOTOHRAPH = 101;
//    private static final int PHOTOZOOM = 102;
//    private static final int PHOTORESOULT = 103;
//    private static final int ROUNDRESOULT = 104;
//    private static final String IMAGE_UNSPECIFIED = "image/*";
//    private static final String IMG_PATH_KEY = "IMG_PATH_KEY";
//    private static String path = "";
//    private Activity activity;
//    private boolean isRound;
//    private Intent intentUtils;
//    private CameraCallBack cameraCallBack;
//
//    public ImgUtils(Activity activity) {
//        this(activity, false, (Intent)null);
//    }
//
//    public ImgUtils(Activity activity, Intent intentUtils) {
//        this(activity, false, intentUtils);
//    }
//
//    public ImgUtils(Activity activity, boolean isRound, Intent intentUtils) {
//        this.isRound = false;
//        if(LimitConfig.getLimitConfig().isLimit()) {
//            this.activity = activity;
//            this.isRound = isRound;
//            this.intentUtils = intentUtils;
//        }
//    }
//
//    public static void setPath(String path) {
//        path = path + "ico" + File.separator;
//        File file = new File(path);
//        if(!file.exists()) {
//            file.mkdirs();
//        }
//    }
//
//    private void trimPath() {
//        MUtils.getMUtils().setShared("IMG_PATH_KEY", path + System.currentTimeMillis() + ".png");
//    }
//
//    private String getPath() {
//        return MUtils.getMUtils().getShared("IMG_PATH_KEY");
//    }
//
//    public void setCameraCallBack(CameraCallBack cameraCallBack) {
//        this.cameraCallBack = cameraCallBack;
//    }
//
//    public void openCamera() {
//        trimPath();
//        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//        intent.putExtra("return-data", false);
//        intent.putExtra("output", Uri.fromFile(new File(getPath())));
//        activity.startActivityForResult(intent, 101);
//    }
//
//    public void openPhotoAlbum() {
//        Intent intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//        activity.startActivityForResult(intent, 102);
//    }
//
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(resultCode != 0) {
//            String requestPath;
//            if(requestCode == 101) {
//                requestPath = this.autoRotation(this.getPath());
//                if(this.intentUtils == null) {
//                    if(cameraCallBack != null) {
//                        cameraCallBack.onCameraCallBack(requestPath);
//                    }
//                } else {
//                    startPhotoZoom(Uri.parse("file:" + File.separator + File.separator + File.separator + requestPath));
//                }
//            }
//
//            if(requestCode == 103 && this.cameraCallBack != null) {
//                cameraCallBack.onCameraCallBack(this.getPath());
//            }
//
//            if(requestCode == 104) {
//                BitmapFactory.Options requestPath1 = new BitmapFactory.Options();
//                requestPath1.inPreferredConfig = Bitmap.Config.RGB_565;
//                Bitmap imgPath = this.toRoundCorner(BitmapFactory.decodeFile(this.getPath(), requestPath1));
//                SaveBitmap(imgPath);
//                if(cameraCallBack != null) {
//                    cameraCallBack.onCameraCallBack(this.getPath());
//                }
//            }
//
//            if(data != null) {
//                if(requestCode == 102) {
//                    requestPath = this.selectImage(this.activity, data);
//                    String imgPath1 = this.autoRotation(requestPath);
//                    if(intentUtils == null) {
//                        if(cameraCallBack != null) {
//                            cameraCallBack.onCameraCallBack(imgPath1);
//                        }
//                    } else {
//                        startPhotoZoom(Uri.parse("file:" + File.separator + File.separator + File.separator + imgPath1));
//                    }
//                }
//
//            }
//        }
//    }
//
//    private Bitmap toRoundCorner(Bitmap bitmap) {
//        int pixels = bitmap.getWidth() / 2;
//        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(output);
//        int color = -16777216;
//        Paint paint = new Paint();
//        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
//        RectF rectF = new RectF(rect);
//        float roundPx = (float)pixels;
//        paint.setAntiAlias(true);
//        canvas.drawARGB(0, 0, 0, 0);
//        paint.setColor(-16777216);
//        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        canvas.drawBitmap(bitmap, rect, rect, paint);
//        bitmap.recycle();
//        return output;
//    }
//
//    private void startPhotoZoom(Uri uri) {
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        if(Build.VERSION.SDK_INT >= 19) {
//            String url = GetImagePath.getImagePath().getPath(uri);
//            intent.setDataAndType(Uri.fromFile(new File(url)), "image/*");
//        } else {
//            intent.setDataAndType(uri, "image/*");
//        }
//        intent.putExtra("crop", "true");
//        intent.putExtra("aspectX", intentUtils.getIntExtra("aspectX", 1));
//        intent.putExtra("aspectY", intentUtils.getIntExtra("aspectY", 1));
//        intent.putExtra("outputX", intentUtils.getIntExtra("outputX", 150));
//        intent.putExtra("outputY", intentUtils.getIntExtra("outputY", 150));
//        intent.putExtra("scale", true);
//        intent.putExtra("scaleUpIfNeeded", true);
//        trimPath();
//        intent.putExtra("return-data", false);
//        intent.putExtra("output", Uri.fromFile(new File(this.getPath())));
//        activity.startActivityForResult(intent, this.isRound?104:103);
//    }
//
//    private void SaveBitmap(Bitmap bitmap) {
//        this.trimPath();
//        FileOutputStream fOut = null;
//
//        try {
//            fOut = new FileOutputStream(this.getPath());
//        } catch (FileNotFoundException var6) {
//            var6.printStackTrace();
//        }
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
//        try {
//            fOut.flush();
//        } catch (IOException var5) {
//            var5.printStackTrace();
//        }
//        try {
//            fOut.close();
//        } catch (IOException var4) {
//            var4.printStackTrace();
//        }
//
//    }
//
//    private String selectImage(Context context, Intent data) {
//        Uri selectedImage = data.getData();
//        if(selectedImage != null) {
//            String filePathColumn = selectedImage.toString();
//            String cursor = filePathColumn.substring(10, filePathColumn.length());
//            if(cursor.startsWith("com.sec.android.gallery3d")) {
//                Log.e("It's auto backup pic path:" + selectedImage.toString());
//                return null;
//            }
//        }
//
//        String[] filePathColumn1 = new String[]{"_data"};
//        Cursor cursor1 = context.getContentResolver().query(selectedImage, filePathColumn1, (String)null, (String[])null, (String)null);
//        if(cursor1 == null) {
//            return selectedImage.getPath();
//        } else {
//            cursor1.moveToFirst();
//            int columnIndex = cursor1.getColumnIndex(filePathColumn1[0]);
//            String picturePath = cursor1.getString(columnIndex);
//            cursor1.close();
//            return picturePath;
//        }
//    }
//
//}
//
