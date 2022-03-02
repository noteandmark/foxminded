package com.foxminded.andreimarkov.formula1.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.foxminded.andreimarkov.formula1.domain.Racer;

class FormatterTest {
	private Formatter formatter = new Formatter();

	@Test
	void format_whenGetListOfRacers_thenReturnOutputString() {
		String expected;
		String actual;
		List<Racer> racers = new ArrayList<>();
		racers = Arrays.asList(
			new Racer("SVF", "Sebastian Vettel", "FERRARI", LocalTime.parse("12:02:58.917"), LocalTime.parse("12:04:03.332"), 64415),
			new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER", LocalTime.parse("12:14:12.054"), LocalTime.parse("12:15:24.067"), 72013),
			new Racer("VBM", "Valtteri Bottas", "MERCEDES", LocalTime.parse("12:00"), LocalTime.parse("12:01:12.434"), 72434),
			new Racer("LHM", "Lewis Hamilton", "MERCEDES", LocalTime.parse("12:18:20.125"), LocalTime.parse("12:19:32.585"), 72460),
			new Racer("SVM", "Stoffel Vandoorne", "MCLAREN RENAULT", LocalTime.parse("12:18:37.735"), LocalTime.parse("12:19:50.198"), 72463),
			new Racer("KRF", "Kimi Raikkonen", "FERRARI", LocalTime.parse("12:03:01.250"), LocalTime.parse("12:04:13.889"), 72639),
			new Racer("FAM", "Fernando Alonso", "MCLAREN RENAULT", LocalTime.parse("12:13:04.512"), LocalTime.parse("12:14:17.169"), 72657),
			new Racer("SSW", "Sergey Sirotkin", "WILLIAMS MERCEDES", LocalTime.parse("12:16:11.648"), LocalTime.parse("12:17:24.354"), 72706),
			new Racer("CLS", "Charles Leclerc", "SAUBER FERRARI", LocalTime.parse("12:09:41.921"), LocalTime.parse("12:10:54.750"), 72829),
			new Racer("SPF", "Sergio Perez", "FORCE INDIA MERCEDES", LocalTime.parse("12:12:01.035"), LocalTime.parse("12:13:13.883"), 72848),
			new Racer("RGH", "Romain Grosjean", "HAAS FERRARI", LocalTime.parse("12:05:14.511"), LocalTime.parse("12:06:27.441"), 72930),
			new Racer("PGS", "Pierre Gasly", "SCUDERIA TORO ROSSO HONDA", LocalTime.parse("12:07:23.645"), LocalTime.parse("12:08:36.586"), 72941),
			new Racer("CSR", "Carlos Sainz", "RENAULT", LocalTime.parse("12:03:15.145"), LocalTime.parse("12:04:28.095"), 72950),
			new Racer("EOF", "Esteban Ocon", "FORCE INDIA MERCEDES", LocalTime.parse("12:17:58.810"), LocalTime.parse("12:19:11.838"), 73028),
			new Racer("NHR", "Nico Hulkenberg", "RENAULT", LocalTime.parse("12:02:49.914"), LocalTime.parse("12:04:02.979"), 73065),
			new Racer("BHS", "Brendon Hartley", "SCUDERIA TORO ROSSO HONDA", LocalTime.parse("12:14:51.985"), LocalTime.parse("12:16:05.164"), 73179),
			new Racer("MES", "Marcus Ericsson", "SAUBER FERRARI", LocalTime.parse("12:04:45.513"), LocalTime.parse("12:05:58.778"), 73265),
			new Racer("LSW", "Lance Stroll", "WILLIAMS MERCEDES", LocalTime.parse("12:06:13.511"), LocalTime.parse("12:07:26.834"), 73323),
			new Racer("KMH", "Kevin Magnussen", "HAAS FERRARI", LocalTime.parse("12:02:51.003"), LocalTime.parse("12:04:04.396"), 73393)
				);
		expected =
		"1. Sebastian Vettel    | FERRARI                       | 1:04.415\n" +
		"2. Daniel Ricciardo    | RED BULL RACING TAG HEUER     | 1:12.013\n" +
		"3. Valtteri Bottas     | MERCEDES                      | 1:12.434\n" +
		"4. Lewis Hamilton      | MERCEDES                      | 1:12.460\n" +
		"5. Stoffel Vandoorne   | MCLAREN RENAULT               | 1:12.463\n" +
		"6. Kimi Raikkonen      | FERRARI                       | 1:12.639\n" +
		"7. Fernando Alonso     | MCLAREN RENAULT               | 1:12.657\n" +
		"8. Sergey Sirotkin     | WILLIAMS MERCEDES             | 1:12.706\n" +
		"9. Charles Leclerc     | SAUBER FERRARI                | 1:12.829\n" +
		"10.Sergio Perez        | FORCE INDIA MERCEDES          | 1:12.848\n" +
		"11.Romain Grosjean     | HAAS FERRARI                  | 1:12.930\n" +
		"12.Pierre Gasly        | SCUDERIA TORO ROSSO HONDA     | 1:12.941\n" +
		"13.Carlos Sainz        | RENAULT                       | 1:12.950\n" +
		"14.Esteban Ocon        | FORCE INDIA MERCEDES          | 1:13.028\n" +
		"15.Nico Hulkenberg     | RENAULT                       | 1:13.065\n" +
		"-----------------------------------------------------------------\n" +
		"16.Brendon Hartley     | SCUDERIA TORO ROSSO HONDA     | 1:13.179\n" +
		"17.Marcus Ericsson     | SAUBER FERRARI                | 1:13.265\n" +
		"18.Lance Stroll        | WILLIAMS MERCEDES             | 1:13.323\n" +
		"19.Kevin Magnussen     | HAAS FERRARI                  | 1:13.393\n";

		actual = formatter.format(racers);
		assertEquals(expected.toString(), actual);
	}

}
