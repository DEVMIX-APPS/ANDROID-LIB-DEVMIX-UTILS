package com.devmix.libs.utils.criptografia;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;

public class CriptografiaUtils {
	// Derivador da senha de criptografia
    private static final String SALT = "o6806642kbM7c5";
	/**
     * Criptografa uma String utilizando o método AES
     *
     * @param plainText Texto a ser criptografado
     * @param password  Senha de criptografia
     * @return Texto criptografado
     */
    public static String EncryptStringAES(String plainText, String password)
    {
        final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS7Padding";
        final String CIPHER_ALGORITHM = "AES";

        try
        {
            // Codifica a senha
            PasswordDeriveBytes key = new PasswordDeriveBytes(password, SALT.getBytes());

            // Parâmetros de criptografia
            byte[] aesKey = key.getBytes(32);
            byte[] aesIV = key.getBytes(16);
            SecretKey secretKey = new SecretKeySpec(aesKey, CIPHER_ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(aesIV);

            // Inicializa a criptografia
            Cipher aesCipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
            aesCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

            // Executa a criptografia
            byte[] cryptoData = aesCipher.doFinal(plainText.getBytes());

            // Retorna a senha codificada
            String senhaCodificada = Base64.encodeToString(cryptoData, Base64.DEFAULT);
            if (senhaCodificada.endsWith("\n"))
                return senhaCodificada.substring(0, senhaCodificada.length() - 1);
            else
                return senhaCodificada;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
