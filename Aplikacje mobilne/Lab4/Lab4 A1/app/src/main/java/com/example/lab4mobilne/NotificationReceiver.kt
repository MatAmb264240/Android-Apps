import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NotificationReceiver : BroadcastReceiver()
{
    override fun onReceive(context: Context?, intent: Intent?)
    {
        when(intent?.action) {
            Intent.ACTION_BATTERY_LOW -> {
                Toast.makeText(context, "BATTERY LOW", Toast.LENGTH_SHORT).show()
            }
            Intent.ACTION_BATTERY_OKAY -> {
                Toast.makeText(context, "BATTERY OKAY", Toast.LENGTH_SHORT).show()
            }
            Intent.ACTION_POWER_CONNECTED -> {
                Toast.makeText(context, "POWER CONNECTED", Toast.LENGTH_SHORT).show()
            }
            Intent.ACTION_POWER_DISCONNECTED -> {
                Toast.makeText(context, "POWER DISCONNECTED", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
