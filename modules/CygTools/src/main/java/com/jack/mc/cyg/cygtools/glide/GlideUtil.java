package com.jack.mc.cyg.cygtools.glide;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jack.mc.cyg.cygtools.drawableresource.CygDrawableResource;


/**
 *
 */
public final class GlideUtil {

    private GlideUtil() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Glide显示图片(Integer、File、URL、Uri、String)
    public static void setDrawable(ImageView imageView, CygDrawableResource drawableResource, int placeholderImgRes) {
        if (imageView == null) {
            return;
        }
        if (drawableResource == null) {
            return;
        }
        switch (drawableResource.getType()) {
            case Integer:
                Glide.with(imageView.getContext())
                        .load(drawableResource.getInteger())
                        .placeholder(placeholderImgRes).error(placeholderImgRes)
                        .into(imageView);
                break;
            case File:
                Glide.with(imageView.getContext())
                        .load(drawableResource.getFile())
                        .placeholder(placeholderImgRes).error(placeholderImgRes)
                        .into(imageView);
                break;
            case URL:
                Glide.with(imageView.getContext())
                        .load(drawableResource.getURL())
                        .placeholder(placeholderImgRes).error(placeholderImgRes)
                        .into(imageView);
                break;
            case Uri:
                Glide.with(imageView.getContext())
                        .load(drawableResource.getUri())
                        .placeholder(placeholderImgRes).error(placeholderImgRes)
                        .into(imageView);
                break;
            case String:
                Glide.with(imageView.getContext())
                        .load(drawableResource.getString())
                        .placeholder(placeholderImgRes).error(placeholderImgRes)
                        .into(imageView);
                break;
            case INVALID:
                break;
            default:
                break;
        }
    }

    /**
     * 用Glide设置图片圆形显示，一般是头像
     *
     * @param imgUrl            图片url
     * @param imageView         显示的图片
     * @param placeholderImgRes 占位图或加载出错图片资源
     */
    public static void setCircleTransforms(String imgUrl, ImageView imageView, int placeholderImgRes) {
        Glide.with(imageView.getContext()).load(imgUrl)
                .placeholder(placeholderImgRes).error(placeholderImgRes)
                .transform(new GlideCircleTransform(imageView.getContext()))
                .into(imageView);
    }
}
