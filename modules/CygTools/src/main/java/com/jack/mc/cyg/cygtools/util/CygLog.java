package com.jack.mc.cyg.cygtools.util;

import android.util.Log;

/**
 * 仿照logger对Log的封装，能自动获取当前栈的类名和方法名等信息
 */
public final class CygLog {

    private CygLog() {
    }

    private static int sLogLevel = Log.DEBUG;

    public static void setLogLevel(int logLevel){
        sLogLevel = logLevel;
    }

    /***********************************************************************************************
     * 有tag
     ***********************************************************************************************/

    // msg
    public static int v(String tag, String msg) { return log(Log.VERBOSE, tag, msg, null); }
    public static int d(String tag, String msg) { return log(Log.DEBUG, tag, msg, null); }
    public static int i(String tag, String msg) { return log(Log.INFO, tag, msg, null); }
    public static int w(String tag, String msg) { return log(Log.WARN, tag, msg, null); }
    public static int e(String tag, String msg) { return log(Log.ERROR, tag, msg, null); }

    // tr
    public static int v(String tag, Throwable tr) { return log(Log.VERBOSE, tag, "", tr); }
    public static int d(String tag, Throwable tr) { return log(Log.DEBUG, tag, "", tr); }
    public static int i(String tag, Throwable tr) { return log(Log.INFO, tag, "", tr); }
    public static int w(String tag, Throwable tr) { return log(Log.WARN, tag, "", tr); }
    public static int e(String tag, Throwable tr) { return log(Log.ERROR, tag, "", tr); }

    // msg, tr
    public static int v(String tag, String msg, Throwable tr) { return log(Log.VERBOSE, tag, msg, tr); }
    public static int d(String tag, String msg, Throwable tr) { return log(Log.DEBUG, tag, msg, tr); }
    public static int i(String tag, String msg, Throwable tr) { return log(Log.INFO, tag, msg, tr); }
    public static int w(String tag, String msg, Throwable tr) { return log(Log.WARN, tag, msg, tr); }
    public static int e(String tag, String msg, Throwable tr) { return log(Log.ERROR, tag, msg, tr); }

    /***********************************************************************************************
     * 无tag，从栈信息里获取当前class生成tag，还有method，line信息
     ***********************************************************************************************/

    // 无参数
    public static int verbose() { return log(Log.VERBOSE, null, "", null); }
    public static int debug() { return log(Log.DEBUG, null, "", null); }
    public static int info() { return log(Log.INFO, null, "", null); }
    public static int warn() { return log(Log.WARN, null, "", null); }
    public static int error() { return log(Log.ERROR, null, "", null); }

    // msg
    public static int verbose(String msg) { return log(Log.VERBOSE, null, msg, null); }
    public static int debug(String msg) { return log(Log.DEBUG, null, msg, null); }
    public static int info(String msg) { return log(Log.INFO, null, msg, null); }
    public static int warn(String msg) { return log(Log.WARN, null, msg, null); }
    public static int error(String msg) { return log(Log.ERROR, null, msg, null); }

    // tr
    public static int verbose(Throwable tr) { return log(Log.VERBOSE, null, "", tr); }
    public static int debug(Throwable tr) { return log(Log.DEBUG, null, "", tr); }
    public static int info(Throwable tr) { return log(Log.INFO, null, "", tr); }
    public static int warn(Throwable tr) { return log(Log.WARN, null, "", tr); }
    public static int error(Throwable tr) { return log(Log.ERROR, null, "", tr); }

    // msg, tr
    public static int verbose(String msg, Throwable tr) { return log(Log.VERBOSE, null, msg, tr); }
    public static int debug(String msg, Throwable tr) { return log(Log.DEBUG, null, msg, tr); }
    public static int info(String msg, Throwable tr) { return log(Log.INFO, null, msg, tr); }
    public static int warn(String msg, Throwable tr) { return log(Log.WARN, null, msg, tr); }
    public static int error(String msg, Throwable tr) { return log(Log.ERROR, null, msg, tr); }

    /***********************************************************************************************
     * 无tag，从指定的栈层次里获取当前class生成tag，还有method，line信息
     ***********************************************************************************************/

    // 无参数
    public static int verboseEx(int stackLevel) { return logEx(Log.VERBOSE, "", null, stackLevel); }
    public static int debugEx(int stackLevel) { return logEx(Log.DEBUG, "", null, stackLevel); }
    public static int infoEx(int stackLevel) { return logEx(Log.INFO, "", null, stackLevel); }
    public static int warnEx(int stackLevel) { return logEx(Log.WARN, "", null, stackLevel); }
    public static int errorEx(int stackLevel) { return logEx(Log.ERROR, "", null, stackLevel); }

