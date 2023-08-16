package algonquin.cst2335.commisioncalculator

import algonquin.cst2335.commisioncalculator.databinding.MainActivityBinding
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.view.KeyEvent

class MainActivity : ComponentActivity() {
    private lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.editText.setOnKeyListener { view, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                val purchaseAmountStr = binding.editText.text.toString()
                if (purchaseAmountStr.isNotEmpty()) {
                    val purchaseAmount = purchaseAmountStr.toDouble()
                    calculate(purchaseAmount)
                }
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }
    private fun calculate(purchaseAmount: Double) {
        val markup65 = purchaseAmount * 1.65
        val markup70 = purchaseAmount * 1.7

        binding.percent65number.text = String.format("%.2f", markup65)
        binding.percent70number.text = String.format("%.2f", markup70)
    }
}