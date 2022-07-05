package com.sajib.ut_testandroid

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class WeekDayViewModel : ViewModel() {

    private val _weekDaysCount = MutableLiveData<Event<Int>>()
    val weekDaysCount = _weekDaysCount


    fun getWorkingDaysBetweenTwoDates(startDate: Date?, endDate: Date?) {

        if (startDate == null || endDate == null) {
            _weekDaysCount.postValue(Event(-1))
            return
        }

        val startCal = Calendar.getInstance()
        startCal.time = startDate
        val endCal = Calendar.getInstance()
        endCal.time = endDate

        var workDays = 0
        var criteriaOneHolidayCount = 0
        var criteriaTwoHolidayCount = 0
        var criteriaThreeHolidayCount = 0
        var normalHolidayCount = 0

        //Return 0 if start and end are the same
        if (startCal.timeInMillis === endCal.timeInMillis) {
            _weekDaysCount.postValue(Event(0))
            return
        }

        if (startCal.timeInMillis > endCal.timeInMillis) {
            startCal.time = endDate
            endCal.time = startDate
        }

        var anzacDayCount = 0
        var anzacDayInWeekendCount = 0
        var newYearCount = 0
        var queensBirthDayCount = 0
        var regularWeekendCount = 0

        val df: DateFormat = SimpleDateFormat("dd/MM/yyyy")

        startCal.add(Calendar.DAY_OF_MONTH, 1)
        while (startCal.before(endCal)) {

//            val dayStart: Int = startCal.get(Calendar.DAY_OF_MONTH)
//            val dayEnd: Int = endCal.get(Calendar.DAY_OF_MONTH)
//            if(dayStart == dayEnd){
//                break
//            }
            startCal.add(Calendar.DAY_OF_MONTH, 1)
            ++workDays

            //criteria #1
            if (startCal[Calendar.MONTH] == Calendar.APRIL && startCal[Calendar.DAY_OF_MONTH] == 25) {
                anzacDayCount++
                if (startCal[Calendar.DAY_OF_WEEK] == Calendar.SATURDAY || startCal[Calendar.DAY_OF_WEEK] == Calendar.SUNDAY) {
                    anzacDayInWeekendCount++
                }
            }

            //criteria #2
            if (startCal[Calendar.MONTH] == Calendar.JANUARY && startCal[Calendar.DAY_OF_MONTH] == 1) {
                newYearCount++
            }

            //criteria #3
            if (startCal[Calendar.MONTH] == Calendar.JUNE && startCal[Calendar.WEEK_OF_MONTH] == 2 && startCal[Calendar.DAY_OF_WEEK] == Calendar.MONDAY) {
                queensBirthDayCount++
            }

            if (startCal[Calendar.DAY_OF_WEEK] == Calendar.SATURDAY || startCal[Calendar.DAY_OF_WEEK] == Calendar.SUNDAY) {
                regularWeekendCount++
            }

        }

        val totalOffDays = anzacDayCount + newYearCount + queensBirthDayCount + regularWeekendCount - anzacDayInWeekendCount

        val totalWeekDays = workDays - totalOffDays
        _weekDaysCount.postValue(Event(totalWeekDays))
    }

    fun getDate(day: Int?, month: Int?, year: Int?): Date? {

        if(day == null || month == null || year == null){
            return null
        }

        val df: DateFormat = SimpleDateFormat("dd/MM/yyyy")
        var strDate: Date? = null

        if (day in 1..31) {
        }else{
            return null
        }

        if (month in 1..12) {
        }else{
            return null
        }

        if (year in 0..9999) {  //min max range
        }else{
           return null
        }

        var date:Date? = null
        try {
            date = df.parse("$day/$month/$year")
        }catch (e:Exception){
            e.printStackTrace()
        }
        return date
    }


}
