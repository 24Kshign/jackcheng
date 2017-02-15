package com.jack.mc.cyg.cygtools.worker;

/**
 *
 */
public class CygWorker {

    public interface Work {
        boolean startWork();
        void stopWork();
        boolean isWorkRunning();
    }

    private Work mWork;

    public CygWorker() {
    }

    protected boolean start(Work work) {
        stop();    //先停止上一次的start,为了防止多次start
        mWork = work;
        if (mWork.startWork()) {
            return true;
        } else {
            stop();
            return false;
        }
    }

    public void stop() {
        if (mWork == null) {
            return;
        }
        mWork.stopWork();
        mWork = null;
    }

    public boolean isWorking() {
        return mWork != null && mWork.isWorkRunning();
    }
}
