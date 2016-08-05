package property;

import java.util.Locale;
import java.util.ResourceBundle;

public class PropertyUtil {
	public static String getMessage(final String key) {
		Locale locale = Locale.getDefault();
		ResourceBundle bundle = ResourceBundle.getBundle("Messages", locale);
		return bundle.getString(key);
	}
}
