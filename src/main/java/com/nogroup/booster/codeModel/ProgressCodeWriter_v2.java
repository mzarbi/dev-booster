package com.nogroup.booster.codeModel;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import javax.annotation.Nonnull;

import com.helger.jcodemodel.JPackage;
import com.helger.jcodemodel.SourcePrintWriter;
import com.helger.jcodemodel.util.JCStringHelper;
import com.helger.jcodemodel.util.JCValueEnforcer;
import com.helger.jcodemodel.writer.AbstractCodeWriter;
import com.helger.jcodemodel.writer.FilterCodeWriter;

public class ProgressCodeWriter_v2 extends FilterCodeWriter{

  @FunctionalInterface
  public static interface IProgressTracker extends Serializable
  {
    void println (@Nonnull String sLine);
  }

  private final IProgressTracker m_aPT;

  public ProgressCodeWriter_v2 (@Nonnull final AbstractCodeWriter output, @Nonnull final IProgressTracker progress)
  {
    super (output);
    JCValueEnforcer.notNull (progress, "Progress");
    m_aPT = progress;
  }

  protected void report (@Nonnull final String sDirName, @Nonnull final String sFilename)
  {
    if (JCStringHelper.hasNoText (sDirName))
      m_aPT.println (sFilename);
    else
      m_aPT.println ( "\t\t [INFO] " + sDirName + '/' + sFilename + " built with success");
  }

  @Override
  public OutputStream openBinary (@Nonnull final String sDirName, @Nonnull final String sFilename) throws IOException
  {
    report (sDirName, sFilename);
    return super.openBinary (sDirName, sFilename);
  }

  @Override
  public SourcePrintWriter openSource (@Nonnull final JPackage aPackage,
                                       @Nonnull final String sFilename) throws IOException
  {
    report (toDirName (aPackage), sFilename);
    return super.openSource (aPackage, sFilename);
  }
}
