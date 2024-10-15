package org.example;

import javax.naming.*;
import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;

public class ContactFactory implements ObjectFactory {

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        if (obj instanceof Reference) {
            Reference ref = (Reference) obj;
            if (ref.getClassName().equals(Contact.class.getName())) {
                // Assuming your Contact class has a constructor that matches the arguments
                return new Contact("C001", "Marie Curie", "marie.curie@example.com", "Recherche");
            }
        }
        return null;
    }
}