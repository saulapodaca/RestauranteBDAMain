/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.encriptacion;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Clase encargada de encriptar datos usando AES en modo CBC con PKCS5Padding.
 * Utiliza una clave secreta fija interna y un IV aleatorio por operación.
 */
public class Encriptador {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String SECRET_KEY = "C4v3D3L4L1br3r14"; // Clave de 16 bytes

    /**
     * Obtiene el objeto SecretKeySpec basado en la clave secreta interna.
     *
     * @return Clave AES como SecretKeySpec.
     */
    private static SecretKeySpec getSecretKey() {
        return new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
    }

    /**
     * Encripta una cadena de texto usando AES-CBC con un IV aleatorio.
     * El IV es prepended al texto cifrado y todo es devuelto en Base64.
     *
     * @param textoPlano Texto a encriptar.
     * @return Texto cifrado en Base64 (IV + Cifrado).
     * @throws Exception Si ocurre un error durante la encriptación.
     */
    public static String encriptar(String textoPlano) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(), ivSpec);
        byte[] cifrado = cipher.doFinal(textoPlano.getBytes());

        byte[] ivAndCiphertext = new byte[iv.length + cifrado.length];
        System.arraycopy(iv, 0, ivAndCiphertext, 0, iv.length);
        System.arraycopy(cifrado, 0, ivAndCiphertext, iv.length, cifrado.length);

        return Base64.getEncoder().encodeToString(ivAndCiphertext);
    }
}
