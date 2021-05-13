package ghaya.tool;


import java.util.ResourceBundle;

public class toolProperties {

    private static toolProperties tp = new toolProperties();
    private static ResourceBundle rb = ResourceBundle.getBundle("config");

    private toolProperties(){}

    public static String getProperty(String str){
        return rb.getString(str);
    }
}
