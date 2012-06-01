package controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import com.atlassian.jira.rest.client.domain.Attachment;

public class JiraPrintImage {

  public final String base64Content;
  public Attachment attachment;

  /**
   * This is used for displaying the images which are located in the
   * {@link Attachment}
   * 
   * @param attachment
   * @param is
   * @throws IOException
   */
  public JiraPrintImage(final Attachment attachment, final InputStream is) throws IOException {

    final ByteArrayOutputStream baos = new ByteArrayOutputStream(attachment.getSize());
    IOUtils.copy(is, baos);
    IOUtils.closeQuietly(baos);
    IOUtils.closeQuietly(is);

    final byte[] encode = Base64.encodeBase64(baos.toByteArray());
    this.base64Content = new String(encode);

    this.attachment = attachment;

  }

}
