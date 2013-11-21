package com.orasystems.libs.utils.webservices;

import java.util.Map;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.content.ContentValues;
import android.util.Log;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.mapper.MapperWrapper;

@SuppressWarnings("deprecation")
public class JAXBUtils {
	public static final String DATA_STRING = "string";
	public static final String DATA_BYTE = "byte";
	
	public static final String cabXmlUTF8 = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
	public static final String cabXmlISO8859_1 = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?>";

	public static SoapSerializationEnvelope ExecutarMetodo(String nomeDoMetodo,
			ContentValues parametros, String dataType, String NAMESPACE,
			String _url) {
		try {
			SoapObject request = new SoapObject(NAMESPACE, nomeDoMetodo);
			if (parametros != null) {
				for (Map.Entry<String, Object> parametro : parametros
						.valueSet()) {
					PropertyInfo pi = new PropertyInfo();
					pi.setName(parametro.getKey());
					if (dataType.equals(DATA_STRING))
						pi.setValue(parametro.getValue().toString());
					if (dataType.equals(DATA_BYTE))
						pi.setValue(parametro.getValue());
					if (dataType.equals(DATA_STRING))
						pi.setType(String.class);
					if (dataType.equals(DATA_BYTE))
						pi.setType(MarshalBase64.BYTE_ARRAY_CLASS);
					request.addProperty(pi);
				}
			}
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			if (dataType.equals(DATA_BYTE)) {
				new MarshalBase64().register(envelope); // serialization
				envelope.encodingStyle = SoapEnvelope.ENC;
			}
			envelope.setOutputSoapObject(request);
			
			HttpTransportSE androidHttpTransport = new HttpTransportSE(_url);

			Log.i("soap:", envelope.bodyOut + "");
			
			androidHttpTransport.call(String.format("\"%s%s\"", NAMESPACE, nomeDoMetodo),envelope);

			return envelope;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static XStream getXStream(boolean ignoreInconsistentElements) {
		if (!ignoreInconsistentElements) {
			return new XStream(new DomDriver("UTF-8",new XmlFriendlyReplacer("__", "_")));
			//return new XStream(new DomDriver("UTF-8"));
		} else {
			//return new XStream(new DomDriver("UTF-8")) {
			return new XStream(new DomDriver("UTF-8",new XmlFriendlyReplacer("__", "_"))) {
				@Override
				protected MapperWrapper wrapMapper(MapperWrapper next) {
					return new MapperWrapper(next) {
						@SuppressWarnings("rawtypes")
						@Override
						public boolean shouldSerializeMember(Class definedIn,
								String fieldName) {
							if (definedIn == Object.class) {
								return false;
							}
							return super.shouldSerializeMember(definedIn,
									fieldName);
						}
					};
				}
			};
		}
	}
}
