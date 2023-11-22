package io.connorwyatt.airline.shared.ids

import kotlin.random.Random
import org.hashids.Hashids

object HashidGenerator {
    private val hashids = Hashids("airline", 16)

    fun generate(): String {
        return hashids.encode(Random.nextLong(9007199254740992L))
    }
}
