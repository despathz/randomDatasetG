
import java.util.*;

import java.io.*;

public class randomDG_v2
{
	private static final String CHARS =
		"0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzαβγδεζηθικλμνξπστυφχψωΓΔΘΛΞΠΣΦΨΩ!@#$%&";

	public static void main(String[] args) throws FileNotFoundException 
	{
		int iLen = Integer.parseInt(args[0]);
		int dProbabilityOfRepetition = Integer.parseInt(args[1]);
		int total = Integer.parseInt(args[2]);
		int hundred = 100;
		String file = "";
		
		file += "file_" + iLen + "_" + dProbabilityOfRepetition + "_" + total; System.out.println(file);
		PrintWriter out = new PrintWriter(file);

		for (int i = 0; i < total; i++) //create total number of strings
		{
			String myString = "";
			String mainChar = getRandomStringCharacters(); //select a main character from the available characters
			for (int iCur = 0; iCur < iLen; iCur++) 
			{
				int percent = (hundred / iLen) * iCur;
				System.out.println(percent);
				if (percent <= dProbabilityOfRepetition)
					myString+=mainChar;
				else
				{
					String newStr = "";
					while (true)
					{
						newStr = getRandomStringCharacters();
						if (!myString.contains(newStr))
							break;
					}
					myString += newStr;
				}
			}
			myString+="\n";
			out.write(myString);
		}
		out.close();
	}
	
	private static String getRandomStringCharacters() 
	{
		Random randomNum = new Random();
		StringBuilder randomChar = new StringBuilder();
		
		randomChar.append(CHARS.charAt(randomNum.nextInt(CHARS.length() - 1)));
				
		return randomChar.toString();

	}

}
