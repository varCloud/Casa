package com.example.rexv666480.jsonrpc.XmlRpc.serializer;

import com.example.rexv666480.jsonrpc.XmlRpc.XMLRPCException;
import com.example.rexv666480.jsonrpc.XmlRpc.XMLUtil;
import com.example.rexv666480.jsonrpc.XmlRpc.xmlcreator.XmlElement;
import org.w3c.dom.Element;

/**
 *
 * @author Tim Roes
 */
public class BooleanSerializer implements Serializer {

	public Object deserialize(Element content) throws XMLRPCException {
		return XMLUtil.getOnlyTextContent(content.getChildNodes()).equals("1")
				? Boolean.TRUE : Boolean.FALSE;
	}

	public XmlElement serialize(Object object) {
		return XMLUtil.makeXmlTag(SerializerHandler.TYPE_BOOLEAN,
				(Boolean)object ? "1" : "0");
	}

}