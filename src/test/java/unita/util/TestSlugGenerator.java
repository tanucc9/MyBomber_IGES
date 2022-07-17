package unita.util;

import org.junit.Before;
import org.junit.Test;
import util.SlugGenerator;

import static org.junit.Assert.*;

public class TestSlugGenerator {
	SlugGenerator slugGenerator;
	String string;
	String slug;

	@Before
	public void setUp() {
		slugGenerator = new SlugGenerator();
		string = "Evento di prova 2022";
		slug = "evento-di-prova-2022";
	}

	@Test
	public void testSlugGeneration() {
		String slugFromString = slugGenerator.generaleSlugByString(string);
		assertEquals(slug, slugFromString);
	}
}
