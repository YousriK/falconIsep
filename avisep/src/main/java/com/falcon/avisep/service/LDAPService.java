package com.falcon.avisep.service;


import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.falcon.avisep.configuration.LDAPServiceException;
import com.falcon.avisep.model.LDAPUserDTO;
import com.falcon.avisep.model.Role;
import com.falcon.avisep.model.UserAvis;
import com.falcon.avisep.repository.UserAvisRepository;

@Service
public class LDAPService {

	@Autowired 
	UserAvisRepository userRepository;

    public LDAPUserDTO getUser(final String user, final String pwd) throws LDAPServiceException {
        // Initial context implementation
        final String INITCTX = "com.sun.jndi.ldap.LdapCtxFactory";
        final String TIMEOUT = "com.sun.jndi.ldap.connect.timeout";
        final String MY_HOST = "ldap://ldap.isep.fr:636";
        final String MGR_DN = "uid=" + user + ", " + "ou=People, dc=isep.fr";
        final String MY_SEARCHBASE = "dc=isep.fr";
        final String MY_FILTER = "(uid=" + user + ")";

        LDAPUserDTO ldapUser = new LDAPUserDTO();

        String messageErreur = "LOGIN INVALIDE";

        try {

            // Hashtable for environmental information
            Hashtable<String, String> env = new Hashtable<String, String>();

            // Specify which class to use for our JNDI provider
            env.put(Context.INITIAL_CONTEXT_FACTORY, INITCTX);
            // Specify SSL
            env.put(Context.SECURITY_PROTOCOL, "ssl");
            // Specify host and port to use for directory service
            env.put(Context.PROVIDER_URL, MY_HOST);
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, MGR_DN);
            env.put(Context.SECURITY_CREDENTIALS, pwd);
            env.put(TIMEOUT, "3000");

            // Get a reference to a directory context
            DirContext ctx = new InitialDirContext(env);

            // Specify the scope of the search
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);

            // Perform the actual search
            // We give it a searchbase, a filter and a the constraints
            // containing the scope of the search
            NamingEnumeration<SearchResult> results = ctx.search(MY_SEARCHBASE, MY_FILTER, constraints);

            // Now step through the search results
            while (results != null && results.hasMore()) {

                SearchResult sr = results.next();

                Attribute cn = sr.getAttributes().get("cn");
                ldapUser.setNom((String) cn.get());

                Attribute uid = sr.getAttributes().get("uid");
                ldapUser.setLogin((String) uid.get());

                Attribute et = sr.getAttributes().get("employeeType");
                ldapUser.setEmployeeType((String) et.get());

                Attribute sn = sr.getAttributes().get("sn");
                ldapUser.setNomFamille((String) sn.get());

                Attribute givenName = sr.getAttributes().get("givenname");
                ldapUser.setPrenom((String) givenName.get());

                try {
                    Attribute en = sr.getAttributes().get("employeeNumber");
                    ldapUser.setEmployeeNumber((String) en.get());
                } catch (Exception e) {
                    messageErreur = "Numéro d'élève non trouvé dans l'annuaire";
                }

                Attribute em = sr.getAttributes().get("mail");
                ldapUser.setMail((String) em.get());

                ctx.close();

            }

        } catch (Exception e) {
            System.err.println(e);
            throw (new LDAPServiceException(messageErreur));
        }

        return ldapUser;
    }

    public UserAvis createUserFromLDAP(LDAPUserDTO userDTO) {
        UserAvis user = null;
        
        Role role = userRepository.findByRole(Role.STUDENT);
        user.setRole(role);

        user.setEmail(userDTO.getMail());
        user.setFirstName(userDTO.getPrenom());
        user.setLastName(userDTO.getNomFamille());
        user.setLdapId(userDTO.getEmployeeNumber());

        return user;
    }

}
