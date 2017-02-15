package org.hybridsquad.android.library;

import com.jack.mc.cyg.cygtools.util.CygLog;
import com.jack.mc.cyg.cygtools.util.CygToast;

/**
 *
 */
public abstract class CropCallback implements CropHandler {

    @Override
    public void onCancel() {
        CygLog.debug("onCancel");
    }

    @Override
    public void onFailed(String message) {
        CygLog.error();
        CygToast.showToast(message);
    }
}