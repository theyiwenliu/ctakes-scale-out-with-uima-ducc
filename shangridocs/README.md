# Integration with DUCC

## Changes
* In shangridocs/shangridocs-services/src/main/java/org/shangridocs/services/tika/TikaCtakesResource.java,  
Because the http request includes secure identity information about the submitting user, so not allowed to maintain similar interface like Tika and cTAKES in Shangridos.  
Instead, after after ajax “services/tika/ctakes” it execute submit command to submit job to DUCC.  
  * Original process: forwardTika, forwardCtakes => both completed in forwardProxy  
  * Modified process: forwardTika = > forwardProxy, forwardCtakes = > createTikaOutput + submitDuccJob   
* In shangridocs/shangridocs-webapp/src/main/resources   
Just a rough concern, maybe add a resources directory and keep tika and ducc output here.  
Or we can come up with a way to specify the path for this directory in a better way.  

## Update 1/11/2016  
* Added CTAKESContentToMetadataHandler to convert the cTAKES XML output to metadata based on CTAKESContentHandler  
* CTAKESContentHandler path: shangridocs/shangridocs-services/src/main/resources/CTAKESContentHandler
