 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.config;

import com.jcraft.jsch.JSchException;
import eu.gymnaila.chunks.artiver.controller.MasterDataController;
import eu.gymnaila.chunks.artiver.entity.*;
import eu.gymnaila.chunks.artiver.exceptions.UserAlreadySetException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * <b>Configuration of the Application</b>
 * </BR>
 * This class contains data that is needed in various other places in the application.
 * Additionally it manages the global config of the software.
 * 
 * @author Matze
 * @version 1.8
 */

public class AppConfig
{
    // TODO Set the Per-Unit String private!
    public static final String PERSISTENCE_UNIT_NAME = "ArtiVerPU";

    // Article for edit
    private static Article editArticle = null;
    
    // Stock for edit
    private static Stock editStock = null;
    
    // State for edit
    private static State editState = null;
    
    // Category for edit
    private static Category editCategory = null;
    
    // Factory for EntityManagers
    private static EntityManagerFactory factory;
    
    // Current user
    private static User curUser;
    
    // Password in clear, decrypted form. Needed for DB-Login
    private static String curDecrPass;
        
    // Properties from prop file
    private static Properties properties = new Properties();
        
    // User-Counter ensures, that only the logged-in User is set
    private static int ctrUser=0;
    
    // SSH Tunnel
    private static Tunnel sshTunnel = null;
    
    // Debug flag
    private static boolean debug;
 
    // Initialized flag
    private static boolean init = false;
    
    // MasterData Object
    private static MasterData masterData = null;
    
    // TODO Config Data from DB, about DB and other information relevant for the application
    
    
    /**
     * Initializes AppConfig class with persistent saved properties
     * @throws FileNotFoundException
     * @throws IOException
     * @throws Exception 
     */
    public static void init() throws FileNotFoundException, IOException, Exception
    {
        // Properties file
        File propFile;
        if(!init)
        {
            propFile = new File("data/AppConfigProp.properties");
            
            System.out.println(getPathToJarfileDir());
            
            // If a global file exists, take it. Else: take the standard one in the .jar-File
            if (!propFile.exists()) 
            {
                propFile = new File(getPathToJarfileDir() + "/data/AppConfigProp.properties");
                
                if (!propFile.exists()) 
                {
                    propFile = new File("dist/data/AppConfigProp.properties");
                 
                    System.out.println(propFile.getAbsoluteFile());
                    
                    if (!propFile.exists()) 
                    {
                        System.out.println("Using .jar-Prop-File");
                        propFile = new File(AppConfig.class.getResource("AppConfigProp.properties").getPath());
                        System.out.println(propFile.getAbsolutePath());
                    }
                }
            }

            // Load properties from the right file
            properties.load(new FileInputStream(propFile));

            // Assign init properties
            debug = Boolean.parseBoolean(properties.getProperty("artiver.debugMode"));
            
        }

        MasterDataController mdc = new MasterDataController();
        masterData = mdc.getMasterData();
        
        // Init completed status update
        init = true;
    }
    
    
    
