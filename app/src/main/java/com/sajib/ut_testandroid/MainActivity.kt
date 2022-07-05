package com.sajib.ut_testandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.sajib.ut_testandroid.databinding.ActivityMainBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.YearMonth
import java.util.*

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val weekDayViewModel: WeekDayViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initListener()
    }

    private fun initListener() {

        binding?.btnGetWeekDays?.setOnClickListener{

            val startDate = weekDayViewModel.getDate(
                binding?.tvStartDay?.getStringText()?.toInt(),
                binding?.tvStartMonth?.getStringText()?.toInt(),
                binding?.tvStartYear?.getStringText()?.toInt())

            if(startDate == null) {
                weekDayViewModel.weekDaysCount.postValue(Event(-1)) // Show -1 for invalid date or null
                return@setOnClickListener
            }

            val endDate = weekDayViewModel.getDate(
                binding?.tvEndDay?.getStringText()?.toInt(),
                binding?.tvEndMonth?.getStringText()?.toInt(),
                binding?.tvEndYear?.getStringText()?.toInt())

            if(endDate == null) {
                weekDayViewModel.weekDaysCount.postValue(Event(-1))
                return@setOnClickListener
            }

            weekDayViewModel.getWorkingDaysBetweenTwoDates(startDate,endDate)
        }

        weekDayViewModel.weekDaysCount.observe(this){
            binding?.tvWeekDays?.text = it.getContentIfNotHandled().toString()
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


}


