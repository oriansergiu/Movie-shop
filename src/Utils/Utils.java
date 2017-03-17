package Utils;


/**
 * Created by Sergiu on 10/8/2016.
 */
public class Utils {

    public Utils() {}

    public boolean isInt(String str)
    {
        try
        {
            Integer.parseInt(str);
        } catch (NumberFormatException e)
        {
            return false;
        } catch (NullPointerException e)
        {
            return false;
        }
        return true;
    }
}
