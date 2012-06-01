package controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import views.html.index;
import views.html.print;

import com.atlassian.jira.rest.client.RestClientException;
import com.typesafe.config.ConfigFactory;

import forms.JiraForm;

/**
 * Default Controller for the Application
 * 
 * @author tuxburner
 * 
 */
public class Application extends Controller {

  public static Result index() {

    final Map<String, String> data = new HashMap<String, String>();

    final boolean hasPath = ConfigFactory.load().hasPath("jira.default.url");
    if (hasPath == true) {
      data.put("url", ConfigFactory.load().getString("jira.default.url"));
    }

    final Form<JiraForm> form = Controller.form(JiraForm.class).bind(data);

    return Results.ok(index.render(form));
  }

  public static Result showPrintableTask() {

    final Form<JiraForm> form = Controller.form(JiraForm.class).bindFromRequest();
    if (form.hasErrors()) {
      return Results.badRequest(index.render(form));
    } else {
      final JiraForm jiraForm = form.get();
      try {
        final JiraPrintTask jiraPrintTask = new JiraPrintTask(jiraForm);
        return Results.ok(print.render(jiraPrintTask));
      } catch (final RestClientException e) {
        Controller.flash("error", "Task: " + jiraForm.task + " does not exist.");
        return Results.badRequest(index.render(form));
      } catch (final URISyntaxException e) {
        return Results.badRequest("Es ist ein Fehler aufgetreten");
      } catch (final IOException e) {
        return Results.badRequest("Es ist ein Fehler aufgetreten");
      }

    }

  }

}
