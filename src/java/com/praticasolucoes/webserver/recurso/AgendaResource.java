/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praticasolucoes.webserver.recurso;

import com.praticasolucoes.webserver.modelo.Agenda;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author Edney Chantal
 */
@Path("agenda")
public class AgendaResource {

    @Context
    private UriInfo context;
    List<Agenda> l = null;

    /**
     * Creates a new instance of AgendaResource
     */
    public AgendaResource() {
    }

    /**
     * Retrieves representation of an instance of com.praticasolucoes.webserver.recurso.AgendaResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public   List<Agenda>  getXml() {
        //TODO return proper representation object
        l = new ArrayList<Agenda>();
        l.add(new Agenda(0, "Joao da Silva", "82988066900"));
        l.add(new Agenda(1, "Mara Barbosa 1", "828888066900"));
        l.add(new Agenda(1, "Mara Barbosa 2", "828888066900"));
        l.add(new Agenda(1, "Mara Barbosa 3", "828888066900"));
       
        
        
        return l;
    }

    /**
     * PUT method for updating or creating an instance of AgendaResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putXml(String content) {
        System.out.println(content);
        
    }
}
