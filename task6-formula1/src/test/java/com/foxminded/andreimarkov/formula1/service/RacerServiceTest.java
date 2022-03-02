package com.foxminded.andreimarkov.formula1.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import com.foxminded.andreimarkov.formula1.dao.RacerDAOImpl;
import com.foxminded.andreimarkov.formula1.domain.Racer;
import java.time.LocalTime;

@ExtendWith(MockitoExtension.class)
class RacerServiceTest {
	
	@Mock
	private RacerDAOImpl dao;
	
	@InjectMocks
	private RacerService racerService;
	
	@Test
	void getRacers_whenGetFilesWithData_thenReturnListOfRacers() {
		String abbreviations = "./src/main/resources/abbreviations.txt";
		String start = "./src/main/resources/start.log";
		String end = "./src/main/resources/end.log";
		RacerService racerService = new RacerService(abbreviations, start, end);
		racerService.setDao(dao);
		List<Racer> listOfRacers = new ArrayList<>();
		
		listOfRacers = Arrays.asList(
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
		
		List<String> listFileAbbreviatios = Arrays.asList(
				"DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER",
				"SVF_Sebastian Vettel_FERRARI",
				"LHM_Lewis Hamilton_MERCEDES",
				"KRF_Kimi Raikkonen_FERRARI",
				"VBM_Valtteri Bottas_MERCEDES",
				"EOF_Esteban Ocon_FORCE INDIA MERCEDES",
				"FAM_Fernando Alonso_MCLAREN RENAULT",
				"CSR_Carlos Sainz_RENAULT",
				"SPF_Sergio Perez_FORCE INDIA MERCEDES",
				"PGS_Pierre Gasly_SCUDERIA TORO ROSSO HONDA",
				"NHR_Nico Hulkenberg_RENAULT",
				"SVM_Stoffel Vandoorne_MCLAREN RENAULT",
				"SSW_Sergey Sirotkin_WILLIAMS MERCEDES",
				"CLS_Charles Leclerc_SAUBER FERRARI",
				"RGH_Romain Grosjean_HAAS FERRARI",
				"BHS_Brendon Hartley_SCUDERIA TORO ROSSO HONDA",
				"MES_Marcus Ericsson_SAUBER FERRARI",
				"LSW_Lance Stroll_WILLIAMS MERCEDES",
				"KMH_Kevin Magnussen_HAAS FERRARI"
				);	
		List<String> listStartFile = Arrays.asList(
				"SVF2018-05-24_12:02:58.917",
				"NHR2018-05-24_12:02:49.914",
				"FAM2018-05-24_12:13:04.512",
				"KRF2018-05-24_12:03:01.250",
				"SVM2018-05-24_12:18:37.735",
				"MES2018-05-24_12:04:45.513",
				"LSW2018-05-24_12:06:13.511",
				"BHS2018-05-24_12:14:51.985",
				"EOF2018-05-24_12:17:58.810",
				"RGH2018-05-24_12:05:14.511",
				"SSW2018-05-24_12:16:11.648",
				"KMH2018-05-24_12:02:51.003",
				"PGS2018-05-24_12:07:23.645",
				"CSR2018-05-24_12:03:15.145",
				"SPF2018-05-24_12:12:01.035",
				"DRR2018-05-24_12:14:12.054",
				"LHM2018-05-24_12:18:20.125",
				"CLS2018-05-24_12:09:41.921",
				"VBM2018-05-24_12:00:00.000"
				);
		List<String> listEndFile = Arrays.asList(
				"MES2018-05-24_12:05:58.778",
				"RGH2018-05-24_12:06:27.441",
				"SPF2018-05-24_12:13:13.883",
				"LSW2018-05-24_12:07:26.834",
				"DRR2018-05-24_12:15:24.067",
				"NHR2018-05-24_12:04:02.979",
				"CSR2018-05-24_12:04:28.095",
				"KMH2018-05-24_12:04:04.396",
				"BHS2018-05-24_12:16:05.164",
				"SVM2018-05-24_12:19:50.198",
				"KRF2018-05-24_12:04:13.889",
				"VBM2018-05-24_12:01:12.434",
				"SVF2018-05-24_12:04:03.332",
				"EOF2018-05-24_12:19:11.838",
				"PGS2018-05-24_12:08:36.586",
				"SSW2018-05-24_12:17:24.354",
				"FAM2018-05-24_12:14:17.169",
				"CLS2018-05-24_12:10:54.750",
				"LHM2018-05-24_12:19:32.585"
				);	
											
		when(dao.readLinesFromFile(abbreviations)).thenReturn(listFileAbbreviatios);
		when(dao.readLinesFromFile(start)).thenReturn(listStartFile);
		when(dao.readLinesFromFile(end)).thenReturn(listEndFile);		
		assertThat("",racerService.getRacers().toString(),is(listOfRacers.toString()));
		verify(dao, times(3)).readLinesFromFile(any());
	}

}
