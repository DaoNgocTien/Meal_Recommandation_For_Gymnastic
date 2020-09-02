/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tien.jaxb;

import java.io.File;
import java.io.Serializable;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;
import tien.jaxb.obj.Materials;
//import tien.jaxb.obj.Attributes;
//import tien.jaxb.obj.Materials;

/**
 *
 * @author Admin
 */
public class JAXBUnmarshalling implements Serializable {

    public static Materials JAXBUnmarshalling(File validatedXML, File schema) throws JAXBException, SAXException {
        JAXBContext context = JAXBContext.newInstance(Materials.class);

        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema sche = sf.newSchema(schema);

        Unmarshaller um = context.createUnmarshaller();

        um.setSchema(sche);
        um.setEventHandler((ValidationEvent event) -> {
            System.out.println("\nEVENT");
            System.out.println("SEVERITY:  " + event.getSeverity());
            System.out.println("MESSAGE:  " + event.getMessage());
            System.out.println("LINKED EXCEPTION:  " + event.getLinkedException());
            System.out.println("LOCATOR");
            System.out.println("    LINE NUMBER:  " + event.getLocator().getLineNumber());
            System.out.println("    COLUMN NUMBER:  " + event.getLocator().getColumnNumber());
            System.out.println("    OFFSET:  " + event.getLocator().getOffset());
            System.out.println("    OBJECT:  " + event.getLocator().getObject());
            System.out.println("    NODE:  " + event.getLocator().getNode());
            System.out.println("    URL:  " + event.getLocator().getURL());
            return true;
        });
        Materials materials = (Materials) um.unmarshal(validatedXML);

        System.out.println("JAXBUnmarshalling successfully");
        return materials;
    }
}
