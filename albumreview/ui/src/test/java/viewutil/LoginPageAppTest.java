package viewutil;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

/**
 * Login Page App test.
 */
public class LoginPageAppTest extends ApplicationTest {
  
  @Override
  public void start(Stage stage) throws Exception {
    new LoginPageApp().start(stage);
  }

  @Test
  public void appTest() {
    assertTrue(Stage.getWindows().size() > 0);
  }

}
