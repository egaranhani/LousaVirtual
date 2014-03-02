package garanhani.utils;

public class StringUtils {
	public static String toStringCommaSeparated(String ... list){
		StringBuilder builder = new StringBuilder(list[0]);
		for (int i = 1; i < list.length; i++)
			builder.append("," + list[i]);
		return builder.toString();
	}
}
