package util;

public class SlugGenerator {
	public String generaleSlugByString(String str) {
		return str
				.replaceAll("[^a-zA-Z0-9]+", "-")
				.replaceAll("-+", "-")
				.replaceAll("^-+", "")
				.replaceAll("-+$", "")
				.toLowerCase()
				;
	}
}
