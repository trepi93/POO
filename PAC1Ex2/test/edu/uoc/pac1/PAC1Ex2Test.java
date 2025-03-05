package edu.uoc.pac1;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PAC1Ex2Test {

    private ByteArrayOutputStream outContent;
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @Order(1)
    @DisplayName("isSuitableForDrugs")
    public void testIsSuitableForDrugs() {
        assertTrue(PAC1Ex2.isSuitableForDrugs(150.0));
        assertTrue(PAC1Ex2.isSuitableForDrugs(500.0));
        assertTrue(PAC1Ex2.isSuitableForDrugs(325.0));
        assertFalse(PAC1Ex2.isSuitableForDrugs(149.9));
        assertFalse(PAC1Ex2.isSuitableForDrugs(500.1));
    }

    @Test
    @Order(2)
    @DisplayName("getAlkalineMetalMolarMass")
    public void testGetAlkalineMetalMolarMass() {
        assertEquals(6.941, PAC1Ex2.getAlkalineMetalMolarMass(0));
        assertEquals(22.99, PAC1Ex2.getAlkalineMetalMolarMass(1));
        assertEquals(39.098, PAC1Ex2.getAlkalineMetalMolarMass(2));
        assertEquals(85.468, PAC1Ex2.getAlkalineMetalMolarMass(3));
        assertEquals(132.905, PAC1Ex2.getAlkalineMetalMolarMass(4));
    }

    @Test
    @Order(3)
    @DisplayName("getHalogenMolarMass")
    public void testGetHalogenMolarMass() {
        assertEquals(18.998, PAC1Ex2.getHalogenMolarMass(0));
        assertEquals(35.45, PAC1Ex2.getHalogenMolarMass(1));
        assertEquals(79.904, PAC1Ex2.getHalogenMolarMass(2));
        assertEquals(126.904, PAC1Ex2.getHalogenMolarMass(3));
    }

    @Test
    @Order(4)
    @DisplayName("calculateMolarMass")
    public void testCalculateMolarMass() {
        assertEquals(32.88, PAC1Ex2.calculateMolarMass(0, 2, 0, 1), 0.001);
        assertEquals(58.44, PAC1Ex2.calculateMolarMass(1, 1, 1, 1), 0.001);
        assertEquals(277.102, PAC1Ex2.calculateMolarMass(2, 3, 2, 2), 0.001);
        assertEquals(722.584, PAC1Ex2.calculateMolarMass(3, 4, 3, 3), 0.001);
        assertEquals(58.819, PAC1Ex2.calculateMolarMass(0, 3, 0, 2), 0.001);
    }

    @Test
    @Order(5)
    @DisplayName("getStability")
    public void testGetStability() {
        assertEquals("Very stable", PAC1Ex2.getStability(1, 1));
        assertEquals("Moderately stable", PAC1Ex2.getStability(1, 2));
        assertEquals("Moderately stable", PAC1Ex2.getStability(2, 2));
        assertEquals("Very unstable", PAC1Ex2.getStability(3, 2));
        assertEquals("Very unstable", PAC1Ex2.getStability(3, 3));
        assertEquals("Unknown", PAC1Ex2.getStability(4, 3));
        assertEquals("Unknown", PAC1Ex2.getStability(0, 0));
        assertEquals("Unknown", PAC1Ex2.getStability(-1, 2));
    }

    @Test
    @Order(6)
    @DisplayName("getCompoundsForDrugs")
    public void testGetCompoundsForDrugs() {
        // Compound names
        String[] compoundsNames = {
                "LiF",        // Real
                "NaCl",       // Real
                "KF",         // Real
                "RbBr",       // Real
                "CsI",        // Real
                "RbI",        // Real
                "Cs4F",       // Non-real
                "Cs6Cl",      // Non-real
                "Rb4I",       // Non-real
                "Cs5Br"       // Non-real
        };

        // Alkali metal indexes corresponding to their position in ALKALI_METALS_MASSES
        int[] alkaliMetalIndexes = {
                0, // Li (Lithium Fluoride)
                1, // Na (Sodium Chloride)
                2, // K (Potassium Fluoride)
                3, // Rb (Rubidium Bromide)
                4, // Cs (Cesium Iodide)
                3, // Rb (Rubidium Iodide)
                4, // Cs (Cesium Tetrafluoride)
                4, // Cs (Cesium Hexachloride)
                3, // Rb (Rubidium Tetraiodide)
                4  // Cs (Cesium Pentabromide)
        };

        // Alkali metal quantities
        int[] alkalineMetalQuantities = {
                1, // LiF
                1, // NaCl
                1, // KF
                1, // RbBr
                1, // CsI
                1, // RbI
                4, // Cs4F
                6, // Cs6Cl
                4, // Rb4I
                5  // Cs5Br
        };

        // Halogen indexes corresponding to their position in HALOGENS_MASSES
        int[] halogenIndexes = {
                0, // F (Lithium Fluoride)
                1, // Cl (Sodium Chloride)
                0, // F (Potassium Fluoride)
                2, // Br (Rubidium Bromide)
                3, // I (Cesium Iodide)
                3, // I (Rubidium Iodide)
                0, // F (Cesium Tetrafluoride)
                1, // Cl (Cesium Hexachloride)
                3, // I (Rubidium Tetraiodide)
                2  // Br (Cesium Pentabromide)
        };

        // Halogen quantities
        int[] halogenQuantities = {
                1, // LiF
                1, // NaCl
                1, // KF
                1, // RbBr
                1, // CsI
                1, // RbI
                1, // Cs4F
                1, // Cs6Cl
                1, // Rb4I
                1  // Cs5Br
        };

        PAC1Ex2.getCompoundsForDrugs(compoundsNames, alkaliMetalIndexes, alkalineMetalQuantities, halogenIndexes, halogenQuantities);

        String expectedOutput =
                "Results of the compounds for drugs:" + System.lineSeparator() +
                        "\tLiF (Very stable) is not suitable for drugs." + System.lineSeparator() +
                        "\tNaCl (Very stable) is not suitable for drugs." + System.lineSeparator() +
                        "\tKF (Very stable) is not suitable for drugs." + System.lineSeparator() +
                        "\tRbBr (Very stable) is suitable for drugs." + System.lineSeparator() +
                        "\tCsI (Very stable) is suitable for drugs." + System.lineSeparator() +
                        "\tRbI (Very stable) is suitable for drugs." + System.lineSeparator() +
                        "\tCs4F (Very unstable) is not suitable for drugs." + System.lineSeparator() +
                        "\tCs6Cl (Unknown) is not suitable for drugs." + System.lineSeparator() +
                        "\tRb4I (Very unstable) is suitable for drugs." + System.lineSeparator() +
                        "\tCs5Br (Very unstable) is not suitable for drugs." + System.lineSeparator() +
                        "Final result: 4 compounds are suitable for drugs." + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

}
