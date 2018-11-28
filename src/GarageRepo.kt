package ca.rjreid

import java.lang.IllegalArgumentException
import java.util.concurrent.CopyOnWriteArraySet
import java.util.concurrent.atomic.AtomicInteger

object GarageRepo {
    private val idCounter = AtomicInteger()
    private val doorActions = CopyOnWriteArraySet<DoorAction>()

    fun add(action: DoorAction): DoorAction {
        if (doorActions.contains(action)) {
            return doorActions.find { it == action }!!
        }

        action.id = idCounter.incrementAndGet()
        doorActions.add(action)
        return action
    }

    fun get(id: String): DoorAction =
            doorActions.find { it.id.toString() == id } ?:
                    throw IllegalArgumentException("No action found with id of $id")

    fun get(id: Int): DoorAction = get(id.toString())

    fun getAll(): List<DoorAction> = doorActions.toList()

    fun remove(action: DoorAction) {
        if (!doorActions.contains(action)) {
            throw IllegalArgumentException("Action not found")
        }

        doorActions.remove(action)
    }

    fun remove(id: String): Boolean = doorActions.remove(get(id))

    fun remove(id: Int): Boolean = doorActions.remove(get(id))

    fun clear() = doorActions.clear()
}