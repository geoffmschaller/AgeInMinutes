package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		choose_age_button.setOnClickListener {
			datePicker(it)
		}
	}

	private fun datePicker(view: View) {
		val myCalendar = Calendar.getInstance()
		val year = myCalendar.get(Calendar.YEAR)
		val month = myCalendar.get(Calendar.MONTH)
		val day = myCalendar.get(Calendar.DAY_OF_MONTH)
		val datePickerDialog = DatePickerDialog(
			this,
			DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
				updateSelectedDateDisplay(month + 1, dayOfMonth, year)
				val simpleDateFormat = SimpleDateFormat("mm/dd/yyyy", Locale.ENGLISH)
				val selectedDate = simpleDateFormat
					.parse("$month/$dayOfMonth/$year")
				val currentDate = simpleDateFormat
					.parse(simpleDateFormat.format(System.currentTimeMillis()))
				updateMinutesResultDisplay((currentDate!!.time / 60000) - (selectedDate!!.time / 60000))
			}, year, month, day
		)
		datePickerDialog.datePicker.maxDate = Date().time - 86400000
		datePickerDialog.show()
	}

	private fun updateSelectedDateDisplay(month: Int, day: Int, year: Int) {
		chosen_date.text = "$month/$day/$year"
	}

	private fun updateMinutesResultDisplay(value: Long) {
		minutes_old_result.text = value.toString()
	}

}