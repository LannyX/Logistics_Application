
package exception;

/** 
* @ClassName:  NullParamException
* @Description: exception to handle null inputs in method
*/
public class DataValidationException extends Exception{
    public DataValidationException (String msg) {
        super(msg);
    }
}
