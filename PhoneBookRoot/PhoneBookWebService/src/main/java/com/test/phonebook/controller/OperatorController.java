package com.test.phonebook.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.teste.phonebook.bo.IOperatorBO;
import com.teste.phonebook.dto.OperatorDTO;

@Path("/operators")
public class OperatorController {

	@Inject
	private IOperatorBO oBO;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() {
		try {
			List<OperatorDTO> list = this.oBO.listAll();
			return Response.ok().entity(list).build();
		} catch (Exception e) {
			return Response.serverError().status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response findById(@PathParam("id") Long id) {
		try {
			OperatorDTO o = this.oBO.findById(id);
			return Response.ok().entity(o).build();
		} catch (Exception e) {
			return Response.serverError().status(Status.NOT_FOUND).build();
		}
	}

}
