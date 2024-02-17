package com.ontariotechu.sofe3980U;

import java.util.ArrayList;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'
	/**
	* A constructor that generates a binary object.
	*
	* @param number a String of the binary values. It should conatins only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	*/
    public Binary(String number) {
		for (int i = 0; i < number.length(); i++) {
			// check each character if it's not 0 or 1
			char ch=number.charAt(i);
			if(ch!='0' && ch!='1') {
				number="0"; // if not store "0" and end the function
				return;
			}
		}
		// remove any trailing zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg)!='0')
				break;
		}
		//beg has the index of the first non zero digit in the number
		this.number=number.substring(beg); // exclude the trailing zeros if any
		// uncomment the following code
		if(this.number=="") { // replace empty strings with a single zero
			this.number="0";
		}
    }
	/**
	* Return the binary value of the variable
	*
	* @return the binary value in a string format.
	*/
	public String getValue()
	{
		return this.number;
	}
	/**
	* Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param num1 The first addend object
	* @param num2 The second addend object
	* @return A binary variable with a value of <i>num1+num2</i>.
	*/
	public static Binary add(Binary num1,Binary num2)
	{
		// the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		//initial variable
		int carry=0;
		String num3="";  // the binary value of the sum
		while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
		{
			int sum=carry; // previous carry
			if(ind1>=0){ // if num1 has a digit to add
				sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if(ind2>=0){ // if num2 has a digit to add
				sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
				ind2--; //update ind2
			}
			carry=sum/2; // the new carry
			sum=sum%2;  // the resultant digit
			num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
		}
		Binary result=new Binary(num3);  // create a binary object with the calculated value.
		return result;
		
	}
	/**
	* Performing the bitwise logical OR of the numbers stored in num1 and num2
	*
	* @param num1 The first Binary object
	* @param num2 The second Binary object
	* @return A Binary Object with a value of <i>num1 OR num2</i>.
	*/
    public static Binary or(Binary num1,Binary num2)
	{
		// Obtain string representation of both binary numbers
		String number1=num1.number;
		String number2=num2.number;
        // If lengths are different, pad with leading zeros
        int maxLength = Math.max(num1.number.length(), num2.number.length());
        number1 = String.format("%" + maxLength + "s", number1).replace(' ', '0');
        number2 = String.format("%" + maxLength + "s", number2).replace(' ', '0');
        
        // Use StringBuilder to append to number3
        StringBuilder number3 = new StringBuilder();

        // OR operation
        for (int i = 0; i < maxLength; i++) {
            char bit1 = number1.charAt(i);
            char bit2 = number2.charAt(i);
            if (bit1 == '1' || bit2 == '1') {
                number3.append('1');
            } else {
                number3.append('0');
            }
        }
		
		Binary result=new Binary(number3.toString());  // create a binary object with the calculated value.
		return result;
	}

	/**
	* Performing the bitwise logical AND of the numbers stored in num1 and num2
	*
	* @param num1 The first Binary object
	* @param num2 The second Binary object
	* @return A Binary Object with a value of <i>num1 AND num2</i>.
	*/
    public static Binary and(Binary num1,Binary num2)
	{
		// Obtain string representation of both binary numbers
		String number1=num1.number;
		String number2=num2.number;
        // If lengths are different, pad with leading zeros
        int maxLength = Math.max(num1.number.length(), num2.number.length());
        number1 = String.format("%" + maxLength + "s", number1).replace(' ', '0');
        number2 = String.format("%" + maxLength + "s", number2).replace(' ', '0');
        
        // Use StringBuilder to append to number3
        StringBuilder number3 = new StringBuilder();

        // AND operation
        for (int i = 0; i < maxLength; i++) {
            char bit1 = number1.charAt(i);
            char bit2 = number2.charAt(i);
            if (bit1 == '1' && bit2 == '1') {
                number3.append('1');
            } else {
                number3.append('0');
            }
        }
		
		Binary result=new Binary(number3.toString());  // create a binary object with the calculated value.
		return result;
	}

	/**
	* Performing the multiplication of the numbers stored in num1 and num2
	*
	* @param num1 The first Binary object
	* @param num2 The second Binary object
	* @return A Binary Object with a value of <i>num1 * num2</i>.
	*/
    public static Binary multiply(Binary num1,Binary num2)
	{
        
        StringBuilder number3 = new StringBuilder(); // Use StringBuilder to append to number3
        ArrayList<Binary> addends = new ArrayList<Binary>(); // list of numbers to add after multiplying

        for (int i = num1.number.length()-1; i >= 0; i--) {
            char bit1 = num1.number.charAt(i);
            for (int j = 0; j <= num2.number.length()-1; j++) {
                char bit2 = num2.number.charAt(j);
                // and condition is the same as multiplying
                if (bit1 == '1' && bit2 == '1') { number3.append('1'); } 
                else { number3.append('0'); }
            }
            int size = addends.size();
            while (size > 0) {
                number3.append('0');
                size--;
            }
            addends.add(new Binary(number3.toString()));
            number3.setLength(0);  // clear number3 for next addends
        }
        Binary product=new Binary("0");
		for (Binary addend : addends) { // the sum of addends = the product
            product = Binary.add(product, addend);
        }
		return product;
	}

    
}	
