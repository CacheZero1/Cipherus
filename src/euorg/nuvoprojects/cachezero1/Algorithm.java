package euorg.nuvoprojects.cachezero1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Algorithm {
    /**
     * Credit: spdqbr
     * 
     * Takes a string of numbers and mathematical operators and attempts
     * to evaluate it using the standard PEMDAS order of operations
     * @param expression the String to evaluate
     * @return The valuation of the string.
     * @throws NumberFormatException
    */
    public static double evalString(String expression) throws NumberFormatException{
        try{
         expression = expression.replaceAll("\\s+", "").replaceAll("(\\d)\\-", "$1~");
            Pattern pat = Pattern.compile("\\([^\\(]+?\\)");
            Matcher mat = pat.matcher(expression);
            if(mat.find()){
                String parens = mat.group();
                return evalString(expression.replaceFirst("\\([^\\(]+?\\)", evalString(parens.substring(1,parens.length()-1))+""));
            }
            pat = Pattern.compile("\\-?\\d*\\.?\\d+\\x5e\\-?\\d*\\.?\\d+");
            mat = pat.matcher(expression);
            
            while(mat.find()){
                String exp = mat.group();
                int index = exp.indexOf("^");
                expression = expression.replaceFirst("\\-?\\d*\\.?\\d+\\x5e\\-?\\d*\\.?\\d+", Math.pow(Double.parseDouble(exp.substring(0,index)),Double.parseDouble(exp.substring(index+1,exp.length())))+"");
            }
            
            pat = Pattern.compile("\\-?\\d*\\.?\\d+\\%\\-?\\d*\\.?\\d+");
            mat = pat.matcher(expression);
            while(mat.find()){
                String exp = mat.group();
                int index = exp.indexOf("%");
                expression = expression.replaceFirst("\\-?\\d*\\.?\\d+\\%\\-?\\d*\\.?\\d+", (Double.parseDouble(exp.substring(0,index))%Double.parseDouble(exp.substring(index+1,exp.length())))+"");
            }
            
            String[] subs = expression.split("\\+");
            if(subs.length > 1 && subs[0] != null && !subs[0].equals("")){
                double sum = evalString(subs[0]);
                for(int i = 1; i < subs.length; i++){
                    sum += evalString(subs[i]);
                }
                return sum;
            }
            subs = expression.split("~");
            if(subs.length > 1 && subs[0] != null && !subs[0].equals("")){
             double diff = evalString(subs[0]);
                for(int i = 1; i < subs.length; i++){
                    diff -= evalString(subs[i]);
                }
                return diff;
            }
            subs = expression.split("\\*");
            if(subs.length > 1){
                double prod = evalString(subs[0]);
                for(int i = 1; i < subs.length; i++){
                    prod *= evalString(subs[i]);
                }
                return prod;
            }
            subs = expression.split("/");
            if(subs.length > 1){
                double quot = evalString(subs[0]);
                for(int i = 1; i < subs.length; i++){
                    quot /= evalString(subs[i]);
                }
                return quot;
            }
 
            return Double.parseDouble(expression);
        }catch(NumberFormatException e){
            throw new NumberFormatException();
        }
    }
}
