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


/* First created by JCasGen Wed Jul 31 15:14:59 EDT 2013 */
package org.apache.uima.ducc.sampleapps;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;


/** 
 * Updated by JCasGen Thu Aug 01 14:48:37 EDT 2013
 * XML source: /users1/eae/workspace-ducc/uima-ducc/uima-ducc-examples/src/main/resources/org/apache/uima/ducc/sampleapps/DuccDocumentInfoTS.xml
 * @generated */
public class DuccDocumentInfo extends TOP {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(DuccDocumentInfo.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected DuccDocumentInfo() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DuccDocumentInfo(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DuccDocumentInfo(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: inputfile

  /** getter for inputfile - gets 
   * @generated */
  public String getInputfile() {
    if (DuccDocumentInfo_Type.featOkTst && ((DuccDocumentInfo_Type)jcasType).casFeat_inputfile == null)
      jcasType.jcas.throwFeatMissing("inputfile", "org.apache.uima.ducc.sampleapps.DuccDocumentInfo");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DuccDocumentInfo_Type)jcasType).casFeatCode_inputfile);}
    
  /** setter for inputfile - sets  
   * @generated */
  public void setInputfile(String v) {
    if (DuccDocumentInfo_Type.featOkTst && ((DuccDocumentInfo_Type)jcasType).casFeat_inputfile == null)
      jcasType.jcas.throwFeatMissing("inputfile", "org.apache.uima.ducc.sampleapps.DuccDocumentInfo");
    jcasType.ll_cas.ll_setStringValue(addr, ((DuccDocumentInfo_Type)jcasType).casFeatCode_inputfile, v);}    
   
    
  //*--------------*
  //* Feature: outputfile

  /** getter for outputfile - gets 
   * @generated */
  public String getOutputfile() {
    if (DuccDocumentInfo_Type.featOkTst && ((DuccDocumentInfo_Type)jcasType).casFeat_outputfile == null)
      jcasType.jcas.throwFeatMissing("outputfile", "org.apache.uima.ducc.sampleapps.DuccDocumentInfo");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DuccDocumentInfo_Type)jcasType).casFeatCode_outputfile);}
    
  /** setter for outputfile - sets  
   * @generated */
  public void setOutputfile(String v) {
    if (DuccDocumentInfo_Type.featOkTst && ((DuccDocumentInfo_Type)jcasType).casFeat_outputfile == null)
      jcasType.jcas.throwFeatMissing("outputfile", "org.apache.uima.ducc.sampleapps.DuccDocumentInfo");
    jcasType.ll_cas.ll_setStringValue(addr, ((DuccDocumentInfo_Type)jcasType).casFeatCode_outputfile, v);}    
   
    
  //*--------------*
  //* Feature: docseq

  /** getter for docseq - gets document sequence within work item
   * @generated */
  public int getDocseq() {
    if (DuccDocumentInfo_Type.featOkTst && ((DuccDocumentInfo_Type)jcasType).casFeat_docseq == null)
      jcasType.jcas.throwFeatMissing("docseq", "org.apache.uima.ducc.sampleapps.DuccDocumentInfo");
    return jcasType.ll_cas.ll_getIntValue(addr, ((DuccDocumentInfo_Type)jcasType).casFeatCode_docseq);}
    
  /** setter for docseq - sets document sequence within work item 
   * @generated */
  public void setDocseq(int v) {
    if (DuccDocumentInfo_Type.featOkTst && ((DuccDocumentInfo_Type)jcasType).casFeat_docseq == null)
      jcasType.jcas.throwFeatMissing("docseq", "org.apache.uima.ducc.sampleapps.DuccDocumentInfo");
    jcasType.ll_cas.ll_setIntValue(addr, ((DuccDocumentInfo_Type)jcasType).casFeatCode_docseq, v);}    
   
    
  //*--------------*
  //* Feature: byteoffset

  /** getter for byteoffset - gets offset of byte location of first character in document
   * @generated */
  public int getByteoffset() {
    if (DuccDocumentInfo_Type.featOkTst && ((DuccDocumentInfo_Type)jcasType).casFeat_byteoffset == null)
      jcasType.jcas.throwFeatMissing("byteoffset", "org.apache.uima.ducc.sampleapps.DuccDocumentInfo");
    return jcasType.ll_cas.ll_getIntValue(addr, ((DuccDocumentInfo_Type)jcasType).casFeatCode_byteoffset);}
    
  /** setter for byteoffset - sets offset of byte location of first character in document 
   * @generated */
  public void setByteoffset(int v) {
    if (DuccDocumentInfo_Type.featOkTst && ((DuccDocumentInfo_Type)jcasType).casFeat_byteoffset == null)
      jcasType.jcas.throwFeatMissing("byteoffset", "org.apache.uima.ducc.sampleapps.DuccDocumentInfo");
    jcasType.ll_cas.ll_setIntValue(addr, ((DuccDocumentInfo_Type)jcasType).casFeatCode_byteoffset, v);}    
  }

    