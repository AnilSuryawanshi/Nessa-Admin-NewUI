package com.connecticus.admin.controller;

import java.util.Hashtable;
import java.util.Properties;
import java.util.jar.Attributes;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class DemoLDap {

    public static void main(String[] args) {  

         Hashtable<String, String> env = new Hashtable<String,String>();
         env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
         env.put(Context.PROVIDER_URL, "LDAP://localhost:10389");
         env.put(Context.SECURITY_AUTHENTICATION, "simple");
         env.put(Context.SECURITY_PRINCIPAL,"uid=admin,ou=system"); // specify the username
         env.put(Context.SECURITY_CREDENTIALS,"secret");// specify the password
        // TODO code application logic here  
System.out.println("connection ok for testing11 ");
System.out.println("connection ok for testing11 demo ");
                  // entry's DN 
  // String entryDN = "uid=sachin,ou=users,ou=system";  
    String entryDN = "uid=amol,uid=admin,ou=system";

    // entry's attributes  

    Attribute cn = new BasicAttribute("cn", "amol patil");  
    Attribute sn = new BasicAttribute("sn", "amolp");  
    Attribute mail = new BasicAttribute("mail", "vaibhavachalpur@gmail.com");  
    Attribute phone = new BasicAttribute("telephoneNumber", "8484081510"); 
    Attribute UserPassword = new BasicAttribute("userPassword","amol");
        Attribute oc = new BasicAttribute("objectClass");  
    oc.add("top");  
    oc.add("person");  
    oc.add("organizationalPerson");  
    oc.add("inetOrgPerson");  
    DirContext ctx = null;  
    System.out.println("connection ok for testing11 ");
    try {  
        // get a handle to an Initial DirContext  
        ctx = new InitialDirContext(env);  

        // build the entry  
        BasicAttributes entry = new BasicAttributes();  
        entry.put(cn);  
        entry.put(sn);  
        entry.put(mail);  
        entry.put(phone);  
        entry.put(UserPassword);
        entry.put(oc);  

        // Add the entry  

        ctx.createSubcontext(entryDN, entry);  
         System.out.println( "AddUser: added entry " + entryDN + ".");  

    } catch (NamingException e) {  
        System.err.println("AddUser: error adding entry." + e);  
        System.out.println("connection fail  ");
    }  
 }  
}  