    // msg
    public static int verboseEx(String msg, int stackLevel) { return logEx(Log.VERBOSE, msg, null, stackLevel); }
    public static int debugEx(String msg, int stackLevel) { return logEx(Log.DEBUG, msg, null, stackLevel); }
    public static int infoEx(String msg, int stackLevel) { return logEx(Log.INFO, msg, null, stackLevel); }
    public static int warnEx(String msg, int stackLevel) { return logEx(Log.WARN, msg, null, stackLevel); }
    public static int errorEx(String msg, int stackLevel) { return logEx(Log.ERROR, msg, null, stackLevel); }

    // tr
    public static int verboseEx(Throwable tr, int stackLevel) { return logEx(Log.VERBOSE, "", tr, stackLevel); }
    public static int debugEx(Throwable tr, int stackLevel) { return logEx(Log.DEBUG, "", tr, stackLevel); }
    public static int infoEx(Throwable tr, int stackLevel) { return logEx(Log.INFO, "", tr, stackLevel); }
    public static int warnEx(Throwable tr, int stackLevel) { return logEx(Log.WARN, "", tr, stackLevel); }
    public static int errorEx(Throwable tr, int stackLevel) { return logEx(Log.ERROR, "", tr, stackLevel); }

    // tr
    public static int verboseEx(String msg, Throwable tr, int stackLevel) { return logEx(Log.VERBOSE, msg, tr, stackLevel); }
    public static int debugEx(String msg, Throwable tr, int stackLevel) { return logEx(Log.DEBUG, msg, tr, stackLevel); }
    public static int infoEx(String msg, Throwable tr, int stackLevel) { return logEx(Log.INFO, msg, tr, stackLevel); }
    public static int warnEx(String msg, Throwable tr, int stackLevel) { return logEx(Log.WARN, msg, tr, stackLevel); }
    public static int errorEx(String msg, Throwable tr, int stackLevel) { return logEx(Log.ERROR, msg, tr, stackLevel); }

    /***********************************************************************************************
     * log和logEx，校验日志等级
     ***********************************************************************************************/

    private static boolean isLogLevelEnable(int logLevel) {
        if (CygDebugMode.isDebugMode()) {
            return true;
        }
        return logLevel >= sLogLevel;
    }

    private static final int CALL_LOG_STACK_LEVEL = 2;

    private static int log(int logLevel, String tag, String msg, Throwable tr) {
        if (!isLogLevelEnable(logLevel)) {
            return 0;
        }
        if (CygString.isEmpty(tag)) {
            return logImpl(logLevel, new Throwable().getStackTrace()[CALL_LOG_STACK_LEVEL], msg, tr);
        } else {
            return logImpl(logLevel, tag, msg, tr);
        }
    }

    public static final int CURRENT_STACK_LEVEL = 0;
    public static final int BELOW_CURRENT_STACK_LEVEL_1 = 1;
    public static final int BELOW_CURRENT_STACK_LEVEL_2 = 2;
    public static final int BELOW_CURRENT_STACK_LEVEL_3 = 3;
    public static final int BELOW_CURRENT_STACK_LEVEL_4 = 4;
    public static final int BELOW_CURRENT_STACK_LEVEL_5 = 5;
    public static final int BELOW_CURRENT_STACK_LEVEL_6 = 6;
    public static final int BELOW_CURRENT_STACK_LEVEL_7 = 7;
    public static final int BELOW_CURRENT_STACK_LEVEL_8 = 8;
    public static final int BELOW_CURRENT_STACK_LEVEL_9 = 9;

    // 无tag，从指定的栈层次里获取当前class生成tag，还有method，line信息
    private static int logEx(int logLevel, String msg, Throwable tr, int stackLevel) {
        if (!isLogLevelEnable(logLevel)) {
            return 0;
        }
        return logImpl(logLevel, new Throwable().getStackTrace()[CALL_LOG_STACK_LEVEL + stackLevel], msg, tr);
    }

    /***********************************************************************************************
     * logImpl
     ***********************************************************************************************/

    // 有tag
    private static int logImpl(int logLevel, String tag, String msg, Throwable tr) {
        String logTag = CygLog.class.getSimpleName() + "-" +  tag;
        String logMsg;
        if (tr != null) {
            logMsg = CygString.valueOf(msg) + " \n" + Log.getStackTraceString(tr);
        } else {
            logMsg = CygString.valueOf(msg);
        }
        if (logLevel >= Log.WARN) {
            if (CygDebugMode.isDebugMode()) {
                CygToast.showToast(logTag + "\n==============================\n" + logMsg);
            }
        }
        return Log.println(logLevel, logTag, logMsg);
    }

    // 无tag，从栈信息里获取当前class生成tag，还有method，line信息
    private static int logImpl(int logLevel, StackTraceElement stackTraceElement, String msg, Throwable tr) {
        return logImpl(logLevel, stackTraceElement.getClassName(),
                stackTraceElement.getMethodName() + "(), Line " + stackTraceElement.getLineNumber() + ". " + CygString.valueOf(msg), tr);
    }
}
