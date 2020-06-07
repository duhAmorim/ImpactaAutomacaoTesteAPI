package definition;

import groovy.json.internal.LazyMap;

/**
 * Created by robson on 30/08/2017.
 */
public class Processos {

    private static final String URL_USER = "http://agapito-server.herokuapp.com/processos/";
    private static LazyMap fieldsJson = new LazyMap();
    private static String lastUser;

    public static void addFields(String field, String value) {
        fieldsJson.put(field, value);
    }

    public static void clearFields() {
        fieldsJson.clear();
    }

    public static LazyMap getFields() {
        return fieldsJson;
    }

    public static String getEndPoint() {
        return URL_USER;
    }

    public static String getLastUser() {
        return lastUser;
    }

    public static void setLastUser(String lastUser) {
        Processos.lastUser = lastUser;
    }
}
