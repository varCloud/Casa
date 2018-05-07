package com.example.rexv666480.oddoventas.Odoo.XmlRpc.serializer;



import com.example.rexv666480.oddoventas.Odoo.XmlRpc.XMLRPCException;
import com.example.rexv666480.oddoventas.Odoo.XmlRpc.XMLUtil;
import com.example.rexv666480.oddoventas.Odoo.XmlRpc.xmlcreator.XmlElement;
import com.example.rexv666480.oddoventas.Odoo.base64.Base64;

import org.w3c.dom.Element;

/**
 *
 * @author Tim Roes
 */
public class Base64Serializer implements Serializer {

	public Object deserialize(Element content) throws XMLRPCException {
		return Base64.decode(XMLUtil.getOnlyTextContent(content.getChildNodes()));
	}

	public XmlElement serialize(Object object) {
		return XMLUtil.makeXmlTag(SerializerHandler.TYPE_BASE64,
				Base64.encode((Byte[])object));
	}

}