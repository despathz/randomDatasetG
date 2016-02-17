package RandomDataSet.randomDs;
import java.util.*;

import org.apache.commons.lang3.StringUtils;

import java.io.*;

public class randomDG_v1
{
	private static final String CHARS =
		"0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzαβγδεζηθικλμνξπστυφχψωΓΔΘΛΞΠΣΦΨΩ!@#$%&";
	private static final String DUPCHARS = 
		"0123456789abcde";

	public static void main(String[] args) throws Exception 
	{
		int stringLength = Integer.parseInt(args[0]);
		int total = Integer.parseInt(args[1]);
		String fileZero = "", fileFive = "", fileTen = "", fileTwenty = "", fileFourty = "", fileEighty = ""; 
		fileZero += stringLength + "_0_" + total; 
		fileFive += stringLength + "_5_" + total; 
		fileTen += stringLength + "_10_" + total; 
		fileTwenty += stringLength + "_20_" + total; 
		fileFourty += stringLength + "_40_" + total;
		fileEighty += stringLength + "_80_" + total; 
		
		int count = 0, cas = 0, stop = 1; double perc = 0.0;
		int zero = 0, five = 0, ten = 0, twenty = 0, fourty = 0, eighty = 0, strLen = 0; 
		String str = "";
		
		try 
		{
			PrintWriter out0 = new PrintWriter(fileZero);
			PrintWriter out5 = new PrintWriter(fileFive);
			PrintWriter out10 = new PrintWriter(fileTen);
			PrintWriter out20 = new PrintWriter(fileTwenty);
			PrintWriter out40 = new PrintWriter(fileFourty);
			PrintWriter out80 = new PrintWriter(fileEighty);
			while(stop == 1)
			{
				cas = -1;
				strLen = getRandomStringLength();
				str = getRandomString(stringLength, strLen);
				if (stringLength == 4)
					strLen = stringLength;
				for (char ch: str.toCharArray())
				{
					String value = String.valueOf(ch);
					count = StringUtils.countMatches(str, value);
					
					perc = ((double)count / (double)strLen); 
					
					if ((perc >= 0.8) && (eighty < total))
					{
						if (((stringLength != 4) && (strLen >= 5)) || (stringLength == 4))
							cas = 80;
					}
					else if ((perc >= 0.4) && (fourty < total) && (cas < 80))
					{
						if (((stringLength != 4) && (strLen > 4)) || (stringLength == 4))
							cas = 40;
					}
					else if ((perc >= 0.2) && (twenty < total) && (cas < 40))
					{
						if (((stringLength != 4) && (strLen > 4)) || (stringLength == 4))
							cas = 20;
					}
					else if ((perc >= 0.1) && (ten < total) && (cas < 20))
					{	
						if (((stringLength != 4) && (strLen > 4)) || (stringLength == 4))
							cas = 10;
					}
					else if ((perc >= 0.05) && (five < total) && (cas < 10))
					{
						if (((stringLength != 4) && (strLen > 4)) || (stringLength == 4))
							cas = 5;
					}
					else if ((count == 1) &&  (zero < total) && (cas < 5))
					{
						if ((stringLength == 4))
							cas = 0; 
					}
					else 
						cas = -1;
				}
				
				str+="\n";
				switch(cas)
				{
					case(0):
						out0.write(str);
						zero++;
						break;
					case(5):
						out5.write(str);
						five++;
						break;
					case(10):
						out10.write(str);
						ten++;
						break;
					case(20):
						out20.write(str);
						twenty++;
						break;
					case(40):
						out40.write(str);
						fourty++;
						break;
					case(80):
						out80.write(str);
						eighty++;
						break;
					default:
						break;
				}
				str = "";
				switch(stringLength)
				{
					case(4):
						if ((zero == total) && (fourty == total) && (twenty == total) && (ten == total) && (five == total))
						{
							for(char strT : CHARS.toCharArray())
							{
								String finalstr = String.valueOf(strT);
								for (int num = 1; num < 4; num++)
									finalstr += String.valueOf(strT);
								finalstr += "\n";
								out80.write(finalstr);
							}		
							stop = 0;
						}
						break;
					case(100):
						if ((fourty == total) && (twenty == total) && (ten == total) && (five == total) && (eighty == total))
						{
							stop = 1; 
							while(stop == 1)
							{
								count = 0;
								strLen = getRandomStringLength();
								str = getRandomString(stringLength, strLen);
								for (char ch: str.toCharArray())
								{
									String value = String.valueOf(ch);
									count = StringUtils.countMatches(str, value);
									if (count != 1)
										break;
								}
								if ((count == 1) && (strLen > 4))
								{
									str += "\n";
									out0.write(str);
									zero++;
								}
								if (zero == total)
									stop = 0;
							}
						}
						break;
					default:
						break;
				}
			}
		       out0.close();  out5.close(); out10.close(); out20.close(); out40.close(); out80.close();
		 }
	        catch (IOException e)
	        {
	            System.out.println("Exception ");       
	        }
	}
	
	/*
	 * Get a random length for the new string of the dataset. 
	 * Max length: 100
	 */
	public static Integer getRandomStringLength()
	{
		int item = 0;
		item = new Random().nextInt(CHARS.length());
		return item;
	}

	/*
	 * Create the new string with length = item
	 * If stringLength equals to 4 then we create the dataset of the 4character strings
	 */
	public static String getRandomString(int stringLength, int item) 
	{		
		String str = "";
		if (stringLength == 4)
			item = stringLength;
		for (int i = 0; i < item; i++) 
			str += getRandomStringCharacters(stringLength);
		return str;
	}

	private static String getRandomStringCharacters(int stringLength) 
	{
		Random randomNum = new Random();
		StringBuilder randomChar = new StringBuilder();
		switch(stringLength)
		{
			case(4):
				randomChar.append(CHARS.charAt(randomNum.nextInt(CHARS.length() - 1)));
				break;
			case(100):
				randomChar.append(DUPCHARS.charAt(randomNum.nextInt(DUPCHARS.length())));
				break;
		}
		return randomChar.toString();

	}
	
}
