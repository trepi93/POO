package edu.uoc.pac1;

public class PAC1Ex2 {

    // Alkali metals data
    private static final double[] ALKALI_METALS_MASSES = {6.941, 22.990, 39.098, 85.468, 132.905};

    // Halogens data
    private static final double[] HALOGENS_MASSES = {18.998, 35.450, 79.904, 126.904};

    private static final double MIN_MOLAR_MASS_FOR_DRUGS = 150.0;
    private static final double MAX_MOLAR_MASS_FOR_DRUGS = 500.0;

    public static boolean isSuitableForDrugs(double molarMass) {
        return molarMass >= MIN_MOLAR_MASS_FOR_DRUGS && molarMass <= MAX_MOLAR_MASS_FOR_DRUGS;
    }

    public static double getAlkalineMetalMolarMass(int alkaliMetalIndex) {
        return ALKALI_METALS_MASSES[alkaliMetalIndex];
    }

    public static double getHalogenMolarMass(int halogenIndex) {
        return HALOGENS_MASSES[halogenIndex];
    }

    public static double calculateMolarMass(int alkaliMetalIndex, int alkalineMetalQuantity, int halogenIndex, int halogenQuantity) {
        return getAlkalineMetalMolarMass(alkaliMetalIndex) * alkalineMetalQuantity +
                getHalogenMolarMass(halogenIndex) * halogenQuantity;
    }

    public static String getStability(int alkaliAtoms, int halogenAtoms) {
        int totalAtoms = alkaliAtoms + halogenAtoms;

        return switch (totalAtoms) {
            case 2 -> "Very stable";
            case 3, 4 -> "Moderately stable";
            case 5, 6 -> "Very unstable";
            default -> "Unknown";
        };
    }

    public static void getCompoundsForDrugs(String[] compoundsNames, int[] alkaliMetalIndexes, int[] alkalineMetalQuantities, int[] halogenIndexes, int[] halogenQuantities) {

        System.out.println("Results of the compounds for drugs:");

        int totalDrugsSuitable = 0;

        for (int i = 0; i < compoundsNames.length; i++) {
            double molarMass = calculateMolarMass(alkaliMetalIndexes[i], alkalineMetalQuantities[i],
                    halogenIndexes[i], halogenQuantities[i]);

            String stability = getStability(alkalineMetalQuantities[i], halogenQuantities[i]);

            if (isSuitableForDrugs(molarMass)) {
                System.out.println("\t" + compoundsNames[i] + " (" +  stability + ") is suitable for drugs.");
                totalDrugsSuitable += 1;
            } else {
                System.out.println("\t" + compoundsNames[i] + " (" +  stability + ") is not suitable for drugs.");
            }
        }
        System.out.println("Final result: " + totalDrugsSuitable + " compounds are suitable for drugs.");
    }
}
