package com.airhacks.doit.business.reminders.boundary;

import java.io.IOException;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.airhacks.doit.business.reminders.entity.ToDo;

/**
 * Created by sebastianbasner on 11.02.16.
 */
@Singleton
@ServerEndpoint("/changes") //websocket
@ConcurrencyManagement(ConcurrencyManagementType.BEAN) //no further locking is needed
public class ToDoChangeTracker {

   private Session session;

   /**
    * Open the websocket session.
    * @param session
    */
   @OnOpen
   public void onOpen(Session session) {
      this.session = session;
   }

   /**
    * Closes the open websocket session.
    */
   @OnClose
   public void onClose() {
      this.session = null;
   }

   public void onToDoChange (@Observes(during = TransactionPhase.AFTER_SUCCESS) ToDo todo) {
      if (this.session != null && this.session.isOpen()) {
         try {
            this.session.getBasicRemote().sendText(todo.toString() );
         } catch (IOException e) {
            //can be ignored here
         }
      }
   }
}
