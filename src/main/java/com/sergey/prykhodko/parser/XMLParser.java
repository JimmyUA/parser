package com.sergey.prykhodko.parser;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;


public interface XMLParser<T> {

    default void parse(XMLStreamReader reader) throws XMLStreamException {
        while (reader.hasNext()){
            int event = reader.next();
            if(event == XMLStreamConstants.START_ELEMENT){
                parseStartElement(reader.getLocalName());
            } else if (event == XMLStreamConstants.CHARACTERS){
                parseText(reader.getText());
            } else if (event == XMLStreamConstants.END_ELEMENT){
                parseEndElement(reader.getLocalName());
            }
        }
    }

    void parseStartElement(String nodeName);

    void parseEndElement(String nodeName);

    void parseText(String text);

    T getParsedObject();
}
