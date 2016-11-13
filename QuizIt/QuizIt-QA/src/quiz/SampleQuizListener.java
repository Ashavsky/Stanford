package quiz;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * Application Lifecycle Listener implementation class SampleQuizListener
 *
 */
@WebListener
public class SampleQuizListener implements ServletContextListener {

    /**
     * Default constructor.
     */
    public SampleQuizListener() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  {
        // TODO Auto-generated method stub
    }

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  {
        List<Question> createQuestionsList = new ArrayList<Question>(); // Used in quiz creation
        arg0.getServletContext().setAttribute("createquestions", createQuestionsList);
    }

}