package org.example;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

public class LDAPDeleteContact {
    public static void main(String[] args) {

        // Configuration de l’environnement JNDI pour LDAP
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:10389");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");

        env.put(Context.SECURITY_CREDENTIALS, "secret");

        try {
            // Cr ation du contexte initial
            DirContext ctx = new InitialDirContext(env);
            System.out.println("Connexion au serveur LDAP reussie.");
            // DN de l’entree supprimer
            String dn = "uid=mcourtois,ou=contacts,dc=business,dc=com";
            // Suppression de l’entr e
            ctx.destroySubcontext(dn);
            System.out.println(" Entr e supprim e avec succ s :" + dn);
            // Fermeture du contexte
            ctx.close();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
