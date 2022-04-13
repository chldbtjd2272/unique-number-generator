package org.cys.ungenerator

import java.util.concurrent.atomic.AtomicLong

class SequenceRange(
    private val sequenceSize: Long
) {
    private val allocatedCount = AtomicLong(0L)

    fun nextVal(): Long? {
        val value = allocatedCount.getAndIncrement();
        return if (value > sequenceSize) null else value
    }

    fun enable(): Boolean {
        return (allocatedCount.get()) < sequenceSize
    }
}