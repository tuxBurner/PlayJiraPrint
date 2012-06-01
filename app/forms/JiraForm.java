package forms;

import play.data.validation.Constraints.Required;

/**
 * This Form is used when filling out the Form
 * 
 * @author tuxburner
 * 
 */
public class JiraForm {

  /**
   * Url to the Jira System
   */
  @Required(message = "Url for the Jira is needed")
  public String url;

  /**
   * Name of the task we want to print
   */
  @Required(message = "Task is needed")
  public String task;

  /**
   * Name of the user in the jira
   */
  @Required(message = "Username for the Jira is needed")
  public String username;

  /**
   * Password of the user in the jira
   */
  @Required(message = "Password for the Jira is needed")
  public String password;

}
