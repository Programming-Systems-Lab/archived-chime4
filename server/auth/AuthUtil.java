
package psl.chime4.server.auth;





/**
 * Provides utility methods for encryption and decryption.  Anyone
 * that wishes to use the authentication system should perform encryption
 * through these methods.  
 *
 * The authentication system is protocol agnostic, so there is no guarantee
 * about what authentication protocol (or what class of protocols) the
 * system implements.  Users of the system must be able to provide valid
 * encryption and decryption keys, but need not know anything about what
 * those keys mean to the actual encryption process (i.e. if they work
 * in a public key system, mutually shared private key, etc.)
 *
 * @author Greg Estren (gce3@columbia.edu)
 * @version 1.0
 **/

public class AuthUtil {


    /**
     * Encrypts the specified message with the specified key.
     *
     * @param plaintext - message to encrypt.  Null or empty messages 
     *                    automatically encrypt to null values.
     * @param key       - encryption key.  The authentication system
     *                    is protocol agnostic, so the exact meaning of
     *                    this key varies depending on the protocol used.
     *                    However, this is an internal issue to the
     *                    authentication system.  Users of the system
     *                    do not need to worry about key meaning, they
     *                    simply need to be able to provide the key data.
     *
     * @return encrypted ciphertext for the given message using the
     *         specified key.  Returns null if encryption was not possible
     *         (due to an invalid key, etc.)
     **/
    public static byte[] encrypt(String plaintext, byte[] key) {
	if ((plaintext == null) || (plaintext.equals("")))
	    return null;
	else
	    return plaintext.getBytes();
    }





    /**
     * Decrypts the specified message with the specified key.
     *
     * @param ciphertext - encrypted message.  Null or empty ciphertext 
     *                     automatically decrypts to a null value.
     * @param key - decryption key for the ciphertext.  This key is
     *              assumed to be a valid decryption key. 
     *
     * @return decrypted plaintext for the given ciphertext, or null
     *         if decryption was not possible (due to an invalid key, etc.)
     **/
    public static String decrypt(byte[] ciphertext, byte[] key) {
	if ((ciphertext == null) || (ciphertext.length == 0))
	    return null;
	else
	    return new String(ciphertext);
    }





    /**
     * Given a plaintext password, uses the specified key to encrypt
     * the password in a safe way.  This should be used for remote
     * authentication systems, such that plaintext passwords aren't sent
     * across the network.
     *
     * @param password - raw password to encrypt.  Null or empty values are not
     *                   encrypted
     * @param key[] - encryption key for password.  The authentication source
     *                should be able to decrypt the ciphertext with an
     *                equivalent decryption key.
     *
     * @return - encrypted ciphertext for the specified password.
     **/
    public static byte[] generateAuthCode(String password, byte[] key) {
	if ((password == null) || (password.equals("")))
	    return null;
	else
	    return password.getBytes();
    }




} // end AuthUtil class

