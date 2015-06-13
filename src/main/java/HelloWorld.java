
  /*Copyright 1999-2004 Carnegie Mellon University.
  Portions Copyright 2004 Sun Microsystems, Inc.
  Portions Copyright 2004 Mitsubishi Electric Research Laboratories.
  All Rights Reserved.  Use is subject to license terms.
 
  See the file "license.terms" for information on usage and
  redistribution of this file, and for a DISCLAIMER OF ALL
  WARRANTIES.*/

import java.io.IOException;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;


public class HelloWorld {
    
    //Main method for running the HelloWorld demo.
    public static void main(String[] args) {
		
    	ConfigurationManager cm = new ConfigurationManager(HelloWorld.class.getResource("/sphinx-custom.xml"));

        Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();

        Result result = null;
        Boolean done = false;

    	// start the microphone or exit if the programm if this is not possible
        Microphone microphone = (Microphone) cm.lookup("microphone");
        if (!microphone.startRecording()) {
            System.out.println("Cannot start microphone.");
            recognizer.deallocate();
            System.exit(1);
        }
        
	while (!done) {
           result = recognizer.recognize();
           System.out.println("Result: " + result);
           done = result.toString().equals("stop");
      	}
    }

}
