package fr.arthurvimond.rxbindingadapters;

public class MathUtils {

    static float map(float value, float inMin, float inMax, float outMin, float outMax) {
        return outMin + (outMax - outMin) * ((value - inMin) / (inMax - inMin));
    }

}
