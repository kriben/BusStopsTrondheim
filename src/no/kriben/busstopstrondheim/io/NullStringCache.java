package no.kriben.busstopstrondheim.io;


/**
 * Cache null object: does not do anything..
 * 
 * @author Kristian Bendiksen
 */
public class NullStringCache implements StringCache {

    @Override
    public void set(String data) {    
    }

    @Override
    public String get() {
        return "";
    }

}
