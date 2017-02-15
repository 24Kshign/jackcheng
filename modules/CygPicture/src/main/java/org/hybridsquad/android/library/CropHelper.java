package org.hybridsquad.android.library;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.jack.mc.cyg.cygtools.util.CygLog;
import com.jack.mc.cyg.cygtools.util.CygEnvironment;

import org.hybridsquad.android.library.bean.WidthAndHeight;
import org.hybridsquad.android.library.utils.CompressImageUtils;
import org.hybridsquad.android.library.utils.CropFileUtils;

import java.io.File;

/**
 *
 */
public class CropHelper {

    /**
     * request code of Activities or Fragments
     * You will have to change the values of the request codes below if they conflict with your own.
     */
    public static final int REQUEST_CROP = 127;
    public static final int REQUEST_CAMERA = 128;
    public static final int REQUEST_PICK = 129;
    public static final Integer MAX_PIXEL = 500000;

    public static final String CROP_CACHE_FOLDER = "PhotoCropper";

    public static Uri generateUri() {
        File cacheFolder = new File(CygEnvironment.getExternalStorageDir() + File.separator + CROP_CACHE_FOLDER);
        if (!cacheFolder.exists()) {
            try {
                boolean result = cacheFolder.mkdir();
                CygLog.debug("generateUri " + cacheFolder + " result: " + (result ? "succeeded" : "failed"));
            } catch (Exception e) {
                CygLog.debug("generateUri failed: " + cacheFolder, e);
            }
        }
        String name = String.format("image-%d.jpg", System.currentTimeMillis());
        return Uri
                .fromFile(cacheFolder)
                .buildUpon()
                .appendPath(name)
                .build();
    }

    public static WidthAndHeight getWidthAndHeight(int width, int height) {
        WidthAndHeight widthAndHeight = new WidthAndHeight();
        Integer pixel = width * height;
        widthAndHeight.setWidth((int) (width / (Math.sqrt(Double.valueOf(pixel / MAX_PIXEL)))));
        widthAndHeight.setHeight((int) (height / (Math.sqrt(Double.valueOf(pixel / MAX_PIXEL)))));
        return widthAndHeight;
    }

    public static boolean isPhotoReallyCropped(Uri uri) {
        File file = new File(uri.getPath());
        long length = file.length();
        return length > 0;
    }

    public static void handleResult(CropCallback callback, int requestCode, int resultCode, Intent data) {
        if (callback == null) {
            return;
        }
        if (resultCode == Activity.RESULT_CANCELED) {
            callback.onCancel();
        } else if (resultCode == Activity.RESULT_OK) {
            CropParams cropParams = callback.getCropParams();
            if (cropParams == null) {
                callback.onFailed("CropHandler's params MUST NOT be null!");
                return;
            }
            switch (requestCode) {
                case REQUEST_PICK:
                case REQUEST_CROP:
                    if (isPhotoReallyCropped(cropParams.uri)) {
                        CygLog.debug("Photo cropped!");
                        onPhotoCropped(callback, cropParams);
                        break;
                    } else {
                        Context context = callback.getCropParams().context;
                        if (context != null) {
                            if (data != null && data.getData() != null) {
                                String path = CropFileUtils.getSmartFilePath(context, data.getData());
                                boolean result = CropFileUtils.copyFile(path, cropParams.uri.getPath());
                                if (!result) {
                                    callback.onFailed("Copy file to cached folder failed");
                                    break;
                                }
                            } else {
                                callback.onFailed("Returned data is null " + data);
                                break;
                            }
                        } else {
                            callback.onFailed("CropHandler's context MUST NOT be null!");
                        }
                    }
                case REQUEST_CAMERA:
                    if (cropParams.enable) {
                        // Send this Uri to Crop
                        Intent intent = buildCropFromUriIntent(cropParams);
                        callback.handleIntent(intent, REQUEST_CROP);
                    } else {
                        CygLog.debug("Photo cropped!");
                        onPhotoCropped(callback, cropParams);
                    }
                    break;
            }
        }
    }

    private static void onPhotoCropped(CropHandler handler, CropParams cropParams) {
        setCropParams(cropParams);
        if (cropParams.compress) {
            Uri originUri = cropParams.uri;
            Uri compressUri = CropHelper.generateUri();
            CompressImageUtils.compressImageFile(cropParams, originUri, compressUri);
            handler.onCompressed(compressUri);
        } else {
            handler.onPhotoCropped(cropParams.uri);
        }
    }

    private static void setCropParams(CropParams cropParams) {
        if (cropParams == null) {
            return;
        }
        WidthAndHeight widthAndHeight = getWidthAndHeight(cropParams.FileToBitmap().getWidth(), cropParams.FileToBitmap().getHeight());
        cropParams.setWidth(widthAndHeight.getWidth());
        cropParams.setHeight(widthAndHeight.getHeight());
    }

    // None-Crop Intents

    public static Intent buildGalleryIntent(CropParams cropParams) {
        return new Intent(Intent.ACTION_GET_CONTENT)
                .setType("image/*")
                .putExtra(MediaStore.EXTRA_OUTPUT, cropParams.uri);
    }

    public static Intent buildCameraIntent(CropParams cropParams) {
        return new Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                .putExtra(MediaStore.EXTRA_OUTPUT, cropParams.uri);
    }

    // Crop Intents

    private static Intent buildCropFromUriIntent(CropParams cropParams) {
        return buildCropIntent("com.android.camera.action.CROP", cropParams);
    }

    private static Intent buildCropIntent(String action, CropParams cropParams) {
        setCropParams(cropParams);
        return new Intent(action)
                .setDataAndType(cropParams.uri, cropParams.type)
                .putExtra("crop", "true")  //裁剪特的征标志
                .putExtra("scale", cropParams.scale)  //需不需要测量
                .putExtra("aspectX", cropParams.getWidth())  //与aspectY构成纵横比
                .putExtra("aspectY", cropParams.getHeight())
                .putExtra("outputX", cropParams.getWidth())  //想要生成图片的宽度
                .putExtra("outputY", cropParams.getHeight())  //想要生成图片的高度
                .putExtra("return-data", cropParams.returnData)
                .putExtra("outputFormat", cropParams.outputFormat)
                .putExtra("noFaceDetection", cropParams.noFaceDetection)
                .putExtra("scaleUpIfNeeded", cropParams.scaleUpIfNeeded)
                .putExtra(MediaStore.EXTRA_OUTPUT, cropParams.uri);  //将这个uri设置成file
    }

    // Clear Cache

    public static boolean clearCacheDir() {
        File cacheFolder = new File(Environment.getExternalStorageDirectory() + File.separator + CROP_CACHE_FOLDER);
        if (cacheFolder.exists() && cacheFolder.listFiles() != null) {
            for (File file : cacheFolder.listFiles()) {
                boolean result = file.delete();
                CygLog.debug("Delete " + file.getAbsolutePath() + (result ? " succeeded" : " failed"));
            }
            return true;
        }
        return false;
    }

    public static boolean clearCachedCropFile(Uri uri) {
        if (uri == null) {
            return false;
        }
        File file = new File(uri.getPath());
        if (file.exists()) {
            boolean result = file.delete();
            CygLog.debug("Delete " + file.getAbsolutePath() + (result ? " succeeded" : " failed"));
            return result;
        }
        return false;
    }
}
