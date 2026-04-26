import java.io.*;

public class Leggi {
private static BufferedReader br =
new BufferedReader(new InputStreamReader(System.in));
private static String s;

public static boolean unBoolean(){
input();
if(s != null && !s.equals("true")
&& !s.equals("false"))
System.err.println("Error: "+s+" is not a boolean");
return s!=null && s.equals("true");
}
public static byte unByte(){
try {
return Byte.parseByte(input());
} catch(NumberFormatException e){
if(s!=null) System.err.println("Error: "+s+
" is not a byte");
return 0;
}
}
public static short unoShort(){
try {
return Short.parseShort(input());
} catch(NumberFormatException e){
if(s!=null) System.err.println("Error: "+s+
" is not a short");
return 0;
}
}
public static int unInt(){
try {
return Integer.parseInt(input());
} catch(NumberFormatException e){
System.err.println("Error: "+s+" is not an integer");
return -1;
}
}
public static long unLong(){
try {
return Long.parseLong(input());
} catch(NumberFormatException e){
if(s!=null) System.err.println("Error: "+s+
" is not a long");
return 0;
}
}
public static float unFloat(){
try {
return Float.parseFloat(input());
} catch(NumberFormatException e){
if(s!=null) System.err.println("Error: "+s+
" is not a float");
} catch(NullPointerException e){}
return 0;
}
public static double unDouble(){
try {
return Double.parseDouble(input());
} catch(NumberFormatException e){
if(s!=null) System.err.println("Error: "+s+
" is not a double");
} catch(NullPointerException e){}
return 0;
}
public static char unChar(){
try {
return input().charAt(0);
} catch(IndexOutOfBoundsException e){
} catch(NullPointerException e){}
return ' ';
}
public static String unoString(){
try {
return br.readLine();
} catch(IOException e){
System.err.println("Error during "+
"input reading!");
return null;
}
}
private static String input(){
try {
s = br.readLine().trim()+" ";
s = s.substring(0,s.indexOf(" "));
if(s.length() < 1){
System.err.println("Error: insert at least one "+
"character or a space");
s = null;
}
} catch(IOException e){
System.err.println("Error during "+
"input reading!");
return null;
}
return s;
}
}
