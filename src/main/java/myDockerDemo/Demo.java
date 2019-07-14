package myDockerDemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/demo")
public class Demo {
	
	@GET
	public String hello() {
		return "URL Format: http://localhost:8080/myDockerDemo/v1/demo/User_Input{Digits}";
	}
	@SuppressWarnings("serial")
	HashMap<String, String> dic = new HashMap<String, String>() {{
        put("0", " ");
        put("1", " ");
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
      }};
      List<String> outputArray = new ArrayList<String>();
      
      public void combinations(String combination, String remainingString) {
          if (remainingString.length() == 0) {
        	  outputArray.add(combination);
          }
          else {
              String digit = remainingString.substring(0, 1);
              String letters = dic.get(digit);
              for (int i = 0; i < letters.length(); i++) {
                  String letter = dic.get(digit).substring(i, i + 1);
                  combinations(combination + letter, remainingString.substring(1));
              }
          }
      }
      public boolean readConfigFile(){
    	  boolean printResultInUpper = false;
    	  BufferedReader reader;
    	  try {
    		  System.out.print(System.getProperty("user.dir"));
    		  reader = new BufferedReader(new FileReader(new File("/config.txt")));
    		  String line = reader.readLine();
    		  while(line!=null) {
    			  String[] config = line.split(" ",0);
    			  if (config[0].equals("version")) {
    				  System.out.println("Version: "+config[0]);
    			  }
    			  else if (config[0].equals("printResultInUpper")) {
    				  printResultInUpper = Boolean.parseBoolean(config[1].toLowerCase());
    			  }
    			  else {
    				  System.out.println("Error in config file please contact your Dev");
    			  }
    			  line = reader.readLine();
    		  }
    		  reader.close();
    	  }catch(IOException e) {
    		  e.printStackTrace();
    	  }
    	  return printResultInUpper;
      }
      public String getResult(boolean printResultInUpper) {
    	  String delimiter = "";
    	  String res="";
    	  for (int i = 0; i< outputArray.size()-1;i++) {
    		  res = res + delimiter +  outputArray.get(i);
    		  delimiter = " ,";
    	  }
    	  if(printResultInUpper) {
    		  return res.toUpperCase();
    	  }
    	  else {
    		  return res;
    	  }
      }

      	@GET
      	@Produces(MediaType.TEXT_HTML)
      	@Path("{digit}")
      	public String compute(@PathParam("digit") String inputString) {
      		String outputString ="";
      		boolean printResultInUpper = readConfigFile();
      		combinations("", inputString);
      		outputString = getResult(printResultInUpper);
      		outputString ="<HTML><HEAD><Title>Result:"+inputString+"</Title>"+
      				"<BODY><H1> " + outputString + "</H1>"+
      				"</BODY></HTML>";
      		return outputString;
      	}

}
