package com.company.session;

import java.util.HashMap;

public class UserSessionStorage {

        private static final HashMap<String, Integer> userSession =
                new HashMap<String, Integer>();

        public static void setSession(String sessionId, int id) {
            //HashMap<String, Integer> test = userSession.get();
            userSession.put(sessionId, id);
        }

        public static Integer getIdUser(String sessionId) {
//            HashMap<String, Integer> test = userSession.get();
            return userSession.get(sessionId);
        }

        public static void deleteSession(String sessionId) {
            userSession.remove(sessionId);
        }

}
