package org.hybridsquad.android.library;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import org.hybridsquad.android.library.utils.CropFileUtils;

/**
 *
 */
public class CropParams {

    public static final String CROP_TYPE = "image/*";
    public static final String OUTPUT_FORMAT = Bitmap.CompressFormat.JPEG.toString();

    public Uri uri;

    public String type;
    public String outputFormat;
    public String crop;

    public boolean scale;
    public boolean returnData;
    public boolean noFaceDetection;
    public boolean scaleUpIfNeeded;

    /**
     * Default is true, if set false, crop function will not work,
     * it will only pick up images from gallery or take pictures from camera.
     */
    public boolean enable;

    /**
     * Default is false, if it is from capture and without crop, the image could be large
     * enough to trigger OOM, it is better to compress image while enable is false
     */
    public boolean compress;

    public boolean rotateToCorrectDirection;

    public int width;
    public int height;
    public int compressQuality;

    public Context context;

    public CropParams(Context context) {
        this.context = context;
        type = CROP_TYPE;
        outputFormat = OUTPUT_FORMAT;
        crop = "true";
        scale = true;
        returnData = false;
        noFaceDetection = true;
        scaleUpIfNeeded = true;
        enable = true;
        rotateToCorrectDirection = false;
        compress = false;
        refreshUri();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void refreshUri() {
        uri = CropHelper.generateUri();
    }

    public Bitmap FileToBitmap() {
        if (uri == null) {
            return null;
        }
        String filePath = CropFileUtils.getSmartFilePath(context, uri);
        return BitmapFactory.decodeFile(filePath);
    }
}
