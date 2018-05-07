package com.example.rexv666480.oddoventas.Odoo.XmlRpc.serializer;


import com.example.rexv666480.oddoventas.Odoo.XmlRpc.XMLRPCException;
import com.example.rexv666480.oddoventas.Odoo.XmlRpc.XMLUtil;
import com.example.rexv666480.oddoventas.Odoo.XmlRpc.xmlcreator.XmlElement;


import org.w3c.dom.Element;

/**
 *
 * @author timroes
 */
public class IntSerializer implements Serializer {

	public Object deserialize(Element content) throws XMLRPCException {
		return Integer.parseInt(XMLUtil.getOnlyTextContent(content.getChildNodes()));
	}

	public XmlElement serialize(Object object) {
		return XMLUtil.makeXmlTag(SerializerHandler.TYPE_INT,
				object.toString());
	}

}