    /**
     * Creates a new, for the application pre-configured EntityManager
     * @return pre-configured EntityManager for DB-Stuff
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static EntityManager createEntityManager()
    {          
        if(sshTunnel == null)
        {
            // Establish tunnel, if needed
            if(properties.getProperty("artiver.usesSSHMysqlConnection").equals("1"))
            {
                sshTunnel = createTunnel();
                
                
                // TODO WICHTIG!!! das try-catch unten mit einem throws ersetzen
                // TODO (sshTunnel.go() einfach aus dem try-catch nehmen und dann try-catch entfernen)
                // TODO Achtung: Anpassung in den Controllern!
                try {
                    sshTunnel.go();
                } catch (JSchException ex) {
                    Logger.getLogger(AppConfig.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if(factory == null)
        {
            // Map (Key, Value) for EntityManager configuration 
            Map persistProps = new HashMap();

            // If DBUser and User are the same, take login data to access the database. Else: take pre-configured connection
            if(curUser != null && curDecrPass != null && properties.getProperty("artiver.appUserEqualsDbUser").equals("1"))
            {
                persistProps.put("javax.persistence.jdbc.user", curUser.getName());
                persistProps.put("javax.persistence.jdbc.password", curDecrPass);
            }
            else
            {
                persistProps.put("javax.persistence.jdbc.user", properties.getProperty("javax.persistence.jdbc.user"));
                persistProps.put("javax.persistence.jdbc.password", properties.getProperty("javax.persistence.jdbc.password"));
            }
            
            persistProps.put("javax.persistence.jdbc.driver", properties.getProperty("javax.persistence.jdbc.driver"));
            persistProps.put("javax.persistence.jdbc.url", properties.getProperty("javax.persistence.jdbc.url"));

            // With persistProps readily configured factory
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, persistProps);            
        }
        
        return factory.createEntityManager();
    }
    
    /**
     * Creates a new, for the application pre-configured SSH Tunnel for the DB connection
     * @return Tunnel object
     */
    private static Tunnel createTunnel()
    {
        String user;
        String password;
        
        String host = properties.getProperty("artiver.ssh.host");
        int port = Integer.parseInt(properties.getProperty("artiver.ssh.port"));
        int tunnelLocalPort= Integer.parseInt(properties.getProperty("artiver.ssh.tunnelLocalPort"));
        String tunnelRemoteHost= properties.getProperty("artiver.ssh.tunnelRemoteHost");
        int tunnelRemotePort= Integer.parseInt(properties.getProperty("artiver.ssh.tunnelRemotePort"));
        
        if(curUser != null && curDecrPass != null && properties.getProperty("artiver.appUserEqualsSSHUser").equals("1"))
        {
           user = curUser.getName();
           password = curDecrPass;
        }
        else
        {
            user = properties.getProperty("artiver.ssh.user");
            password = properties.getProperty("artiver.ssh.password");
        }
        
        return new Tunnel(host, user, password, port, tunnelLocalPort, tunnelRemoteHost, tunnelRemotePort);  
    }
    
    
    /**
     * Gets the path to the jar file directory
     * @return String representing the path to the jar
     */
    public static String getPathToJarfileDir() {
        String url = AppConfig.class.getResource("/" + AppConfig.class.getName().replaceAll("\\.", "/") + ".class").toString();
        url = url.substring(4).replaceFirst("/[^/]+\\.jar!.*$", "/");
        try {
            File dir = new File(new URL(url).toURI());
            url = dir.getAbsolutePath();
        } catch (MalformedURLException mue) {
            url = null;
        } catch (URISyntaxException ue) {
            url = null;
        }
        return url;
    }
    
    public static void close() 
    {
        if(sshTunnel != null)
        {
            sshTunnel.close();   
        }
        if(factory != null)
        {
            factory.close();        
        }
    }
    
    
    /*************************** Getters and Setters ***************************/
    
    /**
     * Get current User object
     * @return current User object
     */
    public static User getUser()
    {
        return curUser;
    }
    
     /**
     * Get debug mode state
     * @return true, if application is in debug mode
     */
    public static boolean isDebug()
    {
        return debug;
    }
    
    /**
     * Set the password for the current user
     * @param pass password
     */
    public static void setCurPassword(String pass)
    {
        curDecrPass = pass;
    }
    
    /**
     * Set current user.
     * 
     * This method sets the current user that is logged-in.
     * It can only be executed once!
     * 
     * @param user user that has done the log-in correctly
     * @throws UserAlreadySetException is thrown, when the user is already set for the current session.
     */
    public static void setUser(User user) throws UserAlreadySetException
    {
        if (ctrUser == 0)
        {
            curUser = user;
            ctrUser++;
        }
        else
        {
            throw new UserAlreadySetException();
        }

    }
    
    /**
     * Gets the article object that will be edited
     * @return article object that will be edited
     */
    public static Article getEditArticle() {
        return editArticle;
    }
    

    /**
     * Set the article that should be edited
     * @param editArticle article to edit
     */
    public static void setEditArticle(Article editArticle) {
        AppConfig.editArticle = editArticle;
    }

    /**
     * Gets the masterData object
     * @return masterData object
     */
    public static MasterData getMasterData() {
        return masterData;
    }
    
    /**
     * Gets the category object that will be edited
     * @return category object that will be edited
     */
    public static Category getEditCategory() {
        return editCategory;
    }

    
    /**
     * Set the category that should be edited
     * @param editCategory category to edit
     */
    public static void setEditCategory(Category editCategory) {
        AppConfig.editCategory = editCategory;
    }

    /**
     * Gets the state object that will be edited
     * @return state object that will be edited
     */
    public static State getEditState() {
        return editState;
    }

    
    /**
     * Set the state that should be edited
     * @param editState state to edit
     */
    public static void setEditState(State editState) {
        AppConfig.editState = editState;
    }

    /**
     * Gets the stock object that will be edited
     * @return stock object that will be edited
     */
    public static Stock getEditStock() {
        return editStock;
    }

    public static void setEditStock(Stock editStock) {
        AppConfig.editStock = editStock;
    }
    
    
    
}
