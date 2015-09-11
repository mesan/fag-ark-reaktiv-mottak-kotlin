package no.mesan.reaktiv.fengsel.mottak.actor

/**
 * Liste over actorer som kan slås opp, og adressen til de.

 * @author Christian Ihle
 */
public enum class Actorer(private val navn: String, private val adresse: String) {

    REGISTRERE_NAVN_OG_NR("RegistrerNavnOgNrActor", "/user/RegistrerNavnOgNrActor"),
    REGISTRERE_EIENDELER("RegistrerEiendelerActor", "/user/RegistrerEiendelerActor");

    public fun navn(): String {
        return navn
    }

    public fun adresse(): String {
        return adresse
    }
}
