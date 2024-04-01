package navigationBar

import com.loxick.navapp.R


sealed class BottomItem(val title:String, val iconId: Int, val route: String) {
    object Screen1: BottomItem("", R.drawable.baseline_accessible_forward_24, "loginScreen")
    object Screen2: BottomItem("", R.drawable.baseline_accessible_forward_24, "receptScreen")
    object Screen3: BottomItem("", R.drawable.baseline_accessible_forward_24, "receptInfo")
}