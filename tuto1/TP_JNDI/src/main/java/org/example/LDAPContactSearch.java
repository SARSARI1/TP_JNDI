import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;

public class LDAPContactSearch {
    public static void main(String[] args){
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.idap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL,"ldap://localhost:10389");
        env.put(Context.SECURITY_AUTHENTICATION,"simple");
        env.put(Context.SECURITY_PRINCIPAL,"uid=admin, ou=system");
        env.put(Context.SECURITY_CREDENTIALS,"secret");

        try{
            DirContext ctx = new InitialDirContext(env);
            System.out.println (" Connexion au serveur LDAP réussie.");
            String searchBase = "ou= contacts ,dc= business ,dc= com ";
            String searchFilter = "( objectClass = inetOrgPerson )";
            String [] attrIDs = {"cn", "sn", " uid ", " mail "," department "};
            SearchControls searchControls = new SearchControls () ;
            searchControls . setSearchScope ( SearchControls . SUBTREE_SCOPE ) ;
            searchControls . setReturningAttributes ( attrIDs ) ;
            NamingEnumeration<SearchResult> results = ctx . search ( searchBase , searchFilter , searchControls );
            while ( results . hasMore () ) {
                SearchResult sr = results . next ();
                System . out . println ("DN: " +  sr . getNameInNamespace () );
                Attributes attrs = sr . getAttributes () ;
                for ( NamingEnumeration <? extends Attribute > ae = attrs . getAll () ; ae . hasMore () ; ) {
                    Attribute attr = ae . next () ;
                    System . out . println ( attr . getID () + ": " +attr . get () );
                }
                System . out . println (" ---------------------------") ;
            }

            ctx . close () ;
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

    }
}