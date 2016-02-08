package com.airhacks.doit.business.monitoring.boundary;

import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.airhacks.doit.business.logging.boundary.LogSink;
import com.airhacks.doit.business.monitoring.entity.CallEvent;

/**
 * Created by sebastianbasner on 02.12.15.
 */
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@Singleton
public class MonitorSink {

   @Inject
   LogSink LOG;

   // CopyOnWriteArrayList is needed because MonitorSink is concurrent and CopyOnWriteArrayList
   // can be read an written to concurrent without any exceptions
   CopyOnWriteArrayList<CallEvent> recentEvents;

   @PostConstruct
   public void init() {
      this.recentEvents = new CopyOnWriteArrayList<>();
   }

   public void onCallEvent(@Observes CallEvent event) {
      LOG.log(event.toString());
      this.recentEvents.add(event);
   }

   public List<CallEvent> getRecentEvents() {
      return this.recentEvents;
   }

   public LongSummaryStatistics getStatistics() {
      return  this.recentEvents.stream().
            collect(Collectors.summarizingLong(CallEvent::getDuration));
   }
}
