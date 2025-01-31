//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.26 at 09:34:18 AM BST 
//


package uk.nhs.careconnect.itksrp;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the uk.nhs.riding.west.fhir.vocabularies package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _VocabularyDescriptionPBr_QNAME = new QName("", "br");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: uk.nhs.riding.west.fhir.vocabularies
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Vocabulary }
     * 
     */
    public Vocabulary createVocabulary() {
        return new Vocabulary();
    }

    /**
     * Create an instance of {@link Vocabulary.Concept }
     * 
     */
    public Vocabulary.Concept createVocabularyConcept() {
        return new Vocabulary.Concept();
    }

    /**
     * Create an instance of {@link Vocabulary.Description }
     * 
     */
    public Vocabulary.Description createVocabularyDescription() {
        return new Vocabulary.Description();
    }

    /**
     * Create an instance of {@link Vocabulary.Concept.DisplayName }
     * 
     */
    public Vocabulary.Concept.DisplayName createVocabularyConceptDisplayName() {
        return new Vocabulary.Concept.DisplayName();
    }

    /**
     * Create an instance of {@link Vocabulary.Description.P }
     * 
     */
    public Vocabulary.Description.P createVocabularyDescriptionP() {
        return new Vocabulary.Description.P();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "br", scope = Vocabulary.Description.P.class)
    public JAXBElement<String> createVocabularyDescriptionPBr(String value) {
        return new JAXBElement<String>(_VocabularyDescriptionPBr_QNAME, String.class, Vocabulary.Description.P.class, value);
    }

}
