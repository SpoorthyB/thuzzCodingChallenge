package thuzzchallenge;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NormalizePathString {
	static final Pattern current = Pattern.compile("/\\./");
	static final Pattern parent = Pattern.compile("/\\../");
	
	public static String normalize(String input){		 
		 
		Matcher dotFinder = current.matcher(input);		
		StringBuilder tempString = new StringBuilder();
		int currIndex = 0;
		
		//for every Match found eliminate the dot
		while (dotFinder.find()) {
	        int startIndex = dotFinder.start();	        
	        int endIndex = dotFinder.end();
	        tempString.append(input.substring(currIndex, startIndex));
	        currIndex = endIndex - 1;	        
	    }
		//append the remainder of String
		tempString.append(input.substring(currIndex));
		
		String partial = tempString.toString();
		
		//find ".." in this new partial string
		Matcher dotdotFinder = parent.matcher(partial);
		
		currIndex = 0;
		tempString.delete(0, tempString.length());//clear the string Builder
		
		//for every Match found eliminate the dot dot and the parent directory
		while(dotdotFinder.find()){
			int startIndex = dotdotFinder.start();
			
			while(startIndex > 0){
				startIndex--;
	        	if(partial.charAt(startIndex) =='/') break;
	        	
	        }
	        int endIndex = dotdotFinder.end();
	        tempString.append(partial.substring(currIndex, startIndex));
	        currIndex = endIndex - 1;
		}
		
		//append the remainder of String
		tempString.append(partial.substring(currIndex));
				
		return tempString.toString();
	}

	public static void main(String[] args) {
		String inputString = new String("foo/./bar/../baz");
		
		String result = normalize(inputString);
		System.out.println("The normalized String is: " + result);

	}

}
