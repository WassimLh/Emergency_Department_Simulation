package fr.cs.simergy.gui;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;
/**
 * Redirects System.out to a JTextArea.
 * We used that class from an Internet website, as mentioned in our report. 
 * @see javax.swing.JTextArea
 * @see java.io.OutputStream
 * @author Glob
 * @version 0.2
 */
public class JTextAreaOutputStream extends OutputStream {
	private JTextArea m_textArea = null;

   /**
    * Method JTextAreaOutputStream.
    * @param aTextArea The JTextArea that receives the characters
    */
   public JTextAreaOutputStream(JTextArea aTextArea) {
      this.m_textArea = aTextArea;
   }

   /**
    * Writes a character in the JTextArea.
    * If the character is a carriage return, it scrolls.
    * @see java.io.OutputStream#write(int)
    */
   public void write(int b) throws IOException {
      byte[] bytes = new byte[1];
      bytes[0] = (byte)b;
      String newText = new String(bytes);
      m_textArea.append(newText);
      if (newText.indexOf('\n') > -1) {
         try {
            m_textArea.scrollRectToVisible(m_textArea.modelToView(
                        m_textArea.getDocument().getLength()));
         } catch (javax.swing.text.BadLocationException err) {
            err.printStackTrace();
         }
      }
   }

   /**
    * Writes a bytes array in the JTextArea and
    * scrolls the JTextArea at the end of the added text.
    * @see java.io.OutputStream#write(byte[])
    */
   public final void write(byte[] arg0) throws IOException {
      String txt = new String(arg0);
      m_textArea.append(txt);
      try {
         m_textArea.scrollRectToVisible(m_textArea.modelToView(m_textArea.getDocument().getLength()));
      } catch (javax.swing.text.BadLocationException err) {
         err.printStackTrace();
      }
   }
}
