import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aldrich.portal.config.BeanConfigurator;
import com.aldrich.portal.service.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=BeanConfigurator.class)
//@Ignore
public class EmployeeTest 
{
	@Autowired
	EmployeeService surveyEmailService;
	
	@Test
	//@Ignore
	public void processFeedEmailTest()
	{
		surveyEmailService.saveEmployee();
	}

}
