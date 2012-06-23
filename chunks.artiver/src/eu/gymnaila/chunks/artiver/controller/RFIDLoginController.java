package eu.gymnaila.chunks.artiver.controller;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import eu.gymnaila.chunks.artiver.config.AppConfig;
import eu.gymnaila.chunks.artiver.encryption.EncMode;
import eu.gymnaila.chunks.artiver.encryption.ShaEncrypter;
import eu.gymnaila.chunks.artiver.entity.User;
import eu.gymnaila.chunks.artiver.exceptions.CardNotPresentException;
import eu.gymnaila.chunks.artiver.exceptions.UserNotFoundException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.smartcardio.*;

/**
 * <b>Controller for the RFID-based login procedure</b>
 * </BR>
 * This controller manages the access to the card terminal (tested with SDI011), reads the data from the card
 * and compares it to the stored data in the DB.
 * 
 * @author Matze
 * @version 1.9
 */
public class RFIDLoginController
{
    /**
     * Validates the uid of the card with the uids stored in the DB
     * @param uid UID of the recognized RFID-Card (Mifare cards preferred)
     * @return true, if the UID matches a stored UID
     * @throws UserNotFoundException thrown, when the UID could not be found
     * @throws CommunicationsException thrown, when DB connection is lost
     * @throws Exception thrown in every other case, when something went wrong
     */
    private boolean checkUser(String uid) throws UserNotFoundException, CommunicationsException, Exception
    {
        EntityManager em;
        User user;
        
        // Create new EntityManager for the DB operations
        em = AppConfig.createEntityManager();

        // Create query and set parameter uid
        Query userQuery = em.createNamedQuery("User.findByUid");
        userQuery.setParameter("uid", uid);

        try
        {
            // Try to get exactly one user from the DB, with exactly that card's uid
            user = (User) userQuery.getSingleResult();
            
            // If no result is found, throw exception
            // Just a safety mechanism, if NoResultException does not fire
            if(user == null)
            {
               throw new UserNotFoundException();
            }
            
            // Assign the correct user to the current user field
            AppConfig.setUser(user);
            
            return true;
        }
        catch (NoResultException e)
        {
            throw new UserNotFoundException();
        }
        finally
        {
            // Remember: Do EVER close the EntityManager you created!!
            em.close();
        }
    }

    
    /**
     * Gets and validates the data from the RFID-Card
     * @return true, if the card is valid and the card's uid is assigned to exactly one user in the db
     * @throws CardException thrown, when a problem with the card occurs
     * @throws UserNotFoundException thrown, when the UID could not be found
     * @throws CardNotPresentException thrown, when the reader could not recognize a card
     * @throws Exception thrown in every other case, when something went wrong
     */
    public boolean validateCard() throws CardException, UserNotFoundException, CardNotPresentException, Exception
    {
        // check the user and return if valid or not
        return checkUser(getCardUid());
    }

    /**
     * Gets the data from the RFID-Card
     * @return String with the card's uid in encrypted form
     * @throws CardException thrown, when a problem with the card occurs
     * @throws UserNotFoundException thrown, when the UID could not be found
     * @throws CardNotPresentException thrown, when the reader could not recognize a card
     * @throws Exception thrown in every other case, when something went wrong
     */
    public String getCardUid() throws CardException, UserNotFoundException, CardNotPresentException, Exception
    {
        CardTerminal terminal = null;

        // Get the list of available terminals
        TerminalFactory factory = TerminalFactory.getDefault();
        List<CardTerminal> terminals = factory.terminals().list();

        String readerName = "";

        // Cycle through the terminals list and search for a contactless reader
        // TODO Would be better, if the reader is specified in the properties-file!!
        for (int i = 0; i < terminals.size(); i++)
        {
            // TODO Remove debug output
            System.out.println(terminals.get(i).toString());
            
            readerName = terminals.get(i).toString();

            if (readerName.toLowerCase(Locale.ENGLISH).contains("contactless"))
            {
                // Assign correct terminal from the list
                terminal = terminals.get(i);
            }
        }
       
        // Debug output
        if(AppConfig.isDebug())
        {
            System.out.println("Waiting for a card..");
        }
        
        
        if(terminal != null)
        {
            // Establish a connection with the card. Timeout: 5 sec
            if(terminal.waitForCardPresent(5000) == false)
            {
                throw new CardNotPresentException();
            }

            // Connect terminal and card
            Card card = terminal.connect("T=1");
            CardChannel channel = card.getBasicChannel();

            // Read the UID
            byte[] baReadUID = new byte[5];

            baReadUID = new byte[]
            {
                (byte) 0xFF, (byte) 0xCA, (byte) 0x00,
                (byte) 0x00, (byte) 0x00
            };

            // Encrypter to encrypt the uid string
            ShaEncrypter enc512 = new ShaEncrypter(EncMode.SHA512);

            // Retrieving uid
            String uid = send(baReadUID, channel);

            // Setting uid for DB connection
            AppConfig.setCurPassword(uid.substring(0, uid.length() - 4));

            // Encrypt uid string
            uid = enc512.encryptString(uid.substring(0, uid.length() - 4));

            //TODO vor Release entfernen
            System.out.println("UID: " + uid);

            // check the user and return if valid or not
            return uid;
        }
        else
        {
            // Using card exception to tell the user, that the reader was not found
            throw new CardException("Kein Leser vorhanden.");
        }
    }

    
    
    /**
     * Sends commands to a card
     * @param cmd Command as byte array
     * @param channel Card channel
     * @return String representation of the hex output
     * @throws CardException thrown, when a problem with the card occurs
     */
    private String send(byte[] cmd, CardChannel channel) throws CardException
    {
        String res = "";

        byte[] baResp = new byte[258];
        ByteBuffer bufCmd = ByteBuffer.wrap(cmd);
        ByteBuffer bufResp = ByteBuffer.wrap(baResp);

        // output = The length of the received response APDU
        int output = 0;

        // Retrieving output bytes
        output = channel.transmit(bufCmd, bufResp);

        // Transform into a String
        for (int i = 0; i < output; i++)
        {
            res += String.format("%02X", baResp[i]);
        }

        return res;
    }
    
}
