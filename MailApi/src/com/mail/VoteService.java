package com.mail;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.model.Vote;


@Path("/sendMail")
public class VoteService {
	
	@SuppressWarnings("static-access")
	@GET
	@Path("/yesdata")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	
	public Response voteYes(@MatrixParam("param") String username,@Context UriInfo uriInfo) throws  URISyntaxException,IOException{
		System.out.println(username);
		VoteDao voteObj = new VoteDao();
		System.out.println("Voted Yes");
		Vote v= new Vote();
		v.setUsername(username);
		v.setVote("Y");
		List<Vote> allData = new ArrayList<Vote>();
		allData.addAll(voteObj.getListData());
		if(allData.contains(v)){
			voteObj.updateData(v);
			ConfirmAlert c = new ConfirmAlert();
			c.sentResponse(v);
		}
		else{
		voteObj.insertVoteToRemote(v);
		ConfirmAlert c = new ConfirmAlert();
		c.sentResponse(v);
		}
		
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		URI uri2 = new URI("http://localhost:8978/MailApi/response.html");
		System.out.println(uri2.toString());
		System.out.println(uri.toString());
		return Response.created(uri2).entity("Yes clicked").build();
		
	}
	@SuppressWarnings("static-access")
	@GET
	@Path("/data")
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	public Response voteNo(@MatrixParam("flag") String flag,@MatrixParam("param") String username,@Context UriInfo uriInfo) throws URISyntaxException, IOException{
		System.out.println(username);
		VoteDao voteObj = new VoteDao();
		System.out.println("Voted No");
		Vote v= new Vote();
		v.setUsername(username);
		System.out.println(flag);
		v.setVote(flag);
		List<Vote> allData = new ArrayList<Vote>();
		allData.addAll(voteObj.getListData());
		if(allData.contains(v)){
			//update 
			voteObj.updateData(v);
			ConfirmAlert c = new ConfirmAlert();
			c.sentResponse(v);
		}
		else{
		voteObj.insertVoteToRemote(v);
		ConfirmAlert c = new ConfirmAlert();
		c.sentResponse(v);
		}
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		URI uri2 = new URI("http://localhost:8978/MailApi/response.html");
		System.out.println(uri2.toString());
		System.out.println(uri.toString());
		if(flag.equalsIgnoreCase("Y")){
			return Response.created(uri2).entity("Thankyou, You have Approved").build();
		}
		else
		return Response.created(uri2).entity("Thankyou, You have Denied").build();
	}
	
}
