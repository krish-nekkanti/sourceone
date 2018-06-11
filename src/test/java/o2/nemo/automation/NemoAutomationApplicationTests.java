package o2.nemo.automation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ah8.app.ivservice.AH8DicomApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AH8DicomApplication.class)
@WebAppConfiguration
public class NemoAutomationApplicationTests {

	
	@Test
	public void contextLoads() {
	}

}
