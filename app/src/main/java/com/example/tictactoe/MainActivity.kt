package com.example.tictactoe


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var allFields: Array<TextView>
    var currentPlayer = "X"
    var isPlaying = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvStatus.text = getString(R.string.tv_status_start, currentPlayer)
        allFields = with(binding) { arrayOf(f0, f1, f2, f3, f4, f5, f6, f7, f8) }

    }

    fun onClick(view: View) {

        val textView = view as TextView
        var status = binding.tvStatus

        if (!isPlaying) {
            resetGame()
            return
        }
        if (textView.text == "") {
            if (currentPlayer == "X") textView.setTextColor(Color.GREEN) else textView.setTextColor(
                Color.RED
            )
            textView.text = currentPlayer
            if (checkWin()) {
                status.text = getString(R.string.tv_status_win, currentPlayer)
                isPlaying = false
            } else if (allFields.all { it.text != "" }) {
                status.text = getString(R.string.tv_status_draw)
                isPlaying = false
            } else {
                currentPlayer = if (currentPlayer == "X") "O" else "X"

                status.text = getString(R.string.tv_status_start, currentPlayer)
            }
        }
    }

    private fun checkWin(): Boolean {
        with(binding) {
            val horizontal =
                (f0.text == f1.text && f1.text == f2.text && f0.text != "") ||
                    (f3.text == f4.text && f4.text == f5.text && f3.text != "") ||
                    (f6.text == f7.text && f7.text == f8.text && f6.text != "")

            val vertical =
                (f0.text == f3.text && f3.text == f6.text && f0.text != "") ||
                    (f1.text == f4.text && f4.text == f7.text && f1.text != "") ||
                    (f2.text == f5.text && f5.text == f8.text && f2.text != "")

            val diagonal =
                (f0.text == f4.text && f4.text == f8.text && f0.text != "") ||
                    (f2.text == f4.text && f4.text == f6.text && f2.text != "")

            return horizontal || vertical || diagonal
        }

    }

    fun resetGame() {
        currentPlayer = "X"
        binding.tvStatus.text = getString(R.string.tv_status_start, currentPlayer)
        isPlaying = true
        allFields.forEach {
            it.text = ""
        }
    }
}