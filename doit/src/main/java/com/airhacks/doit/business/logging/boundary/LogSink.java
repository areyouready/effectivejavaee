package com.airhacks.doit.business.logging.boundary;

/**
 * Created by sebastianbasner on 23.11.15.
 */
@FunctionalInterface
public interface LogSink {

   void log(String msg);
}
