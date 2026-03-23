package utils

import java.util.concurrent.Executor
import java.util.concurrent.Executors


/**
 * Created By Sunil_P on 17/03/2026
 */
class DiskExecutor : Executor {

    private val executor: Executor = Executors.newSingleThreadExecutor()

    override fun execute(runnable: Runnable?) {
        executor.execute (runnable)
    }

}