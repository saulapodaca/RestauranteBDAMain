/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.encriptacion;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Clase encargada de desencriptar datos previamente cifrados por {@link Encriptador}.
 * Usa AES en modo CBC con PKCS5Padding.
 */
public class Desencriptador {

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
     * Desencripta una cadena previamente cifrada por {@link Encriptador}.
     * Extrae el IV desde los primeros 16 bytes del mensaje y aplica AES-CBC.
     *
     * @param textoCifrado Texto cifrado en Base64 (IV + Cifrado).
     * @return Texto original desencriptado.
     * @throws Exception Si ocurre un error durante la desencriptación.
     */
    public static String desencriptar(String textoCifrado) throws Exception {
        byte[] ivAndCiphertext = Base64.getDecoder().decode(textoCifrado);

        byte[] iv = new byte[16];
        byte[] ciphertext = new byte[ivAndCiphertext.length - 16];
        System.arraycopy(ivAndCiphertext, 0, iv, 0, 16);
        System.arraycopy(ivAndCiphertext, 16, ciphertext, 0, ciphertext.length);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), new IvParameterSpec(iv));
        byte[] descifrado = cipher.doFinal(ciphertext);

        return new String(descifrado);
    }
}
