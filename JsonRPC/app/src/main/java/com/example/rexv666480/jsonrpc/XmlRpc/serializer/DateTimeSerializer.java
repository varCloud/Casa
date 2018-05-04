package com.example.rexv666480.jsonrpc.XmlRpc.serializer;

import java.text.SimpleDateFormat;

import org.w3c.dom.Element;

import com.example.rexv666480.jsonrpc.XmlRpc.XMLRPCException;
import com.example.rexv666480.jsonrpc.XmlRpc.XMLUtil;
import com.example.rexv666480.jsonrpc.XmlRpc.xmlcreator.XmlElement;


/**
 *
 * @author timroes
 */
public class DateTimeSerializer implements Serializer {

	private static final String DATETIME_FORMAT = "yyyyMMdd'T'HHmmss";
	private final SimpleDateFormat DATE_FORMATER = new SimpleDateFormat(DATETIME_FORMAT);

	private final boolean accepts_null_input;

	public DateTimeSerializer(boolean accepts_null_input) {
		this.accepts_null_input = accepts_null_input;
	}


	@Override
	public Object deserialize(Element content) throws XMLRPCException {
		return deserialize(XMLUtil.getOnlyTextContent(content.getChildNodes()));
	}

	public Object deserialize(String dateStr) throws XMLRPCException {
		if (accepts_null_input && (dateStr==null || dateStr.trim().length()==0)) {
			return null;
		}

		try {
			return Iso8601Deserializer.toDate(dateStr);
		} catch (Exception ex) {
			throw new XMLRPCException("Unable to parse given date.", ex);
		}
	}

	@Override
	public XmlElement serialize(Object object) {
		return XMLUtil.makeXmlTag(SerializerHandler.TYPE_DATETIME,
				DATE_FORMATER.format(object));
	}

}
