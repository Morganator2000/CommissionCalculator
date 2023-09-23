package algonquin.cst2335.commisioncalculator

import algonquin.cst2335.commisioncalculator.databinding.MainActivityBinding
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.ComponentActivity
import android.widget.Toast

class MainActivity : ComponentActivity() {
    private lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotBlank()) {
                    try {
                        val purchaseAmount = s.toString().toDouble()
                        calculate(purchaseAmount)
                        showImage(purchaseAmount * 20 / 6)
                        checkValue(purchaseAmount)
                    } catch (e: NumberFormatException) {
                        e.printStackTrace()
                        Toast.makeText(
                            applicationContext,
                            "How did you... No, numbers only.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    clear()
                }
            }
        })
    }
    private fun calculate(purchaseAmount: Double) {
            val markup65 = purchaseAmount * 20 / 7
            val markup70 = purchaseAmount * 20 / 6

            binding.percent65number.text = String.format("$"+"%.2f", markup65)
            binding.percent70number.text = String.format("$"+"%.2f", markup70)
    }

    private fun clear() {
        binding.percent65number.text = "$0.00"
        binding.percent70number.text = "$0.00"
    }

    private fun showImage(value: Double) {
        if (value > 9000) {
            binding.over9000.visibility = View.VISIBLE
        } else {
            binding.over9000.visibility = View.INVISIBLE
        }
    }

    private fun checkValue(value: Double) {
        when (value) {
            in 69.0..69.99 -> {
                binding.memeText.text = "Nice!"
            }
            in 420.0..420.99 -> {
                binding.memeText.text = "Blaze it!"
            }
            else -> {
                binding.memeText.text = ""
            }
        }
    }

}