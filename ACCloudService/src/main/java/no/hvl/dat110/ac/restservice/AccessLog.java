package no.hvl.dat110.ac.restservice;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gson.Gson;

public class AccessLog {
	
	// atomic integer used to obtain identifiers for each access entry
	private AtomicInteger cid;
	protected ConcurrentHashMap<Integer, AccessEntry> log;
	
	public AccessLog () {
		this.log = new ConcurrentHashMap<Integer,AccessEntry>();
		cid = new AtomicInteger(0);
	}

	// DONE?: add an access entry to the log for the provided message and return assigned id
	public int add(String message) {
		int id = cid.intValue();
		log.put(id,new AccessEntry(id,message));
		cid.set(id);
		cid.addAndGet(1);
		return id;
	}
		
	// DONE: retrieve a specific access entry from the log
	public AccessEntry get(int id) {

		if (log.contains(id)){
			return log.get(id);
		} else {
			return null;
		}
	}
	
	// DONE: clear the access entry log
	public void clear() {
		log.clear();
	}
	
	// DONE: return JSON representation of the access log
	public String toJson () {
    	
		String json = this.toJson();
    	
    	return json;
    }
}
