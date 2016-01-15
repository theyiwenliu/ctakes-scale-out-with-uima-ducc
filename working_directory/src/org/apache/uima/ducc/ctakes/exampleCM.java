/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.ducc.sampleapps;

/*
 * This sample Cas Multiplier reads compressed CASes from a specified zipfile
 * and returns each as a child CAS. A zipfile may contain zip-compressed XMI
 * format CASes or UIMA compressed binary form 6 format CASes. 
 * 
 * See more information in DUCC Book chapters on sample applications.
 * 
 */

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.uima.UIMAFramework;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasMultiplier_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.AbstractCas;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.impl.Serialization;
import org.apache.uima.cas.impl.XmiCasDeserializer;
import org.apache.uima.ducc.Workitem;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.Level;
import org.apache.uima.util.Logger;
import org.apache.uima.util.XMLInputSource;
import org.apache.uima.util.XMLParser;

public class DuccCasCM extends JCasMultiplier_ImplBase {
  private String inputFileName;
  private String outputFileName;
  private FileInputStream fis;
  private ZipInputStream zis;
  private ZipEntry nextEntry;
  private Workitem wi;
  private int docInWI;
  private boolean readingXmiFormat;
  private TypeSystem inputTS;
  private Logger logger;

  public boolean hasNext() throws AnalysisEngineProcessException {
	try {
		nextEntry = zis.getNextEntry();
	} catch (IOException e) {
		throw new AnalysisEngineProcessException(e);
	}
	  return (nextEntry != null) ? true : false;
  }

  public AbstractCas next() throws AnalysisEngineProcessException {
    JCas newcas = getEmptyJCas();
    if (0 == docInWI) {
    	if (nextEntry.getName().equals("typesystem.xml")) {
        	getTypesystem();
        	readingXmiFormat = false;
        }
        else {
        	readingXmiFormat = true;
        }
    }
    else {
  	  if (nextEntry.getName().equals("typesystem.xml")) {
		  throw new AnalysisEngineProcessException(new RuntimeException(
				  "typesystem.xml entry found in the middle of input zipfile "+inputFileName));
	  }
    }
    byte[] buff = new byte[10000];
    int bytesread;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try {
    	while (-1 != (bytesread = zis.read(buff))) {
    		baos.write(buff,0,bytesread);
    	}
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        if (readingXmiFormat) {
        	XmiCasDeserializer.deserialize(bis, newcas.getCas());
        }
        else {
        	Serialization.deserializeCAS(newcas.getCas(), bis, inputTS, null);
        }
	} catch (Exception e) {
		throw new AnalysisEngineProcessException(e);
	}
    Iterator<FeatureStructure> fsit = newcas.getIndexRepository().getAllIndexedFS(newcas.getCasType(DuccDocumentInfo.type));
    DuccDocumentInfo di;
    if (fsit.hasNext()) {
    	di = (DuccDocumentInfo) fsit.next();
    }
    else {
        di = new DuccDocumentInfo(newcas);
        di.addToIndexes();
    }
    di.setInputfile(inputFileName);
    di.setOutputfile(outputFileName);
    di.setDocseq(docInWI++);
    return newcas;
  }

  @Override
  public void process(JCas jcas) throws AnalysisEngineProcessException {
    Iterator<FeatureStructure> fsit = jcas.getIndexRepository().getAllIndexedFS(jcas.getCasType(Workitem.type));
    if (!fsit.hasNext()) {
      throw new AnalysisEngineProcessException(new RuntimeException("No workitem FS in CAS"));
    }
    wi = (Workitem) fsit.next();
    logger.log(Level.INFO, "DuccCasCM: inputs "+wi.getInputspec()+" outputs "+wi.getOutputspec());
    try {
      openInputFile(wi);
    } catch (IOException e) {
      throw new AnalysisEngineProcessException(e);
    }
  }


  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    logger = aContext.getLogger();
  }


  private void openInputFile(Workitem wi) throws IOException {
    inputFileName = wi.getInputspec();
    outputFileName = wi.getOutputspec();
    fis = new FileInputStream(new File(inputFileName));
    zis = new ZipInputStream(new BufferedInputStream(fis,1024*100));
    docInWI = 0;
  }


  private void getTypesystem() throws AnalysisEngineProcessException {
	  byte[] buff = new byte[10000];
	  int bytesread;
	  ByteArrayOutputStream baos = new ByteArrayOutputStream();
	  try {
	  	while (-1 != (bytesread = zis.read(buff))) {
	  		baos.write(buff,0,bytesread);
	  	}
	  	ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
	  	// Get XML parser from framework
	  	XMLParser xmlParser = UIMAFramework.getXMLParser();
	  	// Parse type system descriptor
	  	TypeSystemDescription tsDesc = xmlParser.parseTypeSystemDescription(new XMLInputSource((InputStream)bis,null));
	  	// Use type system description to create CAS and get the type system object
	  	inputTS = CasCreationUtils.createCas(tsDesc, null, null).getTypeSystem();
	  	// advance to first input CAS
		nextEntry = zis.getNextEntry();
		} catch (Exception e) {
			throw new AnalysisEngineProcessException(e);
		}
  }
}
