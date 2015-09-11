package no.mesan.reaktiv.fengsel.mottak.service

/**
 * Enkel klasse for å sjekke om vi er i debug-modus.

 * @author Christian Ihle
 */
public class Debug {

    public fun erAktivert(): Boolean {
        return java.lang.Boolean.getBoolean("debug") // Bruk jvm-parameter -Ddebug=true på starteren for å aktivere
    }
}
