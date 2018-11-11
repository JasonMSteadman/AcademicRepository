package WebSockets;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.websocket.server.ServerEndpoint;
import Speed.Board;
import javax.websocket.OnOpen;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.Session;


@ServerEndpoint("/Server")
public class Server{
	
	static Set<Session> users = Collections.synchronizedSet(new HashSet<Session>());
	static Board game;
	static String playerOne = null; 
	static String playerTwo = null;
	
	@OnOpen
	public void handleOpen(Session session) 
	{
		String sRole;
		boolean bStartGame = false;

		//	Set player one and two if none exist
		if(playerOne == null)
		{
			playerOne = session.getId();
			sRole = "Player One";
			
			//	If player two is already in the game
			if(playerTwo != null)
				bStartGame = true;			
		}
		else if (playerTwo == null)
		{
			playerTwo = session.getId();
			sRole = "Player Two";
			bStartGame = true;
		}
		else
		{
			sRole = "Spectator";
		}
		
		try
		{
			session.getBasicRemote().sendText(buildJsonData("role", sRole));
		}
		catch(Exception e)
		{
			//	TODO	Make more specific 	
		}
		
		users.add(session);
		if(bStartGame)
		{
			game = new Board();
			game.setPlayer1(playerOne);
			game.setPlayer2(playerTwo);
			try
			{
				refreshBoard();
				bStartGame = false;
			}
			catch (Exception e)
			{
				//	TODO 	Make more specific.
			}
		}
	}
	
	@OnMessage
	public void handleMessage(String message, Session session) throws IOException
	{
		//	TODO  figure out how to create JSON object in JavaScript		
		if(playerOne == session.getId() || playerTwo == session.getId() )
		{
			//	Make player move
			game.playerMove(session.getId(), message);
			
			//	Update board state
			refreshBoard();
		}
		
	}
	
	@OnClose
	public void handleClose(Session session)
	{
		//	See if player one is leaving
		if(playerOne == session.getId())
		{
			playerOne = null;
			game.setPlayer1(null);
		}
		//	See if player two is leaving
		else if(playerTwo == session.getId())
		{
			playerTwo = null;
			game.setPlayer2(null);
		}
		
		//	End game if one of the players leave
		if(playerOne == null || playerTwo == null)
		{
			game.resetBoard();
		}
		//	Refresh board state
		try
		{
			refreshBoard();
		}
		catch (Exception e)
		{
			//	TODO 	Make more specific.
		}		
		users.remove(session);
	}
	
	public String buildJsonData(String attribute, String message)
	{
		
		JsonObject jsonObject = Json.createObjectBuilder().add(attribute,  message).build();
		StringWriter stringWriter = new StringWriter();
		try (JsonWriter jsonWriter = Json.createWriter(stringWriter)) {jsonWriter.write(jsonObject);}
		
		return stringWriter.toString();
	}
	
	private void refreshBoard() throws IOException
	{
		Iterator<Session> iter = users.iterator();
		while(iter.hasNext()) 
		{
			Session currentSession = iter.next();
			JsonObject jsonObject = game.getBoardImg(currentSession.getId());
			StringWriter stringWriter = new StringWriter();
			//	Make player move and create new board state
			try (JsonWriter jsonWriter = Json.createWriter(stringWriter)) 
			{jsonWriter.write(jsonObject);}
			
			//	Send new board state
			currentSession.getBasicRemote().sendText(stringWriter.toString());	
		}
	}
	
}