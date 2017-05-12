
package exception;

/** 
* @ClassName:  NullParamException
* @Description: exception to handle null inputs in method
*/
public class NullParamException extends Exception{
    public NullParamException (String msg) {
        super(msg);
    }
}
