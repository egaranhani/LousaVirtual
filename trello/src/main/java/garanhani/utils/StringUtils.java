package garanhani.utils;

public class StringUtils {
	public static String stringCommaSeparated(String ... list){
		return separatedString(",", list);
	}

	public static String stringSlashSeparated(String ... list){
		return separatedString("/", list);
	}

	private static String separatedString(String separator, String ... list){
		StringBuilder builder = new StringBuilder(list[0]);
		for (int i = 1; i < list.length; i++)
			builder.append(separator + list[i]);
		return builder.toString();
	}
}
