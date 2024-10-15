package org.example;

import javax.naming.*;
import java.util.Hashtable;

public class ContactJNDIService {

    public static void main(String[] args) {
        // Configuration de l'environnement JNDI
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
        env.put(Context.PROVIDER_URL, "file:///tmp/contact_jndi");

        try {
            // Creation du contexte initial
            Context ctx = new InitialContext(env);

            // Creation d'un objet Contact
            Contact contact = new Contact("C001", "Marie Curie", "marie.curie@example.com", "Recherche");

            // Liaison de l'objet Contact dans le contexte
            String name = "contact/C001";

            // Unbind if the name already exists
            try {
                ctx.unbind(name);
            } catch (NameNotFoundException e) {
                // Ignore if the name does not exist
            }

            ctx.bind(name, contact);
            System.out.println("Contact lu avec succes : " + name);

            // Recherche de l'objet Contact dans le contexte
            Contact retrievedContact = (Contact) ctx.lookup(name);
            System.out.println("Contact récupéré : " + retrievedContact);

            // Liste des contacts dans le contexte
            System.out.println("Liste des contacts dans le contexte :");
            // Change here to list the root context
            for (NamingEnumeration<NameClassPair> enumeration = ctx.list(""); enumeration.hasMore(); ) {
                NameClassPair ncp = enumeration.next();
                // Filter for contacts only (you can modify this logic if needed)
                if (ncp.getName().startsWith("contact/")) {
                    System.out.println(ncp.getName() + " : " + ncp.getClassName());
                }
            }

            // Fermeture du contexte
            ctx.close();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}