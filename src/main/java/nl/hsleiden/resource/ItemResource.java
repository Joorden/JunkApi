package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.auth.Auth;
import nl.hsleiden.View;
import nl.hsleiden.model.Item;
import nl.hsleiden.model.User;
import nl.hsleiden.service.ItemService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;


@Singleton
@Path("/items")
@Produces(MediaType.APPLICATION_JSON)
public class ItemResource {
    private ItemService itemService;

    @Inject
    public ItemResource(ItemService itemService) {
        this.itemService = itemService;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    public void add(@Auth User user, @Valid Item item) {
        this.itemService.add(item);
    }

    @GET
    public Collection<Item> getItems(){return this.itemService.getAll();}

    @DELETE
    @JsonView(View.Public.class)
    @Path("/{name}")
    @RolesAllowed("Admin")
    public void delete(@Auth User user, @PathParam("name") String name) {
        this.itemService.delete(name);
    }


    @PUT
    @JsonView(View.Public.class)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @RolesAllowed("Admin")
    public void updateItem(@Auth User user, Item item) {
        itemService.update(item);
    }
}
