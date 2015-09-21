package no.mesan.reaktiv.fengsel.mottak.atom

import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date
import java.util.UUID

import com.rometools.rome.feed.atom.Entry
import com.rometools.rome.feed.atom.Feed
import com.rometools.rome.io.WireFeedOutput

/**
 * Service for å lage atom feeds.
 */
public class AtomFeedGenerator {

    public fun lagNyFeed(tittel: String): Feed {
        val feed = Feed()

        feed.feedType = "atom_1.0"
        feed.title = tittel
        feed.id = genererUnikId()
        feed.updated = lagDato()

        return feed
    }

    public fun leggTilElement(feed: Feed, tittel: String, id: String): Entry {
        val entry = Entry()

        entry.title = tittel
        entry.id = id
        entry.updated = lagDato()

        leggElementIFeed(feed, entry)

        return entry
    }

    public fun hentFeedSomStreng(feed: Feed): String {
        val wfo = WireFeedOutput()

        return wfo.outputString(feed)
    }

    private fun leggElementIFeed(feed: Feed, entry: Entry) {
        var entries: MutableList<Entry>? = feed.entries

        if (entries == null) {
            entries = ArrayList<Entry>()
        }

        entries.add(entry)
        feed.entries = entries
    }

    private fun genererUnikId(): String {
        return UUID.randomUUID().toString()
    }

    private fun lagDato(): Date {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        val RFC3339DateString = sdf.format(Date())

        return sdf.parseObject(RFC3339DateString) as Date
    }
}
