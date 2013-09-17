package com.devmix.libs.utils.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Format {
	/**
	 * valida a data e hora
	 * @param dataHora deve estar no formato yyyy-mm-dd hh:mm
	 * @return retorna data yyyy-mm-dd se estiver no formato indicado
	 */
	public static String getData(String dataHora){
		try{
			String re1="((?:(?:[1]{1}\\d{1}\\d{1}\\d{1})|(?:[2]{1}\\d{3}))[-:\\/.](?:[0]?[1-9]|[1][012])[-:\\/.](?:(?:[0-2]?\\d{1})|(?:[3][01]{1})))(?![\\d])";	// YYYYMMDD 1
		    String re2="(\\s+)";	// White Space 1
		    String re3="((?:(?:[0-1][0-9])|(?:[2][0-3])|(?:[0-9])):(?:[0-5][0-9])(?::[0-5][0-9])?(?:\\s?(?:am|AM|pm|PM))?)";	// HourMinuteSec 1

		    Pattern p = Pattern.compile(re1+re2+re3,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		    Matcher m = p.matcher(dataHora);
		    if (m.find())
		    {
		        //String yyyymmdd1=m.group(1);
		        //String ws1=m.group(2);
		        //String time1=m.group(3);
		        //System.out.print("("+yyyymmdd1.toString()+")"+"("+ws1.toString()+")"+"("+time1.toString()+")"+"\n");
		        //return yyyymmdd1;
                return m.group(1);
		    }else{
		    	return null;
		    }
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * valida a data e hora
	 * @param dataHora deve estar no formato yyyy-mm-dd hh:mm
	 * @return retorna hora hh:mm se estiver no formato indicado
	 */
	public static String getHora(String dataHora){
		try{
			String re1="((?:(?:[1]{1}\\d{1}\\d{1}\\d{1})|(?:[2]{1}\\d{3}))[-:\\/.](?:[0]?[1-9]|[1][012])[-:\\/.](?:(?:[0-2]?\\d{1})|(?:[3][01]{1})))(?![\\d])";	// YYYYMMDD 1
		    String re2="(\\s+)";	// White Space 1
		    String re3="((?:(?:[0-1][0-9])|(?:[2][0-3])|(?:[0-9])):(?:[0-5][0-9])(?::[0-5][0-9])?(?:\\s?(?:am|AM|pm|PM))?)";	// HourMinuteSec 1

		    Pattern p = Pattern.compile(re1+re2+re3,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		    Matcher m = p.matcher(dataHora);
		    if (m.find())
		    {
		        //String yyyymmdd1=m.group(1);
		        //String ws1=m.group(2);
		        //String time1=m.group(3);
		        //System.out.print("("+yyyymmdd1.toString()+")"+"("+ws1.toString()+")"+"("+time1.toString()+")"+"\n");
		        //return time1;
                return m.group(3);
		    }else{
		    	return null;
		    }
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * formata telefone
	 * @param telefone
	 * @return telefone formatado
	 */
	public static String formataTelefone(String telefone) {
		if (telefone != null) {
			if (telefone.length() == 10 || telefone.length() == 11) {
				return String.format("(%s)%s-%s", telefone.substring(0, 2),
						telefone.substring(2, 6),
						telefone.substring(6, telefone.length()));
			} else {
				return telefone;
			}
		} else {
			return "";
		}
	}
	public static String formataCep(String cep){
		if(cep != null){
			if(cep.length() == 8){
				return String.format("%s-%s", cep.substring(0, 5), cep.substring(5, cep.length()));
			}else{
				return cep;
			}
		}else{
			return "";
		}
	}

	/**
	 * formata inscricao estadual
	 * @param ie
	 * @return inscricao estadual formatada
	 */
	public static String formataIE(String ie) {
		if (ie != null) {
			if (ie.length() == 12) {
				return String.format("%s.%s.%s.%s", ie.substring(0, 3),
						ie.substring(3, 6), ie.substring(6, 9),
						ie.substring(9, ie.length()));
			} else {
				return ie;
			}
		} else {
			return "";
		}
	}
	public static String formataCPF(String cpf) {
		if (cpf != null) {
			if (cpf.length() == 11) {
				return String.format("%s.%s.%s-%s", cpf.substring(0, 3),
						cpf.substring(3, 6), cpf.substring(6, 9),
						cpf.substring(9, cpf.length()));
			} else {
				return cpf;
			}
		} else {
			return "";
		}
	}
	/**
	 * formata o cnpj
	 * @param cnpj
	 * @return cnpj formatado
	 */
	public static String formataCnpj(String cnpj) {
		if (cnpj != null) {
			if (cnpj.length() == 14) {
				return String.format("%s.%s.%s/%s-%s", cnpj.substring(0, 2),
						cnpj.substring(2, 5), cnpj.substring(5, 8),
						cnpj.substring(8, 12),
						cnpj.substring(12, cnpj.length()));
			} else {
				return cnpj;
			}
		} else {
			return "";
		}
	}
}
