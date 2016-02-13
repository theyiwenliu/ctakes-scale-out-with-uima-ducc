# ctakes scale out with uima ducc  

# Shangridocs Integration Readme   
https://github.com/emmaliucs/cTAKES-Scale-out-with-UIMA-DUCC/blob/master/shangridocs/README.md

# cTAKES Installation  
Download cTAKES zipfile and resource file.  
Just same as the step "Installing cTAKES" in:  
https://wiki.apache.org/tika/cTAKESParser  

# DUCC Guide (Especially for cTAKES)

## DUCC Docs:
1. https://cwiki.apache.org/confluence/display/UIMA/DUCC
2. http://uima.apache.org/d/uima-ducc-2.0.1/duccbook.html

## DUCC Setup:
Be sure to start with Java 7 [1]
See doc 1 https://cwiki.apache.org/confluence/display/UIMA/DUCC

## Major commands needed in ducc (details in doc 2):
* **${DUCC_HOME}/admin/ducc_post_install**  
  For setting up configuration file for ducc.  
  If network changes(hostname changes), will need to rerun it.  
  In doc 1, there is a step called "Privileged ducc_ling".  
  It is for situations where sharing of resources and data privacy are desired.  
  For example, user "ducc" would use ducc_ling to assume the identity of the job submitter in order to gain access to read and    write.  
  This is useful on large compute clusters with many users.  
  When testing on my small simulated cluster I do not use a privileged ducc_ling.  
  
* **${DUCC_HOME}/admin/check_ducc**  
  check which process, nodes and agents are running.  
  Should only have one agent, ws, or, pm rm and sm at a time.  
  For example:  
  Found ducc ws@guest-wireless-nup-nat-206-117-89-008.usc.edu PID 42584  
  Found ducc or@guest-wireless-nup-nat-206-117-89-008.usc.edu PID 42585  
  Found ducc pm@guest-wireless-nup-nat-206-117-89-008.usc.edu PID 42596  
  Found ducc rm@guest-wireless-nup-nat-206-117-89-008.usc.edu PID 42598  
  Found ducc sm@guest-wireless-nup-nat-206-117-89-008.usc.edu PID 42607  
  Found ducc agent@guest-wireless-nup-nat-206-117-89-008.usc.edu PID 42612  
  If you start ducc for more than once there might be multiple sets, make sure to have only one set of them.  
  check_ducc -i can help kill all nodes above, and then restart ducc again.  

* **${DUCC_HOME}/admin/start_ducc**  
  start ducc. 
  Potential error [4]

* **${DUCC_HOME}/admin/stop_ducc -a**  
  stop ducc. 

* **${DUCC_HOME}/bin/ducc_submit -f jobfile**  
  submit job file.  
  If submission is succesful, it will show  
  Job 1 submitted
  Or it might fail with two situations I know, Conection Failed[2] or System error[3]  

## Set Up a Working Directory  
* Inside working directory, create a input directory, output directory, logs directory, and clone the cTAKES directory.  
* Specify above information in the job file.

## Web Server:
  The DUCC Web Server default address is accessed from the URL http://[DUCC-HOST]:42133.  
  The [DUCC-HOST] is the hostname where the local installation has installed the DUCC Web Server.  
  For example, http://guest-wireless-nup-nat-206-117-89-008.usc.edu:42133/jobs.jsp to see job status.
  The hostname and port are configurable by the DUCC administrator in ducc.properties.  

## For cTAKES only  
* To avoid "java.lang.OutOfMemoryError: Java heap space", increasing the max heap to 4gb will do the trick.  
It is to accommodate loading of models Into Memory.  
* multipleDeploymentAllowed should be set to true in descriptors.  
* In CR, the type system should be specified.  
* Set UMLS username and password through  
export ctakes.umlsuser=[username], ctakes.umlspw=[password]  or
change them in ${CTAKES_HOME}/desc/ctakes-dictionary-lookup/desc/analysis_engine/DictionaryLookupAnnotator.xml  



[1] If with Java 8, web server will fail to start with following error:  
Problem accessing /system.daemons.jsp. Reason:  
PWC6033: Unable to compile class for JSP  
PWC6197: An error occurred at line: 24 in the jsp file:  
/$banner/$testpattern.jsp  
PWC6199: Generated servlet error:  
The type java.util.Map$Entry cannot be resolved. It is indirectly  
referenced from required .class files  

[2] It looks like the following:  
java.net.ConnectException: Connection refused  
	at java.net.PlainSocketImpl.socketConnect(Native Method)  
	at java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:339)  
	at java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:200)  
	at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:182)  
	at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)  
	at java.net.Socket.connect(Socket.java:579)  
	at java.net.Socket.connect(Socket.java:528)  
	at sun.net.NetworkClient.doConnect(NetworkClient.java:180)  
	at sun.net.www.http.HttpClient.openServer(HttpClient.java:432)  
	at sun.net.www.http.HttpClient.openServer(HttpClient.java:527)  
ssh failed, make sure user ducc has setup passwordless ssh, and try to ssh itself without password.  
Or there are multiple sets of nodes running on DUCC(or none), make sure there's only one set exist.  

[3] It looks like the following:  
Job errors:  
ERROR:  type=system error, text=job driver node unavailable.  
ERROR: Request ID not found in reply  
Could not submit job  
Wait for 30 seconds and you will be good, I guess the job driver hasn't got ready yet at the moment.

[4] It looks like the following: 
Starting broker on guest-wireless-nup-nat-206-117-89-008.usc.edu  
Waiting for broker ..... 0  
Waiting for broker ..... 1  
Waiting for broker ..... 2  
Waiting for broker ..... 3  
Waiting for broker ..... 4  
Waiting for broker ..... 5  
Waiting for broker ..... 6  
Waiting for broker ..... 7  
Waiting for broker ..... 8  
ActiveMQ broker cannot be found on guest-wireless-nup-nat-206-117-89-008.usc.edu:61617  
ssh failed, make sure user ducc has setup passwordless ssh, and try to ssh itself without password.
