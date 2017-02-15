package com.jack.mc.cyg.cygtools.timer;


import com.jack.mc.cyg.cygtools.handler.CygMainLooperHandler;
import com.jack.mc.cyg.cygtools.util.CygLog;
import com.jack.mc.cyg.cygtools.worker.CygRepeatingWork;
import com.jack.mc.cyg.cygtools.worker.CygWorker;

/**
 * 定时器，只能在主线程使用，timer任务执行也是在主线程
 */
public class CygTimer extends CygWorker {

    private static final class Work extends CygRepeatingWork {

        private final class TimerRunnable implements Runnable {
            @Override
            public void run() {
                CygLog.debug();
                runTask();
                if (mRepeatCount > 0) {
                    if (!CygMainLooperHandler.getInstance().postDelayed(mTimerRunnable, mIntervalMillis)) {
                        CygLog.error("main looper postDelayed error");
                    }
                }
            }
        }

        private TimerRunnable mTimerRunnable;

        public Work(Runnable task, long delayMillis, long intervalMillis, int repeatCount) {
            super(task, delayMillis, intervalMillis, repeatCount);
        }

        @Override
        public boolean startWork() {
            mTimerRunnable = new TimerRunnable();
            return CygMainLooperHandler.getInstance().postDelayed(mTimerRunnable, mDelayMillis);
        }

        @Override
        public void stopWork() {
            mRepeatCount = 0;
            if (mTimerRunnable != null) {
                CygMainLooperHandler.getInstance().removeCallbacks(mTimerRunnable);
                mTimerRunnable = null;
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public CygTimer() {
    }

    public void schedule(long delayMillis, long intervalMillis, int repeatCount, Runnable task) {
        if (intervalMillis <= 0 || repeatCount <= 0 || task == null) {
            CygLog.error("param error");
            return;
        }
        start(new Work(task, delayMillis, intervalMillis, repeatCount));
    }

    public void schedule(long delayMillis, long intervalMillis, Runnable task) {
        schedule(delayMillis, intervalMillis, Integer.MAX_VALUE, task);
    }
}
