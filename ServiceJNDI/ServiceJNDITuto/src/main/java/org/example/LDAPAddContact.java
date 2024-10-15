package org.example;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;

public class LDAPAddContact {
    public static void main(String[] args) {
        // Configuration de l’environnement JNDI pour LDAP
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:10389");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
        env.put(Context.SECURITY_CREDENTIALS, "secret");
        try {

            DirContext ctx = new InitialDirContext(env);
            System.out.println("Connexion au serveur LDAP réussie.");

                    String dn = "uid=mcourtois,ou=contacts,dc=business,dc=com";

            Attributes attrs = new BasicAttributes(true); //

            Attribute objClass = new BasicAttribute("objectClass");

            objClass.add("inetOrgPerson");
            attrs.put(objClass);
            attrs.put("cn", "Marc Courtois");
            attrs.put("sn", "Courtois");
            attrs.put("uid", "mcourtois");
            attrs.put("mail", "mcourtois@business.com");
            attrs.put("department", "Marketing");
            // Ajouter l’entr e au r pertoire
            ctx.createSubcontext(dn, attrs);
            System.out.println(" Entr e ajout e avec succ s : " + dn);
            // Fermeture du contexte
            ctx.close();
        } catch (NamingException e) {
            e.printStackTrace();

        }
    }
}
