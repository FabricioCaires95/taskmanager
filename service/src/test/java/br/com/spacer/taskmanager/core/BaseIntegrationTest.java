package br.com.spacer.taskmanager.core;


import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import br.com.spacer.taskmanager.Application;
import br.com.spacer.taskmanager.api.ApiClient;
import org.junit.jupiter.api.BeforeEach;

@ActiveProfiles(profiles = "integration-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public abstract class BaseIntegrationTest {

    //@Autowired
    //private Flyway flyway;

    @LocalServerPort
    private int serverPort;

    @BeforeEach
    void setup() throws Exception {
        //setupFlyway();
        setupEach();
    }

    protected void setupEach() throws Exception {}

    protected void setLocalHostBasePath(ApiClient apiClient, String path) throws MalformedURLException {
        apiClient.setBasePath(new URL("http", "localhost", serverPort, path).toString());
    }

    private void setupFlyway() {
//        flyway.clean();
//        flyway.migrate();
    }

}
