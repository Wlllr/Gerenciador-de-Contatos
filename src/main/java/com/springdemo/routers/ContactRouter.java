package com.springdemo.routers;

import spark.Spark;

public class ContactRouter {
    public static void routeProcessing(ContactService contactService) {
        Spark.post("/cadastrar", addContact(contactService));
        Spark.get("/consultar/:id", getContactFromId(contactService));
        Spark.get("/consultar", getAllContacts(contactService));
        Spark.put("/alterar/:id", updateContact(contactService));
        Spark.delete("excluir/:id", deleteContact(contactService));
    }
}
