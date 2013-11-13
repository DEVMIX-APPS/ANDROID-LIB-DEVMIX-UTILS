package com.devmix.libs.utils.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import android.os.Environment;
import android.util.Log;

public class FileManager {
	private static String tag = "FileManager";
	public static String getStringFromInputStream(InputStream is) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	
	/* Checks if external storage is available for read and write */
	public static boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	    	Log.i(tag, "Mídia Montada");
	        return true;
	    }
	    Log.i(tag, "Mídia não montada");
	    return false;
	}
	
	public static boolean writeFile(byte[] bytes,String pathToFile){
		if(isExternalStorageReadable() && isExternalStorageWritable()){
			try {
	            File myFile = new File(pathToFile);
	            myFile.createNewFile();
	            FileOutputStream fOut = new FileOutputStream(myFile);
	            fOut.write(bytes,0,bytes.length);
	            fOut.close();
	            Log.i(tag, "Arquivo gravado");
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            Log.i(tag, "Erro a gravar arquivo");
	            return false;
	        }
		}else{
        	return false;
        }
	}

	/* Checks if external storage is available to at least read */
	public static boolean isExternalStorageReadable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state) ||
	        Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
	        return true;
	    }
	    return false;
	}
	/**
	 * @autor echer
	 * verifica se o diretï¿½rio existe, retorna verdadeiro se existir
	 * @param diretorio caminho para o diretorio
	 * @return retorna verdadeiro se diretorio existe senao retorna falso
	 */
	public static boolean diretorioExiste(String diretorio){
		if(new File(diretorio).exists()){
			Log.i(tag, "Diretório existe");
			return true;
		}else{
			Log.i(tag, "Diretório não existe");
			return false;
		}
	}
	public static boolean delete(String diretorioArquivo){
		if(new File(diretorioArquivo).delete()){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @autor echer
	 * verifica se arquivo existe, retorna verdadeiro se existir
	 * @param diretorio caminho para o arquivo
	 * @return retorna verdadeiro se arquivo existe senao retorna falso
	 */
	public static boolean arquivoExiste(String diretorio){
		if(new File(diretorio).isFile()){
			Log.i(tag, "Arquivo Existe");
			return true;
		}else{
			Log.i(tag, "Arquivo não existe");
			return false;
		}
	}

	@SuppressWarnings("rawtypes")
	public static String dumpClass(Object o) {
	    StringBuffer buffer = new StringBuffer();
	    Class oClass = o.getClass();
	     if (oClass.isArray()) {
	         buffer.append("Array: ");
	        buffer.append("[");
	        for (int i = 0; i < Array.getLength(o); i++) {
	            Object value = Array.get(o, i);
	            if (value.getClass().isPrimitive() ||
	                    value.getClass() == java.lang.Long.class ||
	                    value.getClass() == java.lang.Integer.class ||
	                    value.getClass() == java.lang.Boolean.class ||
	                    value.getClass() == java.lang.String.class ||
	                    value.getClass() == java.lang.Double.class ||
	                    value.getClass() == java.lang.Short.class ||
	                    value.getClass() == java.lang.Byte.class
	                    ) {
	                buffer.append(value);
	                if(i != (Array.getLength(o)-1)) buffer.append(",");
	            } else {
	                buffer.append(dumpClass(value));
	             }
	        }
	        buffer.append("]\n");
	    } else {
	         buffer.append("Class: " + oClass.getName());
	         buffer.append("{\n");
	        while (oClass != null) {
	            Field[] fields = oClass.getDeclaredFields();
	            for (int i = 0; i < fields.length; i++) {
	                fields[i].setAccessible(true);
	                buffer.append(fields[i].getName());
	                buffer.append("=");
	                try {
	                    Object value = fields[i].get(o);
	                    if (value != null) {
	                        if (value.getClass().isPrimitive() ||
	                                value.getClass() == java.lang.Long.class ||
	                                value.getClass() == java.lang.String.class ||
	                                value.getClass() == java.lang.Integer.class ||
	                                value.getClass() == java.lang.Boolean.class ||
	                                    value.getClass() == java.lang.Double.class ||
	                                value.getClass() == java.lang.Short.class ||
	                                value.getClass() == java.lang.Byte.class
	                                ) {
	                            buffer.append(value);
	                        } else {
	                            buffer.append(dumpClass(value));
	                        }
	                    }
	                } catch (IllegalAccessException e) {
	                    buffer.append(e.getMessage());
	                }
	                buffer.append("\n");
	            }
	            oClass = oClass.getSuperclass();
	        }
	        buffer.append("}\n");
	    }
	    return buffer.toString();
	}

	/**
	 * @autor echer
	 * cria diretorio
	 * @param diretorio caminho para o arquivo
	 * @param pasta nome da pasta
	 * @return retorna verdadeiro se diretorio for criado senao retorna false
	 */
	public static boolean criaDiretorio(String diretorio,String pasta){
		if(!diretorioExiste(diretorio+"/"+pasta)){
			if(new File(diretorio+"/"+pasta).mkdir() || new File(diretorio+"/"+pasta).isDirectory()){
				Log.i(tag, "Diretorio criado");
				return true;
			}else{
				Log.i(tag, "Erro ao criar diretorio");
				return false;
			}
		}else{
			return false;
		}
	}

	/**
	 * @autor echer
	 * copia arquivo
	 * @param origem caminho de origem do arquivo
	 * @param destino caminho de destino para criaï¿½ï¿½o
	 * @return retorna verdadeiro se arquivo tiver sido copiado senao retorna false
	 */
	public static boolean copy(String origem,String destino){
		InputStream inStream = null;
		OutputStream outStream = null;
	    	try{
	    	    File org =new File(origem);
	    	    File dst =new File(destino);
	    	    inStream = new FileInputStream(org);
	    	    outStream = new FileOutputStream(dst);
	    	    byte[] buffer = new byte[1024];
	    	    int length;
	    	    while ((length = inStream.read(buffer)) > 0){
	    	    	outStream.write(buffer, 0, length);
	    	    }
	    	    inStream.close();
	    	    outStream.close();
	    	    Log.i(tag, "Copia realizada com sucesso");
	    	    return true;
	    	}catch(IOException e){
	    		Log.i(tag, "Erro ao copiar: "+e.getMessage());
	    		return false;
	    	}
	}
	public static boolean copy(InputStream inStream,String destino){
		OutputStream outStream = null;
	    	try{
	    	    File dst =new File(destino);
	    	    outStream = new FileOutputStream(dst);
	    	    byte[] buffer = new byte[1024];
	    	    int length;
	    	    while ((length = inStream.read(buffer)) > 0){
	    	    	outStream.write(buffer, 0, length);
	    	    }
	    	    inStream.close();
	    	    outStream.close();
	    	    Log.i(tag, "Copia realizada com sucesso");
	    	    return true;
	    	}catch(IOException e){
	    		Log.i(tag, "Erro ao copiar: "+e.getMessage());
	    		return false;
	    	}
	}
	/**
	 * @autor echer
     * Lï¿½ os dados do arquivo texto
     * @param nomeDoArquivo Caminho para o arquivo texto
     * @return Conteï¿½do do arquivo
     */
    public static String LerArquivoTexto(String nomeDoArquivo)
    {
        // Verifica se o arquivo existe
        File arquivo = new File(nomeDoArquivo);
        if (!arquivo.exists()){
        	Log.i(tag, "Arquivo não existe");
            return null;
        }

        // Lï¿½ os dados do arquivo
        try{
            BufferedReader in = new BufferedReader(new FileReader(arquivo));
            StringBuilder sb = new StringBuilder();
            String linha;
            while ((linha = in.readLine()) != null)
                sb.append(linha);
            in.close();
            Log.i(tag, "Texto do arquivo: "+sb.toString());
            return sb.toString();
        }
        catch (IOException ignored){
        	Log.i(tag, "Exeption: "+ignored.getMessage());
            return null;
        }
    }

}
