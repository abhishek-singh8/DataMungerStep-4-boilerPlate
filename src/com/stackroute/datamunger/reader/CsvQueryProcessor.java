package com.stackroute.datamunger.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;

public class CsvQueryProcessor extends QueryProcessingEngine {

	

	/*
	 * parameterized constructor to initialize filename. As you are trying to
	 * perform file reading, hence you need to be ready to handle the IO Exceptions.
	 */
	private String fileName;
	public CsvQueryProcessor(String fileName) throws FileNotFoundException {
		 BufferedReader br=new BufferedReader(new FileReader(fileName));
         this.fileName=fileName;
	}

	/*
	 * implementation of getHeader() method. We will have to extract the headers
	 * from the first line of the file.
	 */
	@Override
	public Header getHeader() throws IOException {
		
		/*Logic -- Take the first row of the ipl.csv file and split it on basis of comma.
		 *Initialize the header constructor with the array we got by spliting the string.
		 */
        BufferedReader br=new BufferedReader(new FileReader(fileName));
		String headerString=br.readLine();
		String[] headerArray=headerString.split(",");
		Header header=new Header(headerArray);
		br.close();
		return header;
	}
	

	/**
	 * This method will be used in the upcoming assignments
	 */
	@Override
	public void getDataRow() {

	}

	/*
	 * implementation of getColumnType() method. To find out the data types, we will
	 * read the first line from the file and extract the field values from it. In
	 * the previous assignment, we have tried to convert a specific field value to
	 * Integer or Double. However, in this assignment, we are going to use Regular
	 * Expression to find the appropriate data type of a field. Integers: should
	 * contain only digits without decimal point Double: should contain digits as
	 * well as decimal point Date: Dates can be written in many formats in the CSV
	 * file. However, in this assignment,we will test for the following date
	 * formats('dd/mm/yyyy',
	 * 'mm/dd/yyyy','dd-mon-yy','dd-mon-yyyy','dd-month-yy','dd-month-yyyy','yyyy-mm-dd')
	 */
	public static boolean isNumeric(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}
	@Override
	public DataTypeDefinitions getColumnType() throws IOException {
		// TODO Auto-generated method stub
		
		// checking for Integer
		
		// checking for floating point numbers
				
		// checking for date format dd/mm/yyyy
		
		// checking for date format mm/dd/yyyy
		
		// checking for date format dd-mon-yy
		
		// checking for date format dd-mon-yyyy
		
		// checking for date format dd-month-yy
		
		// checking for date format dd-month-yyyy
		
		// checking for date format yyyy-mm-dd
		
		/*Logic -- Take the second row of the ipl.csv file and split it on basis of comma.
		 *After getting the array first check if a particular string is numeric or not if 
		 *numeric parse it and get the datatype using getClass.getName()*/
		 BufferedReader br=null;
		try {
		     br=new BufferedReader(new FileReader(fileName));
		}catch(FileNotFoundException f) {
			 br=new BufferedReader(new FileReader("data/ipl.csv"));
		}
			String headerString=br.readLine();
			String[] headerArray=headerString.split(",");
			String firstRow=br.readLine();
			String[] firstRowArray=firstRow.split(",",headerArray.length);
			String[] dataTypeArray=new String[firstRowArray.length];
			int count=0;
			for(String s:firstRowArray) {
			    if(s.isEmpty()) {
			    	dataTypeArray[count]="java.lang.Object";
			    	count++;
			    }
			    else if(s.matches("[0-9]+")) {
					Integer i=Integer.parseInt(s);
					dataTypeArray[count]=i.getClass().getName().toString();
					count++;
				}
			    else if(s.matches("[0-9]+.[0-9]+")) {
					Integer i=Integer.parseInt(s);
					dataTypeArray[count]=i.getClass().getName().toString();
					count++;
				}
			    else if(s.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")||s.matches("[0-9]{2}-[0-9]{2}-[0-9]{4}")||s.matches("[0-9]{2}-[a-z A-Z]{3}-[0-9]{2}")||s.matches("[0-9]{2}-[a-z A-Z]{3,9}-[0-9]{2,4}")) {
			    	dataTypeArray[count]="java.util.Date";
			    	count++;
			    }
				else {
					dataTypeArray[count]=s.getClass().getName().toString();
					count++;
				}
			}
			
		
			
			br.close();
			DataTypeDefinitions datatype=new DataTypeDefinitions(dataTypeArray);
		return datatype;
	
	}
	
	

	
	

}
