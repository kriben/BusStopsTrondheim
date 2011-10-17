package no.kriben.busstopstrondheim.io.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Properties;

import no.kriben.busstopstrondheim.io.ItalianSoapBusStopRepository;
import no.kriben.busstopstrondheim.model.BusStop;

import org.junit.Test;

public class ItalianSoapBusStopRepositoryTest {
    @Test
    public void testDownload() {
        try {
            Properties configFile = new Properties();
            configFile.load(this.getClass().getClassLoader()
                    .getResourceAsStream("atb_api_key.properties"));
            String username = configFile.getProperty("ATB_USERNAME");
            String password = configFile.getProperty("ATB_PASSWORD");

            ItalianSoapBusStopRepository repo = new ItalianSoapBusStopRepository(
                    username, password);
            MockProgressHandler progressHandler = new MockProgressHandler();
            List<BusStop> data = repo.getAll(progressHandler);
            assertTrue(data.size() > 0);
            assertEquals(1.0, progressHandler.getCompleteFraction(), 0.001);
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
            fail("Url was malformed");
        } catch (IOException e) {
            e.printStackTrace();
            fail("Unable to access api key.");
        }
    }
}
