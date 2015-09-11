package no.mesan.reaktiv.fengsel.mottak.repository

import java.util.ArrayList

import no.mesan.reaktiv.fengsel.mottak.domene.Fange

import com.google.common.collect.ImmutableList

/**
 * In-memory "database" med kontrollerte fanger.

 * @author Christian Ihle
 */
public class KontrollerteFangerRepository {

    private val fanger: MutableList<Fange>

    init {
        fanger = ArrayList<Fange>()
    }

    public fun leggTilFange(fange: Fange) {
        println("KontrollerteFangerRepository - legger til fange: " + fange)
        fanger.add(fange)
    }

    public fun hentAlleFanger(): List<Fange> {
        return ImmutableList.copyOf(fanger)
    }
}
