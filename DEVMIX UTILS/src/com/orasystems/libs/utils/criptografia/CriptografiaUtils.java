package com.orasystems.libs.utils.criptografia;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;

public class CriptografiaUtils {
	static class AES {
		// Derivador da senha de criptografia
		private static final String SALT = "o6806642kbM7c5";

		/**
		 * Criptografa uma String utilizando o método AES
		 *
		 * @param plainText
		 *            Texto a ser criptografado
		 * @param password
		 *            Senha de criptografia
		 * @return Texto criptografado
		 */
		public static String EncryptStringAES(String plainText, String password) {
			final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS7Padding";
			final String CIPHER_ALGORITHM = "AES";

			try {
				// Codifica a senha
				PasswordDeriveBytes key = new PasswordDeriveBytes(password,
						SALT.getBytes());

				// Parâmetros de criptografia
				byte[] aesKey = key.getBytes(32);
				byte[] aesIV = key.getBytes(16);
				SecretKey secretKey = new SecretKeySpec(aesKey,
						CIPHER_ALGORITHM);
				IvParameterSpec ivParameterSpec = new IvParameterSpec(aesIV);

				// Inicializa a criptografia
				Cipher aesCipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
				aesCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

				// Executa a criptografia
				byte[] cryptoData = aesCipher.doFinal(plainText.getBytes());

				// Retorna a senha codificada
				String senhaCodificada = Base64.encodeToString(cryptoData,
						Base64.DEFAULT);
				if (senhaCodificada.endsWith("\n"))
					return senhaCodificada.substring(0,
							senhaCodificada.length() - 1);
				else
					return senhaCodificada;
			} catch (Exception e) {
				return null;
			}
		}
	}
	static class DES{
		class Des
		{
		    /**
		     * Chave de Criptografia
		     */
		    private final String ChaveDeCriptografia = "$CLAPS#&";

		    /**
		     * Mensagem de erro
		     */
		    public String _mensagemDeErro;

		    /**
		     * Retorna a mensagem de erro armazenada
		     *
		     * @return Mensagem de erro
		     */
		    public String getMensagemDeErro()
		    {
		        return _mensagemDeErro;
		    }

		    /**
		     * Criptografa uma string utilizando DES
		     *
		     * @param texto Texto para ser criptografado
		     * @param resposta Variável Parâmetro String que receberá o texto criptografado em formato hexadecimal
		     * @return true/false indicando se a criptografia foi executada com sucesso
		     */
		    public String Criptografar(String texto)
		    {
		        // O tamanho da string deve ser múltiplo de 8
		        int b = texto.length() % 8;
		        if (b > 0)
		        {
		            b = texto.length() + (8 - b);
		            texto = PadRight(texto, b, ' ');
		        }

		        try
		        {
		            // Cria a classe de criptografia
		            SecretKeySpec chave = new SecretKeySpec(ChaveDeCriptografia.getBytes(), "DES");
		            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding", "BC");
		            cipher.init(Cipher.ENCRYPT_MODE, chave);

		            // Criptografa a string
		            byte[] textoCriptografado = new byte[cipher.getOutputSize(texto.length())];
		            int ctLength = cipher.update(texto.getBytes(), 0, texto.length(), textoCriptografado, 0);
		            ctLength += cipher.doFinal(textoCriptografado, ctLength);

		            return ByteArrayToHex(textoCriptografado);
		        }
		        catch (Exception ex)
		        {
		            _mensagemDeErro = String.format("Erro no procedimento de criptografia: %s", ex.getMessage());
		            return "";
		        }
		    }

		    /**
		     * Decriptografa o texto
		     *
		     * @param texto Texto para decriptografar
		     * @param resposta Variável String que receberá o texto decriptografado
		     * @return true/false indicando se a decriptografia foi executada com sucesso
		     */
		    public String Decriptografar(String texto)
		    {
		        try
		        {
		            // Cria a classe de criptografia
		            SecretKeySpec chave = new SecretKeySpec(ChaveDeCriptografia.getBytes(), "DES");
		            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding", "BC");
		            cipher.init(Cipher.DECRYPT_MODE, chave);

		            // Converte o texto para array de bytes
		            byte[] dados = hexStringToByteArray(texto);

		            // Decriptografa o texto
		            byte[] textoDecriptografado = new byte[cipher.getOutputSize(dados.length)];
		            int ptLength = cipher.update(dados, 0, dados.length, textoDecriptografado, 0);
		            ptLength += cipher.doFinal(textoDecriptografado, ptLength);

		            // Utiliza o encoding ISO-8859-1
		            return new String(textoDecriptografado, "ISO-8859-1");
		        }
		        catch (Exception ex)
		        {
		            _mensagemDeErro = String.format("Erro no procedimento de decriptografia: %s", ex.getMessage());
		            return "";
		        }
		    }

		    /**
		     * Converte uma string hexa para array de bytes
		     *
		     * @param s String com dados hexadecimais
		     * @return Array de bytes
		     */
		    private byte[] hexStringToByteArray(String s)
		    {
		        int len = s.length();
		        byte[] data = new byte[len / 2];
		        for (int i = 0; i < len; i += 2)
		            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
		        return data;
		    }
		    /**
		     * Retorna um texto com o tamanho fixo alinhada à esquerda
		     *
		     * @param texto    Texto para formatar
		     * @param tamanho  Tamanho final do texto
		     * @param caracter Caracter para completar o texto
		     * @return Texto alinhado à esquerda
		     */
		    private String PadRight(String texto, int tamanho, char caracter)
		    {
		        return String.format("%-" + tamanho + "s", texto).replace(' ', caracter);
		    }
		    /**
		     * Converte um array de bytes para uma string hexadecimal
		     *
		     * @param dados Array de bytes
		     * @return String hexadecimal
		     */
		    private String ByteArrayToHex(byte[] dados)
		    {
		        final String HEXES = "0123456789ABCDEF";

		        // Verifica o parâmetro de entrada
		        if (dados == null)
		            return null;

		        // Converte para hexadecimal
		        final StringBuilder hex = new StringBuilder(2 * dados.length);
		        for (final byte b : dados)
		            hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));

		        return hex.toString();
		    }
		}

	}
}
