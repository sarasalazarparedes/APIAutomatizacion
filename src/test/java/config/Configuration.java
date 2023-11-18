package config;

public class Configuration {
    public static String host="https://todo.ly";
    private static String user;
    public static String pwd="12345";

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        Configuration.host = host;
    }

    public  String getUser() {
        return user;
    }

    public void setUser(String user) {
        Configuration.user = user;
    }

    public static String getPwd() {
        return pwd;
    }

    public static void setPwd(String pwd) {
        Configuration.pwd = pwd;
    }
}
