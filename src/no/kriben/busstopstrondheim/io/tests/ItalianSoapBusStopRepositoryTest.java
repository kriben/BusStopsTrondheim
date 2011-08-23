package no.kriben.busstopstrondheim.io.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

import no.kriben.busstopstrondheim.io.ItalianSoapBusStopRepository;

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
            String data = repo.getData();
            assertTrue(data.length() > 0);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            fail("Url was malformed");
        } catch (IOException e) {
            e.printStackTrace();
            fail("Unable to access api key.");
        }
    }
}
