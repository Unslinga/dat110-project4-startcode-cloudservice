package no.hvl.dat110.ac.restservice;

import com.google.gson.Gson;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AccessLog
{

    // atomic integer used to obtain identifiers for each access entry
    private AtomicInteger cid;
    protected ConcurrentHashMap<Integer, AccessEntry> log;

    public AccessLog()
    {
        this.log = new ConcurrentHashMap<Integer, AccessEntry>();
        cid = new AtomicInteger(0);
    }

    // DONE: add an access entry to the log for the provided message and return assigned id
    public int add(String message)
    {
        int id = cid.getAndIncrement();
        log.put(id, new AccessEntry(id, message));
        return id;
    }

    // DONE: retrieve a specific access entry from the log
    public AccessEntry get(int id)
    {
		return log.getOrDefault(id, null);
    }

    // DONE: clear the access entry log
    public void clear()
    {
        log.clear();
    }

    // DONE: return JSON representation of the access log
    public String toJson()
    {
        return new Gson().toJson(log.values());
    }
}
