package jaxrs;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import entities.Adherent;
import jaxb.AdherentList;
import repositories.AdherentRepository;

/**
 * Root resource (exposed at "myresource" path)
 */
@Component
@Path("/adherent")
public class MyResource {
	@Autowired
	AdherentRepository adherentRepository;

    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Adherent createAdherent( Adherent adherent) {
        return adherentRepository.save(adherent);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Adherent> getAdherentAll() {
        return adherentRepository.findAll();
    }
    
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdherent(@PathParam("id") Integer id) {
    	return Response.status(200)
    			.entity(adherentRepository.findOne(id)).build();
    }
    
    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Adherent> getAdherentByPrenom(@Context UriInfo info) {
    	return adherentRepository.findByPrenom(info.getQueryParameters().getFirst("prenom"));
    }
}
