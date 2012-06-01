package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.NullProgressMonitor;
import com.atlassian.jira.rest.client.RestClientException;
import com.atlassian.jira.rest.client.domain.Attachment;
import com.atlassian.jira.rest.client.domain.Comment;
import com.atlassian.jira.rest.client.domain.Field;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;

import forms.JiraForm;

/**
 * Simple Bean holding all the Informations for the Task so we can pass it to
 * the print.scala.html Template
 * 
 * @author tuxburner
 * 
 */
public class JiraPrintTask {

  public Issue issue;

  public List<JiraPrintImage> images;

  public List<Field> fields;

  public List<Comment> comments;

  public JiraPrintTask(final JiraForm jiraForm) throws URISyntaxException, RestClientException, IOException {
    final JerseyJiraRestClientFactory factory = new JerseyJiraRestClientFactory();
    final URI jiraServerUri = new URI(jiraForm.url);
    final JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, jiraForm.username, jiraForm.password);
    final NullProgressMonitor pm = new NullProgressMonitor();
    this.issue = restClient.getIssueClient().getIssue(jiraForm.task, pm);

    final Iterable<Comment> comments = this.issue.getComments();
    if (comments != null) {
      this.comments = new ArrayList<Comment>();
      for (final Comment comment : comments) {
        this.comments.add(comment);
      }
    }

    final Iterable<Attachment> attachments = this.issue.getAttachments();
    for (final Attachment attachment : attachments) {
      final String mimeType = attachment.getMimeType();
      if (StringUtils.isEmpty(mimeType) == false && mimeType.startsWith("image/")) {
        final URI self = attachment.getContentUri();

        final InputStream attachementIs = restClient.getIssueClient().getAttachment(pm, self);

        if (this.images == null) {
          this.images = new ArrayList<JiraPrintImage>();
        }

        this.images.add(new JiraPrintImage(attachment, attachementIs));

      }
    }
  }

}
