package com.inwhite;


/**
 * Created by Jeka on 18/06/2014.
 */
public class Main {
    private final static String TOMCAT_HOME = "C:\\Users\\JavaSchoolStudent\\apache-tomcat-7.0.70";
    private static final String SRC_MAIN_WEBAPP = "src/main/webapp/";

    public static void main(String[] args) {
      /*  Tomcat tomcat = null;
        try {
            tomcat = new Tomcat();
            tomcat.setBaseDir(TOMCAT_HOME + "/work");
            tomcat.addWebapp("/fullstack", new File(SRC_MAIN_WEBAPP).getAbsolutePath());
            tomcat.start();
            tomcat.getServer().await();
        } catch (Exception e) {
            System.err.println("Could not start the Tomcat server: " + e);
            if (tomcat != null) {
                try {
                    tomcat.stop();
                } catch (Exception e1) {
                    System.err.println("Unable to stop the Tomcat server: " + e1);
                }
            }
        }*/
    }
}
