package org.cys.ungenerator

import java.util.concurrent.locks.ReentrantLock

class SequenceProvider(
    private val allocSize: Long
) {
    private val lock = ReentrantLock()
    private var range: SequenceRange = SequenceRange(allocSize)

    fun nextVal(): Long {
        return range.nextVal() ?: allocateSequence()
    }

    private fun allocateSequence(): Long {
        lock.lock()
        try {
            if (!range.enable()) {
                range = SequenceRange(allocSize)
            }
        } finally {
            lock.unlock()
        }
        return range.nextVal() ?: throw IllegalStateException("AllocateSequence Error.")
    }
}
