package com.framework.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Log {

    private Log() {
    }

    private static Logger getLogger() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return LogManager.getLogger(stackTrace[3].getClassName());
    }

    public static void info(String message) {
        getLogger().info(message);
    }

    public static void debug(String message) {
        getLogger().debug(message);
    }

    public static void warn(String message) {
        getLogger().warn(message);
    }

    public static void warn(String message, Throwable throwable) {
        getLogger().warn(message, throwable);
    }

    public static void error(String message) {
        getLogger().error(message);
    }

    public static void error(String message, Throwable throwable) {
        getLogger().error(message, throwable);
    }

    public static void fatal(String message) {
        getLogger().fatal(message);
    }

    public static void fatal(String message, Throwable throwable) {
        getLogger().fatal(message, throwable);
    }

    public static void trace(String message) {
        getLogger().trace(message);
    }
}
