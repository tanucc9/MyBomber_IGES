package util;

public class SlugGenerator {
	public String generaleSlugByString(String str) {
		return str
				.replaceAll("[^a-zA-Z0-9]+", "-")
				.replaceAll("-+", "-")
				.replaceAll("^-+", "")
				.replaceAll("-+$", "")
//				.replaceAll("([a-zA-Z])[a-zA-Z]*", "$1")
//				.replaceAll("(\\d)\\d*", "$1")
				.toLowerCase()
				;
	}
}
