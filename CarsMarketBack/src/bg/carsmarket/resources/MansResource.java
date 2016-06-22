package bg.carsmarket.resources;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import bg.carsmarket.dao.MansDAO;
import bg.carsmarket.model.Manufacturer;

@Path("/mans")
@Produces(MediaType.APPLICATION_JSON)
public class MansResource {

	@GET
	public List<Manufacturer> getMans() {
		
		List<Manufacturer> mans = null;
		try {
			mans = new MansDAO().getAll();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return mans;
	}

	@GET
	@Path("/{id}")
	public Manufacturer getManById(@PathParam("id") Long id) throws JSONException, NamingException {
		return new MansDAO().getById(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response create(
			@FormParam("name") String name,
			@FormParam("imgUrl") String imgUrl,
			@FormParam("lat") Double lat,
			@FormParam("lon") Double lon) throws IOException {
		
		Response res = null;
		try {
			Manufacturer newMan = new Manufacturer(name, imgUrl, lat, lon);			
			new MansDAO().insert(newMan);
		} catch (NamingException | SQLException e) {
			res = Response.serverError().build();
			e.printStackTrace();
		}
		
		return (res != null) ? res : Response.status(Response.Status.CREATED).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response update(
			@PathParam("id") Long id,
			@FormParam("name") String name,
			@FormParam("imgUrl") String imgUrl,
			@FormParam("lat") Double lat,
			@FormParam("lon") Double lon) throws IOException {
		
		Response res = null;
		try {			
			Manufacturer newMan = new Manufacturer(name, imgUrl, lat, lon);
			new MansDAO().update(newMan, id);
		} catch (NamingException | SQLException e) {
			res = Response.serverError().build();
			e.printStackTrace();
		}
		
		return (res != null) ? res : Response.status(Response.Status.OK).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(
			@PathParam("id") Long id) throws IOException {
		
		Response res = null;
		try {
			new MansDAO().delete(id);
		} catch (NamingException | SQLException e) {
			res = Response.serverError().build();
			e.printStackTrace();
		}
		
		return (res != null) ? res : Response.status(Response.Status.OK).build();
	}
}
