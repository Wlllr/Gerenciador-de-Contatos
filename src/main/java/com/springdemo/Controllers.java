package com.springdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springdemo.models.Contact;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;

public class Controllers {
    private static Route addContact(ContactService contactService) {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                //converts object to Json
                ObjectMapper convertsJson = new ObjectMapper();

                String name = request.queryParams("nome");
                String mobile = request.queryParams("mobile");
                String email = request.queryParams("email");

                Contact contact = contactService.insert(name, mobile, email);

                response.status(201); //201 created

                return convertsJson.writeValueAsString(contact);
            }
        };
    }

    public static Route getContactFromId(ContactService contactService) {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                ObjectMapper convertsJson = new ObjectMapper();

                String id = request.params(":id");

                Contact contact = ContactService.getFromId(id);

                if (contact != null) {
                    response.status(200); // 200 OK

                    return convertsJson.writeValueAsString(contact);
                } else {
                    response.status(204); // 204 No content

                    // ALTERACAO A SER FEITA DEPOIS DE COLOCAR O BANCO DE DADOS!!!
                    return convertsJson.writeValueAsString(new ArrayList<>()); // ALTERACAO A SER FEITA DEPOIS DE COLOCAR O BANCO DE DADOS!!!
                    // ALTERACAO A SER FEITA DEPOIS DE COLOCAR O BANCO DE DADOS!!!
                }

            }
        };
    }

    public static Route getAllContacts(ContactService contactService) {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                ObjectMapper convertsJson = new ObjectMapper();

                // Alguma forma de trazer todos do banco de dados e colocar aqui
                // Alguma forma de trazer todos do banco de dados e colocar aqui
                // Alguma forma de trazer todos do banco de dados e colocar aqui
                // Alguma forma de trazer todos do banco de dados e colocar aqui

                ArrayList<Contact> contacts = (ArrayList<Contact>) contactService.getAll();

                if (contacts.isEmpty()) {
                    return convertsJson.writeValueAsString(new ArrayList<>());
                } else {
                    return convertsJson.writeValueAsString(contacts);
                }
            }
        };
    }

    public static Route updateContact(ContactService contactService) {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                ObjectMapper convertsJson = new ObjectMapper();

                String id = request.params(":id");

                Contact contact = contactService.getFromId(id);

                if (contact != null) {
                    String name = request.queryParams("nome");
                    String mobile = request.queryParams("mobile");
                    String email = request.queryParams("email");

                    contactService.update(id, name, mobile, email);

                    response.status(200); // 200 OK
                    return "{\"message\": \"Contact with id" + id + "was sucessfully updated.\"}";
                } else {
                    response.status(404); // 404 not found
                    return convertsJson.writeValueAsString(new ArrayList<>());
                }
            }
        };
    }

    private static Route deleteContact(ContactService contactService) {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                ObjectMapper convertsJson = new ObjectMapper();

                String id = request.params(":id");
                Contact contact = contactService.getFromId(id);

                if (contact != null) {
                    contactService.delete(id);

                    return "{\"message\": \"Contact with id" + id + "was sucessfully deleted.\"}";
                } else {
                    response.status(404); // 404 not found
                    return convertsJson.writeValueAsString(new ArrayList<>());
                }

            }
        };
    }
}
