package com.test.phonebook.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.teste.phonebook.bo.IContactBO;
import com.teste.phonebook.dto.ContactDTO;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/contacts")
public class ContactController {

	@Inject
	private IContactBO cBO;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() {
		try {
			List<ContactDTO> list = this.cBO.listAll();
			return Response.ok().entity(list).build();
		} catch (Exception e) {
			return Response.serverError().status(Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response findById(@PathParam("id") Long id) {
		try {
			ContactDTO c = this.cBO.findById(id);
			return Response.ok().entity(c).build();
		} catch (Exception e) {
			return Response.serverError().status(Status.BAD_REQUEST).build();
		}
	}

}
