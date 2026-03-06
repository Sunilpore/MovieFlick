import android.content.Context
import android.util.Log
import com.movieflick.data.BuildConfig

class LogUtils {

    companion object{

        private const val TAG_DATA = "hfcDataTag"
        private const val TAG_CONTEXT_DATA = "hfcCtxTag"
        private const val TAG_ERROR = "hfcErrTag"

        private val isDebugable = BuildConfig.DEBUG

        @JvmStatic
        fun d(message: String = ""){
            Log.d(TAG_DATA, message)
        }

        @JvmStatic
        fun e(message: String = ""){
            if(isDebugable){ Log.d(TAG_ERROR, message) }
        }

        @JvmStatic
        fun ctxData(context: Context, message: String = ""){
            if(isDebugable){ Log.d(TAG_CONTEXT_DATA, String.format("%s\t%s", context.javaClass.canonicalName, message)) }
        }

    }

}