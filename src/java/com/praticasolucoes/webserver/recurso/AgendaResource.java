/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praticasolucoes.webserver.recurso;

import com.praticasolucoes.webserver.modelo.Contato;
import com.praticasolucoes.webserver.modelo.Operadora;
import com.sun.jersey.json.impl.provider.entity.JSONArrayProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * REST Web Service
 *
 * @author Edney Chantal
 */
@Path("contato")
public class AgendaResource {

    @Context
    private UriInfo context;
    static Map<Integer,Contato> l = null;
    static Map<String,Operadora> mOperadora = null;
    static int sequencia = 0 ;

    /**
     * Creates a new instance of AgendaResource
     */
    public AgendaResource() {
    }
    
    static {
        mOperadora = new HashMap<String,Operadora>();
        mOperadora.put("claro", new Operadora(1, "claro", "celuar"));
        mOperadora.put("oi", new Operadora(2, "oi", "celular"));
        mOperadora.put("tim", new Operadora(3, "tim", "celular"));
        mOperadora.put("fax", new Operadora(4, "fax", "fixo"));
        mOperadora.put("trabalho", new Operadora(5, "trabalho", "fixo"));
        
        
        
        l = new HashMap<Integer,Contato>();
        l.put(0,new Contato(0, "Joao da Silva", "82988066900",mOperadora.get("oi") ));
        l.put(1,new Contato(1, "Mara Barbosa 1", "828888066900",mOperadora.get("tim")));
        l.put(2,new Contato(2, "Mara Barbosa 2", "828888066900",mOperadora.get("oi")));
        l.put(3,new Contato(3, "Mara Barbosa 3", "828888066900",mOperadora.get("fax")));
        
        sequencia = 4;
        
    }
    
    @Path("{id}")
    @DELETE
    @Produces("text/plain")
    public String deleta(@PathParam("id") int id ) throws ParseException {
        
        l.remove(id);
        return "OK";
        
         /*JSONArray jsonO ; 
        JSONParser jsonP = new JSONParser();
        jsonO = (JSONArray) jsonP.parse(content);
        
        Iterator it = jsonO.iterator(); 
        while (it.hasNext() ) {
            JSONObject jo = (JSONObject) it.next();
            l.remove(((Long)jo.get("codigo")).intValue());
        }*/
    }
    
    
    /**
     * Retrieves representation of an instance of com.praticasolucoes.webserver.recurso.AgendaResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public   List<Contato>  getXml() {
        //TODO return proper representation object
        return new ArrayList(l.values());
    }

    /**
     * PUT method for updating or creating an instance of AgendaResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putXml(String content) throws ParseException {
        JSONObject jsonO ; 
        JSONObject json1 ; 
        JSONParser jsonP = new JSONParser();
        jsonO = (JSONObject) jsonP.parse(content);
        json1 = (JSONObject) jsonO.get("operadora");
        Contato contato = new Contato();
        contato.setNome((String) jsonO.get("nome"));
        contato.setCodigo(sequencia);
        contato.setTelefone((String) jsonO.get("telefone"));
        contato.setOperadora(new Operadora(((Long) json1.get("codigo")).intValue(),(String) json1.get("nome"),(String) json1.get("categoria")));
        l.put(contato.getCodigo(),contato);
        sequencia ++;
        
    }
    
    @PUT
    @Consumes("application/json")
    @Path("{id}")
    public void alteraContato(@PathParam("id") int codigo , String content) throws ParseException {
        JSONObject jsonO ; 
        JSONObject json1 ; 
        JSONParser jsonP = new JSONParser();
        jsonO = (JSONObject) jsonP.parse(content);
        json1 = (JSONObject) jsonO.get("operadora");
        Contato contato = new Contato();
        contato.setNome((String) jsonO.get("nome"));
        contato.setCodigo(sequencia);
        contato.setTelefone((String) jsonO.get("telefone"));
        contato.setOperadora(new Operadora(((Long) json1.get("codigo")).intValue(),(String) json1.get("nome"),(String) json1.get("categoria")));
        l.put(codigo, contato);
    }
        
    @GET
    @Produces("application/json")
    @Path("/operadoras")
    public List<Operadora> getOperadoras() {
        return new ArrayList(mOperadora.values());
    }
    
   
    
}
