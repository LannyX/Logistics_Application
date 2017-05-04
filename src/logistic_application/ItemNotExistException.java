
package logistic_application;

/** 
* @ClassName: ItemNotExistException
* @Description: exception to handle invalid inputs in method
*/
public class ItemNotExistException extends Exception{
    public ItemNotExistException (String msg) {
        super(msg);
    }
}
