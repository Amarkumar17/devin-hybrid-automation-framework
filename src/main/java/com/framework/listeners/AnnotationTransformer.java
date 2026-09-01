package com.framework.listeners;

import com.framework.config.ConfigReader;
import com.framework.retry.RetryAnalyzer;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationTransformer implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        if (testMethod != null) {
            annotation.setRetryAnalyzer(RetryAnalyzer.class);
            annotation.setInvocationCount(1);
            annotation.setThreadPoolSize(1);
        }
    }
}
