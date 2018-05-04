package com.example.rexv666480.jsonrpc.XmlRpc.serializer;

import com.example.rexv666480.jsonrpc.XmlRpc.XMLRPCException;
import com.example.rexv666480.jsonrpc.XmlRpc.xmlcreator.XmlElement;

import org.w3c.dom.Element;

/**
 *
 * @author Tim Roes
 */
public class NullSerializer implements Serializer {

	public Object deserialize(Element content) throws XMLRPCException {
		return null;
	}

	public XmlElement serialize(Object object) {
		return new XmlElement(SerializerHandler.TYPE_NULL);
	}

}