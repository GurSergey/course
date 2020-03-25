package com.company.session;

import java.util.HashMap;

public class UserSessionStorage {

        private static final ThreadLocal<HashMap<String, Integer>> userSession =
                new ThreadLocal<HashMap<String, Integer>>();
        public static void setSession(String sessionId, int id) {
            userSession.get().put(sessionId, id);
        }

        public static Integer getIdUser(String sessionId) {
            return userSession.get().get(sessionId);
        }

        public static void deleteSession(String sessionId) {
            userSession.get().remove(sessionId);
        }

}
