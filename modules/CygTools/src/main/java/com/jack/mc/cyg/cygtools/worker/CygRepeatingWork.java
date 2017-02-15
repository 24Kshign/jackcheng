package com.jack.mc.cyg.cygtools.worker;


import com.jack.mc.cyg.cygtools.util.CygLog;

/**
 *
 */
public abstract class CygRepeatingWork implements CygWorker.Work {

    private final Runnable mTask;
    protected final long mDelayMillis;
    protected final long mIntervalMillis;
    protected int mRepeatCount;

    protected CygRepeatingWork(Runnable task, long delayMillis, long intervalMillis, int repeatCount) {
        mTask = task;
        mDelayMillis = delayMillis;
        mIntervalMillis = intervalMillis;
        mRepeatCount = repeatCount;
    }

    @Override
    public boolean isWorkRunning() {
        return mRepeatCount > 0;
    }

    protected void runTask() {
        if (mRepeatCount <= 0) {
            CygLog.debug(getClass().getSimpleName() + " repeat count <= 0");
            return;
        }

        Runnable runnable = mTask;
        if (runnable == null) {
            CygLog.error(getClass().getSimpleName() + " task is null");
            mRepeatCount = 0;
            return;
        }

        try {
            runnable.run();
        } catch (Exception e) {
            CygLog.error(getClass().getSimpleName() + "run error", e);
        }

        if (mRepeatCount != Integer.MAX_VALUE) {
            mRepeatCount--;
        }
    }
}
