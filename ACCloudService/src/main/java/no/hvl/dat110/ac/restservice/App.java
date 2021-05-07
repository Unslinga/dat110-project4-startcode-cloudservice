package no.hvl.dat110.ac.restservice;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.put;
import static spark.Spark.post;
import static spark.Spark.delete;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App {
	
	static AccessLog accesslog = null;
	static AccessCode accesscode = null;
	
	public static void main(String[] args) {

		if (args.length > 0) {
			port(Integer.parseInt(args[0]));
		} else {
			port(8080);
		}

		// objects for data stored in the service
		
		accesslog = new AccessLog();
		accesscode  = new AccessCode();
		
		after((req, res) -> {
  		  res.type("application/json");
  		});
		
		// for basic testing purposes
		get("/accessdevice/hello", (req, res) -> {
			
		 	Gson gson = new Gson();
		 	
		 	return gson.toJson("IoT Access Control Device");
		});
		
		// DONE: implement the routes required for the access control service
		// as per the HTTP/REST operations describined in the project description

		// CODE METHODS
		String code = "/accessdevice/code";

		get(code, (request, response) -> new Gson().toJson(accesscode));

		put(code, (request, response) ->
		{
			Gson gson = new Gson();

			accesscode = gson.fromJson(request.body(), AccessCode.class);

			return gson.toJson(accesscode);
		});

		// LOG METHODS
		String log = "/accessdevice/log";

		delete(log, (request, response) ->
		{
			accesslog.clear();
			return accesslog.toJson();
		});

		get(log, (request, response) -> accesslog.toJson());

		get(log + "/:id", (request, response) -> new Gson().toJson(accesslog.get(Integer.parseInt(request.params("id")))));

		post(log, (request, response) ->
		{
			Gson gson = new Gson();

			AccessMessage message = gson.fromJson(request.body(), AccessMessage.class);

			int id = accesslog.add(message.getMessage());

			return gson.toJson(accesslog.get(id));
		});
    }
    
}
