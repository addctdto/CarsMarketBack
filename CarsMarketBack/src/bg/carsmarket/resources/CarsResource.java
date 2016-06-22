package bg.carsmarket.resources;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import bg.carsmarket.dao.CarsDAO;
import bg.carsmarket.dao.MansDAO;
import bg.carsmarket.model.Car;
import bg.carsmarket.model.Manufacturer;

@Path("/cars")
@Produces(MediaType.APPLICATION_JSON)
public class CarsResource {

	@GET
	public List<Car> getCars(
			@DefaultValue(Integer.MAX_VALUE+"") @QueryParam("maxPrice") Integer maxPrice,
			@DefaultValue(Integer.MAX_VALUE+"") @QueryParam("maxYear") Integer maxYear) {
		
		List<Car> cars = null;
		
		try {

			if (maxPrice != null) {
				cars = new CarsDAO().getAllCarsFilteredByPrice(maxPrice);
			} else if (maxYear != null) {
				cars = new CarsDAO().getAllCarsFilteredByYear(maxYear);
			} else {
				cars = new CarsDAO().getAll();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cars;
	}

	@GET
	@Path("/{id}")
	public Car getCarById(@PathParam("id") Long id) throws JSONException, NamingException {
		return new CarsDAO().getById(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response create(
			@FormParam("manId") long manId,
			@FormParam("imgUrl") String imgUrl,
			@FormParam("videoUrl") String videoUrl,
			@FormParam("model") String model,
			@FormParam("price") Double price,
			@FormParam("year") Double year, 
			@FormParam("hp") Integer hp) throws IOException {
		
		Response res = null;
		try {
			Manufacturer man = new MansDAO().getById(manId);
			Car newCar = new Car(man, imgUrl, videoUrl, model, price, year, hp);
			
			new CarsDAO().insert(newCar);
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
			@FormParam("manId") long manId,
			@FormParam("imgUrl") String imgUrl,
			@FormParam("videoUrl") String videoUrl,
			@FormParam("model") String model,
			@FormParam("price") Double price,
			@FormParam("year") Double year,
			@FormParam("hp") Integer hp) throws IOException {
		
		Response res = null;
		try {
			Manufacturer man = new MansDAO().getById(manId);
			Car newCar = new Car(man, imgUrl, videoUrl, model, price, year, hp);
			
			new CarsDAO().update(newCar, id);
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
			new CarsDAO().delete(id);
		} catch (NamingException | SQLException e) {
			res = Response.serverError().build();
			e.printStackTrace();
		}
		
		return (res != null) ? res : Response.status(Response.Status.OK).build();
	}
	
}